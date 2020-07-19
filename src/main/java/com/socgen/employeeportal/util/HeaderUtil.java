package com.socgen.employeeportal.util;

/**
 * HeaderUtil
 * <p>
 * class to create response headers
 *
 * @author: Ravi Kumar Soni
 * @authorLinkedProfile: https://www.linkedin.com/in/tenaciousprevailer
 * @authorContact: 8955047966
 * @purpose: Societe Generale Test
 */

import org.springframework.http.HttpHeaders;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class HeaderUtil {

    private HeaderUtil() {
    }

    public static HttpHeaders createEntityCreationAlert(
            String applicationName,
            boolean enableTranslation,
            String entityName,
            String param) {

        String message = enableTranslation ?
                applicationName + "." + entityName + ".created" :
                "A new " + entityName + " is created with identifier " + param;
        return createAlert(applicationName, message, param);
    }

    public static HttpHeaders createEntityUpdateAlert(
            String applicationName,
            boolean enableTranslation,
            String entityName,
            String param) {

        String message = enableTranslation ?
                applicationName + "." + entityName + ".updated" :
                "A " + entityName + " is updated with identifier " + param;
        return createAlert(applicationName, message, param);
    }

    public static HttpHeaders createEntityDeletionAlert(
            String applicationName,
            boolean enableTranslation,
            String entityName,
            String param) {

        String message = enableTranslation ?
                applicationName + "." + entityName + ".deleted" :
                "A " + entityName + " is deleted with identifier " + param;
        return createAlert(applicationName, message, param);
    }

    public static HttpHeaders createAlert(String applicationName, String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-" + applicationName + "-alert", message);

        try {
            headers.add("X-" + applicationName + "-params",
                    URLEncoder.encode(param, StandardCharsets.UTF_8.toString()));
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
        }

        return headers;
    }
}
