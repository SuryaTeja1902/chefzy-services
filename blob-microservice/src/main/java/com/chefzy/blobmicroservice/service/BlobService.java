package com.chefzy.blobmicroservice.service;

import com.chefzy.blobmicroservice.entity.BlobMetaData;
import com.chefzy.blobmicroservice.repository.BlobMetaDataRepository;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class BlobService {

    @Autowired
    private BlobMetaDataRepository blobMetaDataRepository;

    @Autowired
    private MinioClient minioClient;

    public BlobMetaData upload(MultipartFile file, Long userId, String userType) {


    }

    public Resource download(String fileName) {

    }

    public List<BlobMetaData> getAllFilesByUser(String userType, String userId) {

    }

    public void delete(String fileName) {

    }
}
