package com.dockerdemo.product_service.service;

import com.dockerdemo.product_service.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO createProduct(ProductDTO productDTO);
}