package com.hust.productsale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        ProductSaleApplication.class,
        Jsr310JpaConverters.class
})
public class ProductSaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductSaleApplication.class, args);
    }

}
