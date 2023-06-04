package com.hust.productsale.filesystem.service;

import com.hust.productsale.filesystem.model.Image;
import com.hust.productsale.filesystem.repository.ImageRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private DatabaseSequenceService sequenceService;

    public Long uploadImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setId(sequenceService.generateSequence(Image.SEQUENCE_NAME));
        image.setData(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        image.setFileType(file.getContentType());
        image.setFileName(file.getOriginalFilename());
        image.setCreateDate(new Date());
        image = imageRepository.insert(image);
        return image.getId();
    }

    public Image getImage(Long id) {
        return imageRepository.findById(id).get();
    }
}
