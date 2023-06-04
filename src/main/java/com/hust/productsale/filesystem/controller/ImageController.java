package com.hust.productsale.filesystem.controller;

import com.hust.productsale.annotation.CurrentUser;
import com.hust.productsale.bean.ApiResponse;
import com.hust.productsale.filesystem.model.Image;
import com.hust.productsale.filesystem.service.ImageService;
import com.hust.productsale.model.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/filesystem")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/uploadimage", consumes = {"multipart/form-data"})
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            Long idFile = imageService.uploadImage(file);
            return ResponseEntity.ok(new ApiResponse(true, idFile, "Upload file thành công."));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, null, "Upload file không thành công."));
        }
    }

    @GetMapping(value = "/getimage")
    public ResponseEntity<?> getImage(@PathVariable Long id) {
        Image image = imageService.getImage(id);
        return ResponseEntity.ok(new ApiResponse(true, image, "Lấy file thành công"));
    }
}
