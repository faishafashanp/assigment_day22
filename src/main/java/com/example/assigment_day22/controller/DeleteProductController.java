package com.example.assigment_day22.controller;

import com.example.assigment_day22.service.DeleteProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteProductController {

    private final DeleteProductService deleteProductService;

    public DeleteProductController(DeleteProductService deleteProductService) {
        this.deleteProductService = deleteProductService;
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {

        deleteProductService.deleteProduct(id);

        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }
}

