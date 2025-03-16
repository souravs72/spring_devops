package io.github.souravs72.department_service.service.impl;

import io.github.souravs72.department_service.entity.Department;
import io.github.souravs72.department_service.repository.DepartmentRepository;
import io.github.souravs72.department_service.service.DepartmentService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    public final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    };
}
