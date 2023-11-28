package com.upload.main.services.implementations;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.upload.main.entity.Image;
import com.upload.main.repository.ImageRepository;
import com.upload.main.services.attachements.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
    private ImageRepository repository;

    public ImageServiceImpl(ImageRepository repository) throws Exception {
        this.repository = repository;

    }

    @Override
    public Image saveImage(Image image) {
        return repository.save(image);
    }

    @Override
    public void deleteImage(Image image) {
        repository.delete(image);
    }

    @Override
    public List<Image> getAll() {
        return repository.findAll();
    }

    @Override
    public Image findImageByName(String name) {
        Optional<Image> image = repository.findByName(name);
        return image.orElseThrow(() -> new RuntimeException("Image not found"));

    }

    @Override
    public Image findImageById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
    }
}
