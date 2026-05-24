package com.catalystone.iv.jdbc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.catalystone.iv.jdbc.model.Product;
import com.catalystone.iv.jdbc.service.JdbcProductService;

import lombok.AllArgsConstructor;



@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class JdbcProductController {

    private final JdbcProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("/category-count")
    public ResponseEntity<Map<String, Integer>> getCategoryCount() {
        return ResponseEntity.ok(productService.getCategoryCount());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.create(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProductByName(@RequestParam String name) {
        return ResponseEntity.ok(productService.getProductByName(name));
    }
    
    

}

