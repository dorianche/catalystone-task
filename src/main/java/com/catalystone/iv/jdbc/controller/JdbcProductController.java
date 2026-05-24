package com.catalystone.iv.jdbc.controller;

import com.catalystone.iv.jdbc.model.Product;
import com.catalystone.iv.jdbc.service.JdbcProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class JdbcProductController {

    private final JdbcProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        return ResponseEntity.ok(productService.getById(id));
    }
}

