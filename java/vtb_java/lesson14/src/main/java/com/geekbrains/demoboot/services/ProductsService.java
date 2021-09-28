package com.geekbrains.demoboot.services;

import com.geekbrains.demoboot.entities.Product;
import com.geekbrains.demoboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public java.lang.Object getAllProducts() {
        return productRepository.findAll();
    }

    public java.lang.Object getAllProductsByStringContains(String str) {
        return productRepository.getAllProductsByStringContains(str);
    }

    public void add(Product product) {
        productRepository.save(product);
    }
}
