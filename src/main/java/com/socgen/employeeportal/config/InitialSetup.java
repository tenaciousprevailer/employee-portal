package com.socgen.employeeportal.config;

/**
 * Initial Setup Class
 *
 * The purpose of this class is just to have few employees ready for the
 * application testing
 *
 * Note: Spring profile: dev / local / test
 *
 * @author: Ravi Kumar Soni
 * @authorLinkedProfile: https://www.linkedin.com/in/tenaciousprevailer
 * @authorContact: 8955047966
 * @purpose: Societe Generale Test
 */

import com.socgen.employeeportal.domain.enumeration.Gender;
import com.socgen.employeeportal.service.dto.EmployeeDTO;
import com.socgen.employeeportal.web.rest.EmployeeResource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import java.net.URISyntaxException;
import java.time.Instant;

@Controller
@Profile({"dev", "local", "test"})
public class InitialSetup implements InitializingBean {

    private final EmployeeResource employeeResource;

    public InitialSetup(EmployeeResource employeeResource) {
        this.employeeResource = employeeResource;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        createEmployees();
    }

    private void createEmployees() throws URISyntaxException {
        EmployeeDTO employeeDTO = null;
        employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("Ravi Kumar");
        employeeDTO.setLastName("Soni");
        employeeDTO.setGender(Gender.MALE);
        employeeDTO.setDepartmentName("Tech");
        employeeDTO.setDob(Instant.ofEpochMilli(806224668L));
        employeeResource.createEmployee(employeeDTO);

        employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("Rachel");
        employeeDTO.setLastName("Henry");
        employeeDTO.setGender(Gender.FEMALE);
        employeeDTO.setDepartmentName("Audit");
        employeeDTO.setDob(Instant.ofEpochMilli(806224668L));
        employeeResource.createEmployee(employeeDTO);

        employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("Monica");
        employeeDTO.setLastName("Michael");
        employeeDTO.setGender(Gender.FEMALE);
        employeeDTO.setDepartmentName("Audit");
        employeeDTO.setDob(Instant.ofEpochMilli(806224668L));
        employeeResource.createEmployee(employeeDTO);

        employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("Ross");
        employeeDTO.setLastName("Felange");
        employeeDTO.setGender(Gender.MALE);
        employeeDTO.setDepartmentName("Arch");
        employeeDTO.setDob(Instant.ofEpochMilli(806224668L));
        employeeResource.createEmployee(employeeDTO);

    }
}
