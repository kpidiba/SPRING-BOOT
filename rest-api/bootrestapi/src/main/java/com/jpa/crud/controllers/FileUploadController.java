package com.jpa.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jpa.crud.helper.FileUploaderHelper;

@RestController
public class FileUploadController {
    @Autowired
    FileUploaderHelper fileUploadFile;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("profile") MultipartFile profile) {
        // return ResponseEntity.ok("name: "+profile.getName()+"\n size:
        // "+profile.getSize()+"\n content type: "+profile.getContentType()+"\n original
        // name: "+profile.getOriginalFilename());

        // NOTE: controle effectue sur le fichier
        if (profile.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("file is empty");
        }
        // else if(!profile.getContentType().equals("image/jpeg")){
        // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only
        // JPEG content type are allowed");
        // }
        if (fileUploadFile.uploadFile(profile)) {
            // return ResponseEntity.status(HttpStatus.OK).body("file is succesfully
            // upload");
            return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(profile.getOriginalFilename()).toUriString());
        }

        return ResponseEntity.ok("working");
    }
}
