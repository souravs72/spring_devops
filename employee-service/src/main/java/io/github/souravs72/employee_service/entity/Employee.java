package io.github.souravs72.employee_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "tbl_employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    @Id
    @Column(name = "emp_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeNumber;

    @Column(name = "birth_date")
    @Past(message = "Birth Date needs to be in past")
    private Date birthDate;

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;


    @Column(name = "hire_date")
    @PastOrPresent(message = "Hire Date can be in past or present")
    private Date hireDate;


}
