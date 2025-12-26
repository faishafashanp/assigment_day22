package com.example.assigment_day22.controller;

import com.example.assigment_day22.dto.ProductDTO;
import com.example.assigment_day22.entity.ProductEntity;
import com.example.assigment_day22.service.EditProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EditProductController {

    private final EditProductService editProductService;

    public EditProductController(EditProductService editProductServices) {
        this.editProductService = editProductServices;
    }

    @PutMapping("/editProduct/{id}")
    public ResponseEntity<ProductEntity> editProduct(
            @PathVariable Long id,
            @RequestBody ProductDTO request
    ) {
        ProductEntity updatedProduct = editProductService.editProduct(id, request);
        return ResponseEntity.ok(updatedProduct);
    }
}
