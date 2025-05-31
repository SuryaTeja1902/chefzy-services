package com.chefzy.blobmicroservice.api;

import com.chefzy.blobmicroservice.dto.BlobDTO;
import com.chefzy.blobmicroservice.enums.DocumentType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;

public interface BlobAPI {

    @PostMapping("/upload")
    ResponseEntity<BlobDTO> upload(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId, @RequestParam("userType") String userType, @RequestParam("documentType") DocumentType documentType);


    @GetMapping("/download")
    ResponseEntity<StreamingResponseBody> download(@RequestParam String fileName, @RequestHeader(value = "Range", required = false) String rangeHeader);

    @GetMapping("/{userType}/{userId}/{documentType}")
    ResponseEntity<List<BlobDTO>> getUserFiles(@PathVariable String userType, @PathVariable Long userId, @PathVariable DocumentType documentType);

    @DeleteMapping
    ResponseEntity<Void> delete(@RequestParam String fileName);

}
