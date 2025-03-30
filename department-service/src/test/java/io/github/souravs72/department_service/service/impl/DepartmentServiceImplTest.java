package io.github.souravs72.department_service.service.impl;

import io.github.souravs72.department_service.entity.Department;
import io.github.souravs72.department_service.model.DepartmentRequest;
import io.github.souravs72.department_service.model.DepartmentResponse;
import io.github.souravs72.department_service.repository.DepartmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper = new ModelMapper();

    private DepartmentServiceImpl departmentServiceImpl;


    @BeforeEach
    void setUp() {
        departmentServiceImpl = new DepartmentServiceImpl(departmentRepository, modelMapper);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testCreateDepartment() {
        DepartmentRequest departmentRequest = DepartmentRequest.builder().departmentName("Computer Science").departmentAddress("Lake Town").build();
        when(departmentRepository.save(any(Department.class))).thenReturn(Department.builder().departmentName("Computer Science").departmentAddress("Lake Town").departmentNumber(1).build());

        DepartmentResponse departmentResponse = departmentServiceImpl.createDepartment(departmentRequest);
        DepartmentResponse expectedDepartmentResponse = DepartmentResponse.builder().departmentName("Computer Science").departmentNumber(1).departmentAddress("Lake Town").build();
        assertThat(departmentResponse.getDepartmentNumber()).isNotNull();
        assertThat(departmentResponse.getDepartmentName()).isEqualTo(expectedDepartmentResponse.getDepartmentName());
        assertThat(departmentResponse.getDepartmentAddress()).isEqualTo(expectedDepartmentResponse.getDepartmentAddress());
        assertThat(departmentResponse.getDepartmentNumber()).isEqualTo(1);
        assertThat(departmentResponse.getDepartmentAddress()).isEqualTo(expectedDepartmentResponse.getDepartmentAddress());
        assertThat(departmentResponse.getDepartmentAddress()).isEqualTo(expectedDepartmentResponse.getDepartmentAddress());

    }

    @Test
    void getAllDepartments() {
        departmentServiceImpl.getAllDepartments();
        verify(departmentRepository).findAll();
    }

    @Test
    void testGetDepartmentNumberShouldHaveValueWhenFound() {
        Integer departmentNumber = 1;
        Department department = Department.builder().departmentName("Computer Science").departmentNumber(departmentNumber).departmentAddress("Lake Town").build();
        when(departmentRepository.findById(anyInt())).thenReturn(Optional.of(department));

        DepartmentResponse departmentResponse = departmentServiceImpl.getDepartmentById(departmentNumber);
        assertThat(departmentResponse.getDepartmentNumber()).isEqualTo(departmentNumber);
        assertThat(departmentResponse.getDepartmentName()).isEqualTo(department.getDepartmentName());
        assertThat(departmentResponse.getDepartmentAddress()).isEqualTo(department.getDepartmentAddress());
    }

    @Test
    void testGetDepartmentNumberShouldThrowExceptionWhenDepartmentNotFound() {
        Integer departmentNumber = 1;
        when(departmentRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> departmentServiceImpl.getDepartmentById(departmentNumber)).isInstanceOf(RuntimeException.class).hasMessage("Department not found");

    }

    @Test
    void testDeleteDepartmentById() {
        Integer departmentNumber = 1;
        Map<String, Object> deleteDepartment = departmentServiceImpl.deleteDepartmentById(departmentNumber);
        verify(departmentRepository).deleteById(departmentNumber);
        assertThat(deleteDepartment).containsKey("message");
        assertThat(deleteDepartment).containsValue("Department deleted successfully");
    }

    @Test
    void testUpdateDepartmentById() {
        Integer departmentNumber = 1;
        DepartmentRequest departmentRequest = DepartmentRequest.builder()
                .departmentName("Information Technology")
                .departmentAddress("Salt Lake")
                .build();
        Department department = Department.builder()
                .departmentName("Computer Science")
                .departmentNumber(departmentNumber)
                .departmentAddress("Salt Lake")
                .build();

        when(departmentRepository.findById(eq(departmentNumber))).thenReturn(Optional.of(department));
        when(departmentRepository
                .findByDepartmentName(eq(departmentRequest.getDepartmentName())))
                .thenReturn(Optional.empty());
        when(departmentRepository.save(any(Department.class)))
                .thenReturn(Department
                        .builder()
                        .departmentName("Information Technology")
                        .departmentAddress("Salt Lake")
                        .departmentNumber(1)
                        .build()
                );


        DepartmentResponse departmentResponse = departmentServiceImpl
                .updateDepartmentById(departmentNumber, departmentRequest);

        assertThat(departmentResponse.getDepartmentNumber()).isEqualTo(departmentNumber);
        assertThat(departmentResponse.getDepartmentName()).isEqualTo(departmentRequest.getDepartmentName());
    }

    @Test
    void testUpdateDepartmentNameWhenNameAlreadyExists() {
        Integer departmentNumber = 1;
        String existingDepartmentName = "Information Technology";

        // Department request to be updated
        DepartmentRequest departmentRequest = DepartmentRequest.builder()
                .departmentName(existingDepartmentName)
                .departmentAddress("Salt Lake")
                .build();

        Department existingDepartment = Department.builder()
                .departmentName(existingDepartmentName)
                .departmentNumber(departmentNumber)
                .departmentAddress("Salt Lake")
                .build();


        when(departmentRepository.findById(eq(departmentNumber))).thenReturn(Optional.of(existingDepartment));
        when(departmentRepository.findByDepartmentName(eq(departmentRequest.getDepartmentName())))
                .thenReturn(Optional.of(existingDepartment));

        // Expecting a RuntimeException with the message "Department name already exists"
        assertThatThrownBy(() -> departmentServiceImpl
                .updateDepartmentById(departmentNumber, departmentRequest))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Department name already exists");

        // Verifying that save was never called since the department name already exists
        verify(departmentRepository, never()).save(any());
    }

    @Test
    void testUpdateDepartmentWhenDepartmentDoesNotExist() {
        Integer departmentNumber = 1;
        String departmentName = "Information Technology";

        DepartmentRequest departmentRequest = DepartmentRequest.builder()
                .departmentName(departmentName)
                .departmentAddress("Salt Lake")
                .build();


        when(departmentRepository.findById(eq(departmentNumber))).thenReturn(Optional.empty());

        // Expecting a RuntimeException with the message "Department name already exists"
        assertThatThrownBy(() -> departmentServiceImpl
                .updateDepartmentById(departmentNumber, departmentRequest))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Department not found");

        // Verifying that save was never called since the department name already exists
        verify(departmentRepository, never()).save(any());
        verify(departmentRepository, never()).findByDepartmentName(anyString());
    }
}