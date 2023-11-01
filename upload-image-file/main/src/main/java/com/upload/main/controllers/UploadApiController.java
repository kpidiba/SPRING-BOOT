package com.upload.main.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.upload.main.dto.UploadApiDTO;
import com.upload.main.entity.UploadApi;
import com.upload.main.response.UploadApiResponse;
import com.upload.main.services.UploadApiService;

@RestController
public class UploadApiController {
    private UploadApiService uploadApiService;

    public UploadApiController(UploadApiService service) {
        this.uploadApiService = service;
    }

    @PostMapping(value="/upload/api")
    public ResponseEntity<UploadApiResponse> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        // NOTE: UPLOAD FILE INFORMATIONS
        System.out.println("name: " + file.getName());
        // System.out.println("size: " + file.getSize());
        // System.out.println("Original File name: " + file.getOriginalFilename());
        // System.out.println("content Type: " + file.getContentType());
        // this.uploadApiService.uploadFile(file);
        return ResponseEntity.ok(new UploadApiResponse("upload success",""));
    }

    //NOTE:MULTI PARAMA
    @PostMapping(value="/upload/api/param")
    public ResponseEntity<UploadApiResponse> uploadFileParam(@RequestParam("name") String name,
    @RequestParam("email") String email,
    @RequestParam("file") MultipartFile file) throws Exception {
        // NOTE: UPLOAD FILE INFORMATIONS
        System.out.println("name: " + file.getName());
        // System.out.println("size: " + file.getSize());
        // System.out.println("Original File name: " + file.getOriginalFilename());
        // System.out.println("content Type: " + file.getContentType());
        // this.uploadApiService.uploadFile(file);
        return ResponseEntity.ok(new UploadApiResponse("upload success",""));
    }

}
