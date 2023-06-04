package com.hust.productsale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        ProductSaleApplication.class,
        Jsr310JpaConverters.class
})
@EnableMongoRepositories
public class ProductSaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductSaleApplication.class, args);
    }

}
