package com.manusiaikan.bootcamp.controller;

import com.manusiaikan.bootcamp.model.Department;
import com.manusiaikan.bootcamp.repository.DepartmentJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/home")
public class HomeAction {
    @Autowired
    DepartmentJdbc departmentJdbc;

    @GetMapping("")
    public String testHome(Model model){
        model.addAttribute("message","This is the message that you got !");
        model.addAttribute("date",new Date());
        model.addAttribute("departments",departmentJdbc.getListNamedJdbcTemplate());
        model.addAttribute("data",new Department());
        return "home";
    }

    @PostMapping("/department/add")
    public String addDepartment(@ModelAttribute Department department){
        this.departmentJdbc.insertDepartment(department);
        return "redirect:/home";
    }

    @PostMapping("/department/update")
    public String updateDepartment(@ModelAttribute Department department){
        this.departmentJdbc.updateDepartment(department);
        return "redirect:/home";
    }

}
