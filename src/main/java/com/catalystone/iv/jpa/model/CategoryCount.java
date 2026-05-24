package com.catalystone.iv.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CategoryCount {
    String category;
    long count;
}