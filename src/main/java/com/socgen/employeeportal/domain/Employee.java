package com.socgen.employeeportal.domain;

import com.socgen.employeeportal.domain.enumeration.Gender;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * A Employee.
 *
 * @author: Ravi Kumar Soni
 * @authorLinkedProfile: https://www.linkedin.com/in/tenaciousprevailer
 * @authorContact: 8955047966
 * @purpose: Societe Generale Test
 */
@Document(collection = "employee")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "employee")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    @Field("gender")
    private Gender gender;

    @Field("dob")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date dob;

    @Field("departmentName")
    private String departmentName;

    public Employee firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Employee lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Employee gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public Employee dob(Date dob) {
        this.dob = dob;
        return this;
    }

    public Employee departmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

}
