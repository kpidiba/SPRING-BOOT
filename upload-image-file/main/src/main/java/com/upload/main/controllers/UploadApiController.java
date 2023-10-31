package com.upload.main.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.upload.main.response.UploadApiResponse;
import com.upload.main.services.UploadApiService;

@RestController
public class UploadApiController {
    private UploadApiService uploadApiService;

    public UploadApiController(UploadApiService service) {
        this.uploadApiService = service;
    }

    @PostMapping("/upload/api")
    public ResponseEntity<UploadApiResponse> uploadFile(@RequestParam("file") MultipartFile profile) throws Exception {
        // NOTE: UPLOAD FILE INFORMATIONS
        // System.out.println("name: " + profile.getName());
        // System.out.println("size: " + profile.getSize());
        // System.out.println("Original File name: " + profile.getOriginalFilename());
        // System.out.println("content Type: " + profile.getContentType());
        this.uploadApiService.uploadFile(profile);
        return null;
    }
}
