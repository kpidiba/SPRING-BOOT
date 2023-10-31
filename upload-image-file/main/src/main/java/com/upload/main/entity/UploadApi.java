package com.upload.main.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UploadApi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String filename;
    private String fileurl;
    
    public UploadApi(int id, String filename, String fileurl) {
        this.id = id;
        this.filename = filename;
        this.fileurl = fileurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    @Override
    public String toString() {
        return "UploadApi [id=" + id + ", filename=" + filename + ", fileurl=" + fileurl + "]";
    }
}
