package com.socgen.employeeportal.web.rest;

import com.socgen.employeeportal.EmployeePortalApplication;
import com.socgen.employeeportal.domain.Employee;
import com.socgen.employeeportal.domain.enumeration.Gender;
import com.socgen.employeeportal.service.dto.EmployeeDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.net.URISyntaxException;
import java.time.Instant;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeePortalApplication.class)
@WebAppConfiguration
@Slf4j
public class EmployeeResourceTest {


    @Autowired
    private EmployeeResource employeeResource;

    @Before
    public void setUp() {
        // some setup
    }

    @Test
    public void when_createNewEmployee() throws URISyntaxException {
        EmployeeDTO employeeDTO = null;
        employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("TestEmployee");
        employeeDTO.setLastName("Hakunama tata");
        employeeDTO.setGender(Gender.MALE);
        employeeDTO.setDepartmentName("Tech");
        employeeDTO.setDob(Instant.ofEpochMilli(806224668L));
        ResponseEntity<EmployeeDTO> savedEmployeeResponse = employeeResource.createEmployee(employeeDTO);
        log.info("savedEmployeeResponse, {}", savedEmployeeResponse);
        assert  savedEmployeeResponse.getStatusCodeValue() == 201;
        assert  savedEmployeeResponse.getBody().getId() != null;
    }

    /**
     * TODO: Add more test cases
     */

}
