package com.coba.client.controller;
import com.coba.client.models.Department;
import com.coba.client.models.Employee;
import com.coba.client.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("department",departmentService.getAlll());
        return "department/view";
    }

    @GetMapping("/add")
    public String showForm(Model model){
        Department department = new Department();
        model.addAttribute("department",department);
        return "department/form-department";
    }

    @PostMapping("/save")
    public String saveDeparment(@ModelAttribute("department") Department department){
        departmentService.create(department);
        return "redirect:/department";
    }
}
