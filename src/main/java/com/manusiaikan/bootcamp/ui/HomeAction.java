package com.manusiaikan.bootcamp.ui;

import com.manusiaikan.bootcamp.repository.DepartmentJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeAction {
    @Autowired
    DepartmentJdbc departmentJdbc;

    @GetMapping("/home")
    public String testHome(Model model){
        model.addAttribute("message","This is the message that you got !");
        model.addAttribute("departments",departmentJdbc.getListDepartmentPS());
        return "home";
    }

}
