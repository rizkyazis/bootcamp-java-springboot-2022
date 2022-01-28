package com.manusiaikan.bootcamp.repository;


import com.manusiaikan.bootcamp.model.DataTableRequest;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional(readOnly = true)
public class ProductRepo {

    @Autowired
    private NamedParameterJdbcTemplate namedTemplate;

    @Transactional
    public List<Product> list(DataTableRequest request) {
        Map<String,Object> extraParam =  request.getExtraParam();
        String name = (String) extraParam.get("name");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("limit",request.getLength());
        paramMap.put("offset",request.getStart());
        paramMap.put("name",name);

        String sql = "SELECT product_id, name, description, price FROM product"
                +" WHERE 1=1 and name ilike '%'||:name||'%' ORDER BY "
                +(request.getSortCol()+1)+" "+ request.getSortDir() +" "
                + "limit :limit offset  :offset ";

        return this.namedTemplate.query(sql,paramMap, new RowMapper<Product>() {
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
    public Long countProduct(DataTableRequest request){
        Map<String,Object> extraParam =  request.getExtraParam();
        String name = (String) extraParam.get("name");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name",name);
        String sql =  "SELECT COUNT(distinct(product_id)) as total from product " +
                " WHERE 1=1 and name ilike '%'||:name||'%' ";
        return namedTemplate.queryForObject(sql,paramMap,Long.class);
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

