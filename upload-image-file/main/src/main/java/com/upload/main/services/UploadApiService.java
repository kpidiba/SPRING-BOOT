package com.upload.main.services;

import org.springframework.web.multipart.MultipartFile;

import com.upload.main.entity.UploadApi;
import com.upload.main.response.UploadApiResponse;

public interface UploadApiService {
    UploadApiResponse uploadFile(MultipartFile file) throws Exception;

    UploadApiResponse previewFile(int fileId);

    UploadApiResponse downloadFile(int fileId);    
    
    UploadApiResponse saveFileName(UploadApi uploadApi);

}
