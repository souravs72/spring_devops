package io.github.souravs72.department_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_departments",
        indexes = {@Index(columnList = "dept_name", name = "IDX_TBL_DEPARTMENTS_DEPT_NAME")},
        uniqueConstraints = {@UniqueConstraint(columnNames = "dept_name", name = "UNIQUE_TBL_DEPARTMENTS_DEPT_NAME")}
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @Column(name = "dept_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departmentNumber;

    @Column(name = "dept_name")
    @NotBlank
    private String departmentName;

    @Column(name = "dept_address")
    private String departmentAddress;
}
