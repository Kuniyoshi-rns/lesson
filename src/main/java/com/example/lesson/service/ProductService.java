package com.example.lesson.service;

import com.example.lesson.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(int id);
    int insert(Product product);
    int update(Product product);
    int delete(int id);

    int bulkInsert(List<Product> list);

    List<Product> findByName(String find);
}
