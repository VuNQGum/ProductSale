package com.hust.productsale.filesystem.repository;

import com.hust.productsale.filesystem.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, Long> {

}
