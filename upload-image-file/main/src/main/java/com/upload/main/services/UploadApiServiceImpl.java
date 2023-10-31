package com.upload.main.services;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import com.upload.main.response.UploadApiResponse;

@Service
public class UploadApiServiceImpl implements UploadApiService {
    public final String UPLOAD_DIR = new File("static/").getAbsolutePath();

    public UploadApiServiceImpl() throws Exception{
    }

    @Override
    public UploadApiResponse uploadFile(MultipartFile file) throws Exception {
        // Create a subfolder if it doesn't exist
        File subfolder = new File(UPLOAD_DIR, "image");
        if (!subfolder.exists()) {
            if (subfolder.mkdirs()) {
                System.out.println("Subfolder created successfully.");
            } else {
                System.err.println("Failed to create subfolder.");
            }
        }
        return null;
    }

    @Override
    public UploadApiResponse previewFile(int fileId)  {
        throw new UnsupportedOperationException("Unimplemented method 'previewFile'");
    }

    @Override
    public UploadApiResponse downloadFile(int fileId)  {
        throw new UnsupportedOperationException("Unimplemented method 'downloadFile'");
    }

}
