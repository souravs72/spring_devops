package io.github.souravs72.employee_service.service.impl;

import io.github.souravs72.employee_service.entity.Employee;
import io.github.souravs72.employee_service.model.EmployeeCreateRequest;
import io.github.souravs72.employee_service.model.EmployeeResponse;
import io.github.souravs72.employee_service.repository.EmployeeRepository;
import io.github.souravs72.employee_service.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public EmployeeResponse createEmployee(@Valid EmployeeCreateRequest employeeCreateRequest) {
        Employee employee = modelMapper.map(employeeCreateRequest, Employee.class);
        var savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeResponse.class);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll().stream().map(employee -> modelMapper.map(employee, EmployeeResponse.class)).toList();
    }

    @Override
    public EmployeeResponse getEmployeeByEmployeeNumber(Integer employeeNumber) {
        return employeeRepository.findById(employeeNumber).map(employee -> modelMapper.map(employee, EmployeeResponse.class))
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public String deleteEmployeeById(Integer employeeNumber) {
        if(employeeRepository.existsById(employeeNumber)) {
            employeeRepository.deleteById(employeeNumber);
            return "Employee deleted";
        } else {
            throw new RuntimeException("Employee not found");
        }
    }

}
