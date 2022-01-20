package com.manusiaikan.bootcamp.controller.api;


import com.manusiaikan.bootcamp.model.Department;
import com.manusiaikan.bootcamp.repository.DepartmentJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentJdbc departmentJdbc;

    @GetMapping("")
    public List<Department> list(){
        return this.departmentJdbc.getListNamedJdbcTemplate();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable Integer id){
        try {
            Department dept = departmentJdbc.findDepartmentById(id);
            return ResponseEntity.ok(dept);
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Department dept){
        dept = this.departmentJdbc.insertDepartment(dept);
        if(dept.getId() == null){
            return ResponseEntity.internalServerError().body("Error");
        }else {
            return ResponseEntity.ok(dept);
        }
    }


}
