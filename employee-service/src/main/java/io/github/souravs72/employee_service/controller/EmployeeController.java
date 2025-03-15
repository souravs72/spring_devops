package io.github.souravs72.employee_service.controller;

import io.github.souravs72.employee_service.model.EmployeeCreateRequest;
import io.github.souravs72.employee_service.model.EmployeeResponse;
import io.github.souravs72.employee_service.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Create an employee
    @PostMapping
    public EmployeeResponse createEmployee(@RequestBody @Valid EmployeeCreateRequest employeeCreateRequest) {
        return employeeService.createEmployee(employeeCreateRequest);
    }

    // Get all employees
    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Get all employees
    @GetMapping("/{employeeNumber}")
    public EmployeeResponse getEmployeeById(@PathVariable(value = "employeeNumber") Integer employeeNumber) {
        return employeeService.getEmployeeByEmployeeNumber(employeeNumber);
    }

    // Delete an employee by Employee Number
    @DeleteMapping("/{employeeNumber}")
    public String deleteEmployeeById(@PathVariable(value = "employeeNumber") Integer employeeNumber) {
        return employeeService.deleteEmployeeById(employeeNumber);
    }
}
