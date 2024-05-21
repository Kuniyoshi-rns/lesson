package com.example.lesson.service;

import com.example.lesson.Exception.ProductNotFoundException;
import com.example.lesson.dao.PgProductDao;
import com.example.lesson.dao.ProductDao;
import com.example.lesson.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class PgProductService implements ProductService{
    public final ProductDao productDao;

    @Autowired
    PgProductService(ProductDao productDao){
        this.productDao = productDao;
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product findById(int id) {
        var product = productDao.findById(id);
        if(product == null){
            throw new ProductNotFoundException();
        }else{
            return product;
        }
    }

    @Override
    public int insert(Product product) {
        var num = 0;
        try {
            num = productDao.insert(product);
        }catch (RuntimeException e){
            System.out.println("idが被っています");
        }
        return num;
    }

    @Override
    public int update(Product product) {
        return productDao.update(product);
    }

    @Override
    public int delete(int id) {
        return productDao.delete(id);
    }

    @Override
    @Transactional
    public int bulkInsert(List<Product> list) {
        list.forEach(productDao::insert);
        return list.size();
    }

    @Override
    public List<Product> findByName(String find) {
        return productDao.findByName(find);
    }
}
