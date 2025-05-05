package com.chefzy.blobmicroservice.controller;


import com.chefzy.blobmicroservice.api.BlobAPI;
import com.chefzy.blobmicroservice.entity.BlobMetaData;
import com.chefzy.blobmicroservice.service.BlobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/blobs")
public class BlobController implements BlobAPI {

    @Autowired
    private BlobService blobService;

    @Override
    public ResponseEntity<BlobMetaData> upload(MultipartFile file, Long userId, String userType) {
            BlobMetaData data = BlobService.upload(file,userId,userType);
            return ResponseEntity.ok(data);

    }

    @Override
    public ResponseEntity<Resource> download(String fileName) {
        Resource file = BlobService.download(fileName);
        return ResponseEntity.ok().body(file);
    }

    @Override
    public ResponseEntity<List<BlobMetaData>> getAllFilesByUser(String userType, String userId) {
        List<BlobMetaData> blob_list = BlobService.getAllFilesByUser(userType,userId);
        return ResponseEntity.ok().body(blob_list);
    }

    @Override
    public ResponseEntity<Void> delete(String fileName) {
        BlobService.delete(fileName);
        return ResponseEntity.noContent().build();
    }
}
