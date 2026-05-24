package com.catalystone.iv.jdbc.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.catalystone.iv.jdbc.model.CategoryCount;
import com.catalystone.iv.jdbc.model.Product;
import com.catalystone.iv.jdbc.repository.JdbcProductRepository;

import lombok.AllArgsConstructor;

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

    public Map<String, Integer> getCategoryCount() {
    return productRepo.categoryCount()
        .stream()
        .collect(Collectors.toMap(
            CategoryCount::getCategory,
            CategoryCount::getCount,
            (a, b) -> a,
            LinkedHashMap::new
        ));
    }

    public Product create(Product product) {
        return productRepo.create(product);
    }

    public List<Product> getProductsByName(String name) {
        return productRepo.getProductsByName(name);
    }
    
}
