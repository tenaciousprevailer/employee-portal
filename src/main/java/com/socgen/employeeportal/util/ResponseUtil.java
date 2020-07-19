package com.socgen.employeeportal.util;

/**
 * ResponseUtil
 * <p>
 * class to handle resources
 *
 * @author: Ravi Kumar Soni
 * @authorLinkedProfile: https://www.linkedin.com/in/tenaciousprevailer
 * @authorContact: 8955047966
 * @purpose: Societe Generale Test
 */

import com.socgen.employeeportal.web.rest.errors.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class ResponseUtil {

    private ResponseUtil() {
    }

    public static <T> ResponseEntity<T> wrapOrNotFound(Optional<T> optionalResponse) {
        return wrapOrNotFound(optionalResponse, (HttpHeaders) null);
    }

    public static <X> ResponseEntity<X> wrapOrNotFound(
            Optional<X> optionalResponse, HttpHeaders header) {

        return (ResponseEntity) optionalResponse.map((response) -> {
            return ((ResponseEntity.BodyBuilder) ResponseEntity
                    .ok()
                    .headers(header)).body(response);
        }).orElseThrow(() -> {
            return new ResourceNotFoundException("Resource Not Found");
        });
    }
}
