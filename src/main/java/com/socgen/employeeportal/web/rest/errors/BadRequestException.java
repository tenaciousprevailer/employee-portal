package com.socgen.employeeportal.web.rest.errors;

/**
 * BadRequestException Class
 * The exception class should be used when the input for any API
 * is not valid.
 * <p>
 * Note: The exception message will be used in response
 * e.g. Update request must have Id
 *
 * @author: Ravi Kumar Soni
 * @authorLinkedProfile: https://www.linkedin.com/in/tenaciousprevailer
 * @authorContact: 8955047966
 * @purpose: Societe Generale Test
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends EmployeePortalException {
    public BadRequestException(String messgae) {
        super(messgae);
    }
}
