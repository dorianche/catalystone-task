package com.catalystone.iv.jpa.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.catalystone.iv.jpa.model.JpaProduct;
import com.catalystone.iv.jpa.service.JpaProductService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/jpa/product")
public class JpaProductController {

    private final JpaProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<JpaProduct> getProductById(@PathVariable long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("/category-count")
    public ResponseEntity<Map<String, Integer>> getCategoryCount() {
        return ResponseEntity.ok(productService.getCategoryCount());
    }

    @PostMapping
    public ResponseEntity<JpaProduct> createProduct(JpaProduct product) {
        return ResponseEntity.ok(productService.create(product));
    }

    @GetMapping
    public ResponseEntity<List<JpaProduct>> getProductWithName(@RequestParam String name) {
        return ResponseEntity.ok(productService.getProductsWithName(name));
    }
}

