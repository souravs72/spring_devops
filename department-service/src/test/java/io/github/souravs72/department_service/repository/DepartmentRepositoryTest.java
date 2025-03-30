package io.github.souravs72.department_service.repository;

import io.github.souravs72.department_service.entity.Department;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:latest"));



    @Test
    void canEstablishConnection() {
        assertThat(MySQLContainer.class).isNotNull();
        assertThat(mySQLContainer.getDatabaseName()).isNotNull();
        assertThat(mySQLContainer.getUsername()).isNotNull();
        assertThat(mySQLContainer.getPassword()).isNotNull();
        assertThat(mySQLContainer.isCreated()).isTrue();
        assertThat(mySQLContainer.isRunning()).isTrue();
    }

    @BeforeEach
    void setUp() {
        Department department = Department.builder().departmentName("Computer Science").build();
        departmentRepository.save(department);
    }

    @AfterEach
    void tearDown() {
        departmentRepository.deleteAll();
    }

    @Test
    void shouldFindByDepartmentName() {
        Optional<Department> department = departmentRepository.findByDepartmentName("Computer Science");
        assertThat(departmentRepository.findAll()).hasSize(1);
        assertThat(department).isPresent();
    }

    @Test
    void shouldNotFindByDepartmentName() {
        assertThat(departmentRepository.findByDepartmentName("Information Science")).isNotPresent();
    }
}