package com.chefzy.blobmicroservice.controller;


import com.chefzy.blobmicroservice.api.BlobAPI;
import com.chefzy.blobmicroservice.dto.BlobDTO;
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
    public ResponseEntity<BlobDTO> upload(MultipartFile file, Long userId, String userType) {
            BlobMetaData data = blobService.upload(file,userId,userType);
            return ResponseEntity.ok(BlobDTO.toDTO(data));
    }

    @Override
    public ResponseEntity<Resource> download(String fileName) {
        Resource file = blobService.download(fileName);
        return ResponseEntity.ok().body(file);
    }

    @Override
    public ResponseEntity<List<BlobDTO>> getAllFilesByUser(String userType, Long userId) {
        List<BlobMetaData> blob_list = blobService.getAllFilesByUser(userType,userId);
        return ResponseEntity.ok().body(BlobDTO.toDTO(blob_list));
    }

    @Override
    public ResponseEntity<Void> delete(String fileName) {
        blobService.delete(fileName);
        return ResponseEntity.noContent().build();
    }
}
