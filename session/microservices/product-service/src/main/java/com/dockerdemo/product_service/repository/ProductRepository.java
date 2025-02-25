package com.dockerdemo.product_service.repository;

import com.dockerdemo.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
