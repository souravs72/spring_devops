package io.github.souravs72.department_service.repository;


import io.github.souravs72.department_service.entity.Department;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Optional<Department> findByDepartmentName(String departmentName);
}
