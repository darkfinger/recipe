package com.dkkcorp.recipe.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImageFile(Long aLong, MultipartFile file);
}
