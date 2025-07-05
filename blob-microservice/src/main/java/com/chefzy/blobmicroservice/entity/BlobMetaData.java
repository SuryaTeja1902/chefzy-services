package com.chefzy.blobmicroservice.entity;


import com.chefzy.blobmicroservice.enums.DocumentType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Entity
@Slf4j
@Data
@Table(name = "blob_metadata")
public class BlobMetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String objectKey;
    private String originalFilename;
    private String bucketName;
    private String contentType;
    private Long size;
    private Long userId;
    private String userType;
    private LocalDateTime uploadedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType documentType;
}
