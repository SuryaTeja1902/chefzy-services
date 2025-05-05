package com.chefzy.blobmicroservice.api;

import com.chefzy.blobmicroservice.entity.BlobMetaData;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlobAPI {

    @PostMapping("/upload")
    ResponseEntity<BlobMetaData> upload(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId, @RequestParam("userType") String userType);


    @GetMapping("/{fileName}/download")
    ResponseEntity<Resource> download(@PathVariable String fileName);

    @GetMapping("/{userType}/{userId}/all")
    ResponseEntity<List<BlobMetaData>> getAllFilesByUser(@PathVariable String userType, @PathVariable String userId);

    @DeleteMapping("/{filename}")
    ResponseEntity<Void> delete(@PathVariable String filename);

}
