package com.upload.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upload.main.entity.UploadApi;

public interface UploadApiRepository extends JpaRepository<UploadApi,String>{
    
}
