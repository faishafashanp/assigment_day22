package com.example.assigment_day22.service;

import com.example.assigment_day22.dto.UpdateProductDTO;
import com.example.assigment_day22.entity.ProductEntity;
import com.example.assigment_day22.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdateProductService {

    private final ProductRepository productRepository;

    public UpdateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity updateStock(Long productId, UpdateProductDTO request) {

        // Cek Ketersediaan Product
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Product with id " + productId + " not found")
                );

        // Validasi nilai awal stock
        if (product.getStock() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock is already zero"
            );
        }

        // Validasi Product Sold
        if (request.getSold() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity is required"
            );
        }

        if (request.getSold() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity must be greater than 0"
            );
        }

        // Validasi hasil nilai awal stock - product sold
        int remainingStock = product.getStock() - request.getSold();

        if (remainingStock < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock is not enough"
            );
        }

        product.setStock(remainingStock);

        return productRepository.save(product);
    }
}
