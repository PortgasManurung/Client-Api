package com.coba.client.controller;

import com.coba.client.models.Employee;
import com.coba.client.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("employees",employeeService.getAlll());
        return "employee/view";
    }

    @GetMapping("/add")
    public String showForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "employee/form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.create(employee);
        return "redirect:/employee";
    }

}
