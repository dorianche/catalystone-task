package com.catalystone.iv.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryCount {
    String category;
    int count;
}
