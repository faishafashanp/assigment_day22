package com.example.assigment_day22.controller;

import com.example.assigment_day22.dto.UpdateProductDTO;
import com.example.assigment_day22.entity.ProductEntity;
import com.example.assigment_day22.service.UpdateProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateProductController {

    private final UpdateProductService updateProductService;

    public UpdateProductController(UpdateProductService updateProductService) {
        this.updateProductService = updateProductService;
    }

    @PatchMapping("/updateProduct/{id}")
    public ResponseEntity<ProductEntity> updateStock(
            @PathVariable Long id,
            @RequestBody UpdateProductDTO request
    ) {
        ProductEntity updatedProduct = updateProductService.updateStock(id, request);
        return ResponseEntity.ok(updatedProduct);
    }
}
