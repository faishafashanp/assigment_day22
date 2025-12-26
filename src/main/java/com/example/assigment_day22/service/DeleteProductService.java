package com.example.assigment_day22.service;

import com.example.assigment_day22.entity.ProductEntity;
import com.example.assigment_day22.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DeleteProductService {

    private final ProductRepository productRepository;

    public DeleteProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void deleteProduct(Long id) {

        ProductEntity product = productRepository.findById(id).orElseThrow(() ->
                     new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id " + id + " not found")
                );

        productRepository.delete(product);
    }
}
