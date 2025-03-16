package io.github.souravs72.department_service.controller;

import io.github.souravs72.department_service.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/department")
@AllArgsConstructor
public class DepartmentController {
    public DepartmentService departmentService;




}
