package com.manusiaikan.bootcamp.controller.api;

import com.manusiaikan.bootcamp.model.Category;
import com.manusiaikan.bootcamp.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryRepo categoryRepo;

    @GetMapping("")
    public List<Category> list() {
        return this.categoryRepo.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Integer id) throws EmptyResultDataAccessException {
        Category category = this.categoryRepo.findById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Category category, BindingResult result) {
        Map<String, Object> hasil = new HashMap<>();
        try {
            if (result.hasErrors()){
                hasil.put("id", null);
                hasil.put("status",result.getAllErrors());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hasil);
            }else {
                hasil.put("id", categoryRepo.insert(category));
                hasil.put("status","Success added new Category");
                return ResponseEntity.ok(hasil);
            }
        } catch (SQLException e) {
            hasil.put("id", null);
            hasil.put("status",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hasil);
        }

    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Category category, BindingResult result)  {
        Map<String, Object> hasil = new HashMap<>();
        try {
            if (result.hasErrors()){
                hasil.put("id", null);
                hasil.put("status",result.getAllErrors());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hasil);
            }else {
                hasil.put("id", categoryRepo.update(category));
                hasil.put("status","Success updated Category");
                return ResponseEntity.ok(hasil);
            }
        } catch (SQLException e) {
            hasil.put("id", null);
            hasil.put("status",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hasil);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String,Object>> delete(@RequestBody Category category) throws SQLException {
        Map<String, Object> hasil = new HashMap<>();
        try {
            this.categoryRepo.findById(category.getId());
            this.categoryRepo.delete(category.getId());
            hasil.put("status", "Success Deleted Category");
            return ResponseEntity.ok(hasil);
        } catch (EmptyResultDataAccessException e) {
            hasil.put("status", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hasil);
        }
    }

}
