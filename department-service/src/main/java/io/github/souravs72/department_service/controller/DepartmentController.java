package io.github.souravs72.department_service.controller;

import io.github.souravs72.department_service.entity.Department;
import io.github.souravs72.department_service.model.DepartmentRequest;
import io.github.souravs72.department_service.model.DepartmentResponse;
import io.github.souravs72.department_service.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/department")
@AllArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    // Create department
    @PostMapping
    public DepartmentResponse createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        return departmentService.createDepartment(departmentRequest);
    }

    // Get all Departments
    @GetMapping
    public List<DepartmentResponse> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // Get Department by Department Number
    @GetMapping("/{departmentNumber}")
    public DepartmentResponse getDepartment(@PathVariable Integer departmentNumber) {
        return departmentService.getDepartmentById(departmentNumber);
    }

    // Delete Department by Department Number
    @DeleteMapping("/{departmentNumber}")
    public Map<String, Object> deleteDepartment(@PathVariable Integer departmentNumber) {
        return departmentService.deleteDepartmentById(departmentNumber);
    }

    // Update Department by Department Number
    @PutMapping("/{departmentNumber}")
    public DepartmentResponse updateDepartment(@PathVariable Integer departmentNumber, @RequestBody DepartmentRequest departmentRequest) {
        return departmentService.updateDepartmentById(departmentNumber, departmentRequest);
    }
}
