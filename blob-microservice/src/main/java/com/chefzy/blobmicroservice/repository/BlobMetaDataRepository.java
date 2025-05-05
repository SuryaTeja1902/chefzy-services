package com.chefzy.blobmicroservice.repository;

import com.chefzy.blobmicroservice.entity.BlobMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlobMetaDataRepository extends JpaRepository<BlobMetaData, Long> {}
