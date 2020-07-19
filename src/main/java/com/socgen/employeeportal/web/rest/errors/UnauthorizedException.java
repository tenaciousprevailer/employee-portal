package com.socgen.employeeportal.web.rest.errors;

/**
 * UnauthorizedException Class
 * The exception class should be used when user is not authorized
 * to access the resource
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

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends EmployeePortalException {
    public UnauthorizedException(String messgae) {
        super(messgae);
    }
}
