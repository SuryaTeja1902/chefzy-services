package com.chefzy.blobmicroservice.repository;

import com.chefzy.blobmicroservice.entity.BlobMetaData;
import com.chefzy.blobmicroservice.enums.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlobMetaDataRepository extends JpaRepository<BlobMetaData, Long> {
    Optional<BlobMetaData> findByUserTypeAndUserIdAndOriginalFilenameAndDocumentType(
            String userType,
            Long userId,
            String originalFilename,
            DocumentType documentType
    );
    List<BlobMetaData> findByUserTypeAndUserIdAndDocumentType(String userType, Long userId, DocumentType documentType);

    Optional<BlobMetaData> findByObjectKey(String objectKey);
}
