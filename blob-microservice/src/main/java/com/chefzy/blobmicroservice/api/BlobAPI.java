package com.chefzy.blobmicroservice.api;

import com.chefzy.blobmicroservice.dto.BlobDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;

public interface BlobAPI {

    @PostMapping("/upload")
    ResponseEntity<BlobDTO> upload(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId, @RequestParam("userType") String userType);


    @GetMapping("/{fileName}/download")
    ResponseEntity<StreamingResponseBody> download(@PathVariable String fileName);

    @GetMapping("/{userType}/{userId}/all")
    ResponseEntity<List<BlobDTO>> getAllFilesByUser(@PathVariable String userType, @PathVariable Long userId);

    @DeleteMapping("/{fileName:.+}")
    ResponseEntity<Void> delete(@PathVariable String filename);

}
