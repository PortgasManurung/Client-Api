package com.coba.client.controller;
import com.coba.client.models.Department;
import com.coba.client.models.Employee;
import com.coba.client.models.ResponseMessage;
import com.coba.client.models.ResponseSingle;
import com.coba.client.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("department", departmentService.getById(id));
        return "/department/view-id";
    }

    @GetMapping("/add")
    public String showForm(Model model){
        Department department = new Department();
        model.addAttribute("department",department);
        return "department/form-department";
    }

    @GetMapping("/formUpdate")
    public String showFormUpdate(@RequestParam("departmentId")Long departmentId,
                                 Model model){
        Department departmentx = new Department();
        model.addAttribute(departmentx);
        ResponseSingle<Department> department = departmentService.getById(departmentId);
        System.out.println(department);
        model.addAttribute("department",department);
        return "department/update-form";
    }

    @PostMapping("/save")
    public String saveDeparment(@ModelAttribute("department") Department department){
        System.out.println(department);
        departmentService.create(department);
        return "redirect:/department";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id){
        departmentService.del(id);
        return "redirect:/department";
    }
}
