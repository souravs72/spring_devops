package io.github.souravs72.department_service.service;


import io.github.souravs72.department_service.entity.Department;
import io.github.souravs72.department_service.model.DepartmentRequest;
import io.github.souravs72.department_service.model.DepartmentResponse;
import io.github.souravs72.department_service.repository.DepartmentRepository;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    DepartmentResponse createDepartment(DepartmentRequest departmentRequest);
    List<DepartmentResponse> getAllDepartments();
    DepartmentResponse getDepartmentById(Integer departmentNumber);
    Map<String, Object> deleteDepartmentById(Integer departmentNumber);
    DepartmentResponse updateDepartmentById(Integer departmentNumber, DepartmentRequest departmentRequest);
}
