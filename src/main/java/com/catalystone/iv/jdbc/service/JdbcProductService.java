package com.catalystone.iv.jdbc.service;

import com.catalystone.iv.jdbc.model.Product;
import com.catalystone.iv.jdbc.repository.JdbcProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JdbcProductService {

    private final JdbcProductRepository productRepo;

    public Product getById(long id) {
        return productRepo.byId(id);
    }

    public List<Product> getAllProducts() {
        return productRepo.allProducts();
    }
}
