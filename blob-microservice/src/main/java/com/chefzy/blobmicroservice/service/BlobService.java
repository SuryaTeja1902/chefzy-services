package com.chefzy.blobmicroservice.service;

import com.chefzy.blobmicroservice.entity.BlobMetaData;
import com.chefzy.blobmicroservice.repository.BlobMetaDataRepository;
import io.minio.*;
import jakarta.transaction.Transactional;
import org.apache.commons.compress.utils.FileNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlobService {

    @Autowired
    private BlobMetaDataRepository blobMetaDataRepository;

    @Autowired
    private MinioClient minioClient;
    @Value("${minio.bucket}")
    String bucketName;

    @Transactional
    public BlobMetaData upload(MultipartFile file, Long userId, String userType) {
            try{

                String original_fileName = file.getOriginalFilename();
                String fileName = userType + "_" + userId + "_" + original_fileName;
                if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                    minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                }
                minioClient.putObject(
                        PutObjectArgs.builder().bucket(bucketName).object(fileName).stream(file.getInputStream(), file.getSize(), -1)
                                .contentType(file.getContentType()).build()
                );

                Optional<BlobMetaData> existingBlob = blobMetaDataRepository.findByFileName(fileName);
                BlobMetaData blob = new BlobMetaData();
                if (existingBlob.isPresent()) {
                    blob = existingBlob.get();
                }
                blob.setFileName(fileName);
                blob.setContentType(file.getContentType());
                blob.setSize(file.getSize());
                blob.setUserId(userId);
                blob.setBucketName(bucketName);
                blob.setUploadedAt(LocalDateTime.now());
                blob.setUserType(userType);

                return blobMetaDataRepository.save(blob);
            }
            catch(Exception e){
                throw new RuntimeException(e);
            }

    }

    @Transactional
    public StreamingResponseBody download(String fileName) {
        try{
            InputStream inputStream = minioClient.getObject(
                    GetObjectArgs.builder().bucket(bucketName).object(fileName).build()
            );

            return out -> {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                inputStream.close();
            };
        }
        catch (Exception e){
            throw new RuntimeException("Download failed",e);
        }

    }
    @Transactional
    public List<BlobMetaData> getAllFilesByUser(String userType, Long userId) {
        return blobMetaDataRepository.findByUserTypeAndUserId(userType,userId);

    }
    @Transactional
    public void delete(String fileName) {
        try
        {
            minioClient.removeObject(
                    RemoveObjectArgs.builder().bucket(bucketName).object(fileName).build()
            );
            blobMetaDataRepository.deleteByFileName(fileName);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }
}
