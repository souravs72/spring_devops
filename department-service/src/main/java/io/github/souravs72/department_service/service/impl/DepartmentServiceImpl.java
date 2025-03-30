package io.github.souravs72.department_service.service.impl;

import io.github.souravs72.department_service.entity.Department;
import io.github.souravs72.department_service.model.DepartmentRequest;
import io.github.souravs72.department_service.model.DepartmentResponse;
import io.github.souravs72.department_service.repository.DepartmentRepository;
import io.github.souravs72.department_service.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    public final DepartmentRepository departmentRepository;
    public final ModelMapper modelMapper;

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        Department department = modelMapper.map(departmentRequest, Department.class);
        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentResponse.class);
    }

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        List<Department> getAllDepartments = departmentRepository.findAll();
        return getAllDepartments.stream()
                .map(department -> modelMapper.map(department, DepartmentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponse getDepartmentById(Integer departmentNumber) {
        Department department = departmentRepository.findById(departmentNumber)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        return modelMapper.map(department, DepartmentResponse.class);
    }

    @Override
    public Map<String, Object> deleteDepartmentById(Integer departmentNumber) {
        Map<String, Object> response = new HashMap<>();
        if (!departmentRepository.existsById(departmentNumber)) {
            response.put("message", "Department not found");
        }
        departmentRepository.deleteById(departmentNumber);
        response.put("message", "Department deleted successfully");

        return response;
    }

    @Override
    public DepartmentResponse updateDepartmentById(Integer departmentNumber, DepartmentRequest departmentRequest) {
        Department department = departmentRepository.findById(departmentNumber)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        boolean departmentExists = departmentRepository.findByDepartmentName(departmentRequest.getDepartmentName())
                .isPresent();
        if (departmentExists) {
            throw new RuntimeException("Department name already exists");
        }

        department.setDepartmentName(departmentRequest.getDepartmentName());
        Department updatedDepartment = departmentRepository.save(department);
        return modelMapper.map(updatedDepartment, DepartmentResponse.class);
    }

}
