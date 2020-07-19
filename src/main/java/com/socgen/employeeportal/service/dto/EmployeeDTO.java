package com.socgen.employeeportal.service.dto;

import com.socgen.employeeportal.domain.enumeration.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.socgen.employeeportal.domain.Employee} entity.
 *
 * @author: Ravi Kumar Soni
 * @authorLinkedProfile: https://www.linkedin.com/in/tenaciousprevailer
 * @authorContact: 8955047966
 * @purpose: Societe Generale Test
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@ApiModel("Employee DTO class object")
public class EmployeeDTO implements Serializable {

    @ApiModelProperty("Employee ID")
    private String id;

    @ApiModelProperty("Employee First Name")
    @NotNull(message = "Employee first name cannot be null")
    @Size(min = 3, max = 20, message = "Employee first name should be atleast 3 character and atlax 20 characters long")
    private String firstName;

    @NotNull(message = "Employee last name cannot be null")
    @Size(min = 3, max = 20, message = "Employee last name should be atleast 3 character and atlax 20 characters long")
    @ApiModelProperty("Employee Last Name")
    private String lastName;

    @ApiModelProperty("Employee Gender")
    private Gender gender;

    @Past(message = "Employee Date of birth should be in Past")
    @ApiModelProperty("Employee Date of Birth")
    private Instant dob;

    @NotNull(message = "Employee must have a department")
    @ApiModelProperty("Employee Department Name")
    private String departmentName;
}
