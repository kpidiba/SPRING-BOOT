package com.upload.main.services.attachements;

import java.util.List;


import com.upload.main.entity.Image;

public interface ImageService {

    Image saveImage(Image image);

    void deleteImage(Image image);

    List<Image> getAll();

    Image findImageByName(String name);

    Image findImageById(Long id);

    // UploadApiResponse
    // UploadApiResponse uploadFile(MultipartFile file) throws Exception;

    // UploadApiResponse previewFile(int fileId);

    // UploadApiResponse downloadFile(int fileId);

    // UploadApiResponse saveFileName(Image uploadApi);

}
