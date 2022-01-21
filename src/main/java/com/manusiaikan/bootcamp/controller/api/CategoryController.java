package com.manusiaikan.bootcamp.controller.api;

import com.manusiaikan.bootcamp.model.Category;
import com.manusiaikan.bootcamp.model.Department;
import com.manusiaikan.bootcamp.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryRepo categoryRepo;

    @GetMapping("")
    public List<Category> list(){
        return this.categoryRepo.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Integer id){
        try {
            Category category = this.categoryRepo.findById(id);
            return ResponseEntity.ok(category);
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Category category) throws SQLException {
        category = this.categoryRepo.insert(category);
            return ResponseEntity.ok(category);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Category category) throws SQLException {

        category = this.categoryRepo.update(category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) throws SQLException{
        this.categoryRepo.delete(id);
        return ResponseEntity.ok("Delete Success");
    }

}
