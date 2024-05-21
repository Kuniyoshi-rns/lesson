package com.example.lesson.dao;

import com.example.lesson.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class PgProductDao implements ProductDao{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM products ORDER BY id",
                new DataClassRowMapper<>(Product.class));
    }

    @Override
    public Product findById(int id) {
        var param = new MapSqlParameterSource();
        param.addValue("id",id);
        var list = jdbcTemplate.query("SELECT * FROM products where id = :id",param,
                        new DataClassRowMapper<>(Product.class));
        return list.isEmpty()?null:list.get(0);
    }

    @Override
    public int insert(Product product) throws RuntimeException{
        var param = new MapSqlParameterSource();
        param.addValue("name",product.name());
        param.addValue("price",product.price());

        return jdbcTemplate.update("INSERT INTO products (name,price) VALUES(:name,:price)",param);
    }

    @Override
    public int update(Product product) {
        var param = new MapSqlParameterSource();
        param.addValue("id",product.id());
        param.addValue("name",product.name());
        param.addValue("price",product.price());

        return jdbcTemplate.update("UPDATE products SET name=:name, price=:price WHERE id = :id",param);
    }

    @Override
    public int delete(int id) {
        var param = new MapSqlParameterSource();
        param.addValue("id",id);

        return jdbcTemplate.update("DELETE FROM products WHERE id = :id",param);
    }

    @Override
    public List<Product> findByName(String find) {
        var param = new MapSqlParameterSource();
        param.addValue("find",find);
        param.addValue("likeFind","%"+find+"%");
        return jdbcTemplate.query("SELECT * FROM products WHERE name LIKE :likeFind ORDER BY name = :find DESC",param,
                new DataClassRowMapper<>(Product.class));
    }
}
