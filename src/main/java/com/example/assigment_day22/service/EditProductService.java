package com.example.assigment_day22.service;

import com.example.assigment_day22.dto.ProductDTO;
import com.example.assigment_day22.entity.ProductEntity;
import com.example.assigment_day22.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EditProductService {

    private final ProductRepository productRepository;

    public EditProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity editProduct(Long id, ProductDTO request) {

        // Cek Ketersediaan Product
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id " + id + " not found")
                );

        // Validasi Wajib Isi
        if (request.getName() == null || request.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is required");
        }

        if (request.getPrice() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price is required");
        }

        if (request.getStock() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock is required");
        }

        // Validasi Nilai
        if (request.getPrice() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price must be greater than 0");
        }

        if (request.getStock() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock must be greater than or equal to 0"
            );
        }

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setStock(request.getStock());

        return productRepository.save(product);
    }
}
