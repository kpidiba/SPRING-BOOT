package com.upload.main.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.upload.main.entity.UploadApi;
import com.upload.main.repository.AttachementRepository;
import com.upload.main.repository.UploadApiRepository;
import com.upload.main.response.UploadApiResponse;

@Service
public class UploadApiServiceImpl implements UploadApiService {
    public final String UPLOAD_DIR = new File("src/main/resources/static/").getAbsolutePath();
    private UploadApiRepository repository;

    public UploadApiServiceImpl( UploadApiRepository repository) throws Exception {
        this.repository = repository;

    }

    @Override
    public UploadApiResponse uploadFile(MultipartFile file) throws Exception {
        System.out.println(UPLOAD_DIR);
        // Create a subfolder if it doesn't exist
        File subfolder = new File(UPLOAD_DIR, "images");
        if (!subfolder.exists()) {
            if (subfolder.mkdirs()) {
                System.out.println("Subfolder created successfully.");
                try {
                    Files.copy(file.getInputStream(),
                            Paths.get(UPLOAD_DIR + "/images" + File.separator + file.getOriginalFilename()),
                            StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Files.copy(file.getInputStream(),
                        Paths.get(UPLOAD_DIR + "/images" + File.separator + file.getOriginalFilename()),
                        StandardCopyOption.REPLACE_EXISTING);

            }
        } else {
            System.out.println("exists");
        }
        return null;
    }

    @Override
    public UploadApiResponse previewFile(int fileId) {
        return null;
    }

    @Override
    public UploadApiResponse downloadFile(int fileId) {
        throw new UnsupportedOperationException("Unimplemented method 'downloadFile'");
    }

    @Override
    public UploadApiResponse saveFileName(UploadApi uploadApi) {
        repository.save(uploadApi);
return new UploadApiResponse("upload success",uploadApi.getFilename());
    }

}
