package io.github.souravs72.employee_service.model;

import io.github.souravs72.employee_service.entity.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponse {

    private Integer employeeNumber;

    @Past(message = "Birth Date needs to be in past")
    private Date birthDate;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    private Gender gender;
    @PastOrPresent(message = "Birth Date needs to be in past or present")
    private Date hireDate;
}
