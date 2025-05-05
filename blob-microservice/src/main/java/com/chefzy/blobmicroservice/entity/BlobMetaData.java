package com.chefzy.blobmicroservice.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class BlobMetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String bucketName;
    private String contentType;
    private Long size;
    private Long userId;
    private String userType;
    private LocalDateTime uploadedAt;

}
