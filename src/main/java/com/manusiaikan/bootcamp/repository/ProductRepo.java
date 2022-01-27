package com.manusiaikan.bootcamp.repository;


import com.manusiaikan.bootcamp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ProductRepo {

    @Autowired
    private NamedParameterJdbcTemplate namedTemplate;

    @Transactional
    public List<Product> list() {
        return this.namedTemplate.query(
                "SELECT product_id, name, description, price FROM product ORDER BY product_id", new RowMapper<Product>() {
                    @Override
                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Product product = new Product();
                        product.setId(rs.getInt("product_id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getDouble("price"));
                        return product;
                    }
                }
        );
    }

    @Transactional
    public Product findById(Integer id) throws EmptyResultDataAccessException{
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        String sql = "SELECT product_id, name, description, price FROM product WHERE product_id=:id ORDER BY product_id";

        return this.namedTemplate.queryForObject(sql, map, new RowMapper<Product>() {
                    @Override
                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Product product = new Product();
                        product.setId(rs.getInt("product_id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getDouble("price"));
                        return product;
                    }
                }
        );
    }
}

