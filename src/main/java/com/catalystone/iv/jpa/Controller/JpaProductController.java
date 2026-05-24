package com.catalystone.iv.jpa.Controller;

import com.catalystone.iv.jpa.model.JpaProduct;
import com.catalystone.iv.jpa.service.JpaProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/jpa/product")
public class JpaProductController {

    private final JpaProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<JpaProduct> getProductById(@PathVariable long id) {
        return ResponseEntity.ok(productService.getById(id));
    }
}

