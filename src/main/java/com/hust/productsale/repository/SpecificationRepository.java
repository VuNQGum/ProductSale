package com.hust.productsale.repository;

import com.hust.productsale.model.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecificationRepository extends JpaRepository<Specification, Long> {
}