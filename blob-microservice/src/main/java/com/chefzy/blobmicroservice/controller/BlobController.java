package com.chefzy.blobmicroservice.controller;


import com.chefzy.blobmicroservice.api.BlobAPI;
import com.chefzy.blobmicroservice.dto.BlobDTO;
import com.chefzy.blobmicroservice.entity.BlobMetaData;
import com.chefzy.blobmicroservice.enums.DocumentType;
import com.chefzy.blobmicroservice.service.BlobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blobs")
public class BlobController implements BlobAPI {

    @Autowired
    private BlobService blobService;

    @Override
    public ResponseEntity<BlobDTO> upload(MultipartFile file, Long userId, String userType, DocumentType documentType) {
            BlobMetaData data = blobService.upload(file,userId,userType, documentType);
            return ResponseEntity.ok(BlobDTO.toDTO(data));
    }

    @Override
    public ResponseEntity<StreamingResponseBody> download(String fileName, String rangeHeader) {
        StreamingResponseBody file = blobService.download(fileName, rangeHeader);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .body(file);
    }

    @Override
    public ResponseEntity<List<BlobDTO>> getUserFiles(String userType, Long userId, DocumentType documentType) {
        List<BlobMetaData> blob_list = blobService.getUserFilesByType(userType,userId,documentType);
        return ResponseEntity.ok().body(BlobDTO.toDTO(blob_list));
    }

    @Override
    public ResponseEntity<Void> delete(String fileName) {
        blobService.delete(fileName);
        return ResponseEntity.noContent().build();
    }
}
