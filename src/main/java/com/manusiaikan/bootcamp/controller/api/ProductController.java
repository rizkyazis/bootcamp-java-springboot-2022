package com.manusiaikan.bootcamp.controller.api;


import com.manusiaikan.bootcamp.model.Department;
import com.manusiaikan.bootcamp.model.Product;
import com.manusiaikan.bootcamp.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductRepo productRepo;

    @GetMapping("")
    public List<Product> list() {
        return this.productRepo.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Integer id) throws EmptyResultDataAccessException {
        Product product = productRepo.findById(id);
        return ResponseEntity.ok(product);
    }

}
