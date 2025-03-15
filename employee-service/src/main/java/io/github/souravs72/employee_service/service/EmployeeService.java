package io.github.souravs72.employee_service.service;

import io.github.souravs72.employee_service.model.EmployeeCreateRequest;
import io.github.souravs72.employee_service.model.EmployeeResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse createEmployee(@Valid EmployeeCreateRequest employeeCreateRequest);
    List<EmployeeResponse> getAllEmployees();
    EmployeeResponse getEmployeeByEmployeeNumber(Integer employeeNumber);

    String deleteEmployeeById(Integer employeeNumber);
}
