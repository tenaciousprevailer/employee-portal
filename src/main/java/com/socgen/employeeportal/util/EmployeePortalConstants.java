package com.socgen.employeeportal.util;

/**
 * EmployeePortalConstants
 *
 * @author: Ravi Kumar Soni
 * @authorLinkedProfile: https://www.linkedin.com/in/tenaciousprevailer
 * @authorContact: 8955047966
 * @purpose: Societe Generale Test
 */
public final class EmployeePortalConstants {
    private EmployeePortalConstants() {
    }

    public static final String ES_HOST_AND_PORT = "localhost:9200";
    public static final String REDIS_ADDRESS = "redis://127.0.0.1:6379";
    public static final int CACHE_TTL = 24 * 1000;
    public static final String EMPLOYEE_BY_ID_CACHE = "employeeById";
    public static final int CACHE_TTL_IN_SECONDS = 24;
    public static final int CACHE_MAX_IDLE_TIME = 12 * 60 * 1000;
}
