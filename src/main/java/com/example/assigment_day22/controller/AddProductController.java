package com.example.assigment_day22.controller;

import com.example.assigment_day22.dto.ProductDTO;
import com.example.assigment_day22.entity.ProductEntity;
import com.example.assigment_day22.service.AddProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddProductController {

        private final AddProductService addProductServices;

        public AddProductController(AddProductService addProductServices) {
            this.addProductServices = addProductServices;
        }

        @PostMapping("/addProduct")
        public ResponseEntity<ProductEntity> addProduct(@RequestBody ProductDTO request) {
            ProductEntity product = addProductServices.addProduct(request);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        }
    }



