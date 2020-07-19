package com.socgen.employeeportal.web.rest.errors;

/**
 * EmployeePortalException Class
 * The exception class should be used when there's an internal server error.
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

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmployeePortalException extends RuntimeException {
    public EmployeePortalException(String messgae) {
        super(messgae);
    }
}
