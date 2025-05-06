package com.chefzy.blobmicroservice.repository;

import com.chefzy.blobmicroservice.entity.BlobMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlobMetaDataRepository extends JpaRepository<BlobMetaData, Long> {
    void deleteByFileName(String fileName);

    List<BlobMetaData> findByUserTypeAndUserId(String userType, Long userId);
}
