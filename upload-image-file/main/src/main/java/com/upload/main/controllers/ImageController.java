package com.upload.main.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.upload.main.entity.Image;
import com.upload.main.response.UploadApiResponse;
import com.upload.main.services.attachements.ImageService;
import com.upload.main.utils.FileUploadUtils;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {
    private ImageService imageService;
    private FileUploadUtils fileUploadUtils;

    public ImageController(ImageService service,FileUploadUtils fileUploadUtils) {
        this.fileUploadUtils = fileUploadUtils;
        this.imageService = service;
    }

    @PostMapping(value="/upload")
    public ResponseEntity<UploadApiResponse> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        // NOTE: UPLOAD FILE INFORMATIONS
        String name = this.fileUploadUtils.uploadImage(file);
        this.imageService.saveImage(new Image(name,file.getSize()));
        return ResponseEntity.ok(new UploadApiResponse("upload success",name));
    }

    @GetMapping(value="")
    public ResponseEntity<List<Image>> getIMages() {
        // NOTE: UPLOAD FILE INFORMATIONS
        return ResponseEntity.ok(this.imageService.getAll());
    }

    //NOTE:MULTI PARAMA
    @PostMapping(value="/upload/api/param")
    public ResponseEntity<UploadApiResponse> uploadFileParam(@RequestParam("name") String name,
    @RequestParam("email") String email,
    @RequestParam("file") MultipartFile file) throws Exception {
        // NOTE: UPLOAD FILE INFORMATIONS
        System.out.println("name: " + file.getName());
        System.out.println("size: " + file.getSize());
        System.out.println("Original File name: " + file.getOriginalFilename());
        System.out.println("content Type: " + file.getContentType());
        return ResponseEntity.ok(new UploadApiResponse("upload success",""));
    }

}
