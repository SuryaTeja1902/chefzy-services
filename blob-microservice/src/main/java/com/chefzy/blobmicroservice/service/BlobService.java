package com.chefzy.blobmicroservice.service;

import com.chefzy.blobmicroservice.entity.BlobMetaData;
import com.chefzy.blobmicroservice.enums.DocumentType;
import com.chefzy.blobmicroservice.repository.BlobMetaDataRepository;
import io.minio.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlobService {

    @Autowired
    private BlobMetaDataRepository blobMetaDataRepository;

    @Autowired
    private MinioClient minioClient;
    @Value("${minio.bucket}")
    String bucketName;

    @Transactional
    public BlobMetaData upload(MultipartFile file, Long userId, String userType, DocumentType documentType) {
            try{

                String original_fileName = file.getOriginalFilename();
                String object_key = String.format("%ss/%d/%s/%s",
                        userType.toLowerCase(), userId, documentType.name().toLowerCase(), original_fileName);

                if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                    minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                }
                minioClient.putObject(
                        PutObjectArgs.builder().bucket(bucketName).object(object_key).stream(file.getInputStream(), file.getSize(), -1)
                                .contentType(file.getContentType()).build()
                );
                Optional<BlobMetaData> existingBlob = blobMetaDataRepository
                        .findByUserTypeAndUserIdAndOriginalFilenameAndDocumentType(userType, userId, original_fileName, documentType);

                BlobMetaData blob = existingBlob.orElseGet(BlobMetaData::new);

                blob.setObjectKey(object_key);
                blob.setOriginalFilename(original_fileName);
                blob.setContentType(file.getContentType());
                blob.setSize(file.getSize());
                blob.setUserId(userId);
                blob.setBucketName(bucketName);
                blob.setUploadedAt(LocalDateTime.now());
                blob.setUserType(userType);
                blob.setDocumentType(documentType);

                return blobMetaDataRepository.save(blob);
            }
            catch(Exception e){
                throw new RuntimeException(e);
            }

    }


    @Transactional
    public List<BlobMetaData> getUserFilesByType(String userType, Long userId, DocumentType documentType) {
        return blobMetaDataRepository.findByUserTypeAndUserIdAndDocumentType(userType, userId, documentType);
    }

    @Transactional
    public StreamingResponseBody download(String ObjectKey, String rangeHeader) {
        try {

            InputStream inputStream;
            long contentLength;
            long start = 0;
            Optional<BlobMetaData> metaData = blobMetaDataRepository.findByObjectKey(ObjectKey);
            long fileSize = metaData.get().getSize();

            long end = fileSize - 1;

            if (rangeHeader != null && rangeHeader.startsWith("bytes=")) {
                String rangeValue = rangeHeader.substring("bytes=".length());
                String[] parts = rangeValue.split("-");
                start = Long.parseLong(parts[0]);
                if (parts.length > 1 && !parts[1].isEmpty()) {
                    end = Long.parseLong(parts[1]);
                }

                long length = end - start + 1;
                inputStream = minioClient.getObject(
                        GetObjectArgs.builder()
                                .bucket(bucketName)
                                .object(ObjectKey)
                                .offset(start)
                                .length(length)
                                .build()
                );
                contentLength = length;
            } else {
                inputStream = minioClient.getObject(
                        GetObjectArgs.builder().bucket(bucketName).object(ObjectKey).build()
                );
                contentLength = fileSize;

            }
            final long finalStart = start;
            final long finalEnd = end;
            final long finalContentLength = contentLength;


            return outputStream -> {
                byte[] buffer = new byte[1024];
                int bytesRead;
                try (inputStream) {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Transactional
    public void delete(String objectKey) {
        try
        {
            BlobMetaData metaData = blobMetaDataRepository.findByObjectKey(objectKey)
                    .orElseThrow(() -> new RuntimeException("No metadata found for objectKey: " + objectKey));
            minioClient.removeObject(
                    RemoveObjectArgs.builder().bucket(bucketName).object(objectKey).build()
            );
            blobMetaDataRepository.delete(metaData);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Failed to delete file: "+objectKey,e);
        }

    }
}
