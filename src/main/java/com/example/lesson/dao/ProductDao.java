package com.example.lesson.dao;
import com.example.lesson.entity.Product;
import java.util.List;

public interface ProductDao {
    List<Product> findAll();
    Product findById(int id);
    int insert(Product product) throws RuntimeException;
    int update(Product product);
    int delete(int id);
    List<Product> findByName(String find);
}
