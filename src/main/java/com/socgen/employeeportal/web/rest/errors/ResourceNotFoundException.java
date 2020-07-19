package com.socgen.employeeportal.web.rest.errors;

/**
 * ResourceNotFoundException Class
 * The exception class should be used when resource is not found
 * <p>
 * Note: The exception message will be used in response
 *
 * @author: Ravi Kumar Soni
 * @authorLinkedProfile: https://www.linkedin.com/in/tenaciousprevailer
 * @authorContact: 8955047966
 * @purpose: Societe Generale Test
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends EmployeePortalException {
    public ResourceNotFoundException(String messgae) {
        super(messgae);
    }
}
