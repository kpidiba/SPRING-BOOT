package com.jpa.crud.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Component

public class FileUploaderHelper {
    // public final String UPLOAD_DIR =
    // "C:\\Users\\kpidi\\Coding\\Github\\SPRING-BOOT\\bootrestapi\\src\\main\\resources\\static\\image";

    @Autowired
    private ServletContext context;
    // public final String UPLOAD_DIR = new
    // ClassPathResource("static").getFile().getAbsolutePath();
    // public final String UPLOAD_DIR = context.getRealPath("/static/");

    public FileUploaderHelper() throws Exception {

    }

    public boolean uploadFile(MultipartFile file) {
        boolean f = false;
        String UPLOAD_DIR = context.getRealPath("/static/");
        try {

            // NOTE: method 1
            InputStream is = file.getInputStream();
            byte data[] = new byte[is.available()];
            is.read(data);

            // write
            FileOutputStream fos = new FileOutputStream(UPLOAD_DIR + File.separator + file.getOriginalFilename());
            fos.write(data);
            fos.flush();
            fos.close();

            // NOTE:methode 2
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            f = true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return f;
    }

}
