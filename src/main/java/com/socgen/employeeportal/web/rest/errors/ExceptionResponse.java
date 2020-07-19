package com.socgen.employeeportal.web.rest.errors;

/**
 * ExceptionResponse Structure Class
 *
 * @author: Ravi Kumar Soni
 * @authorLinkedProfile: https://www.linkedin.com/in/tenaciousprevailer
 * @authorContact: 8955047966
 * @purpose: Societe Generale Test
 */

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExceptionResponse {
    private String message;
    private String details;
    private Instant timestamp;
}
