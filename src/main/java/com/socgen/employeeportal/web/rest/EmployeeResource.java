package com.socgen.employeeportal.web.rest;

import com.socgen.employeeportal.service.EmployeeService;
import com.socgen.employeeportal.service.dto.EmployeeDTO;
import com.socgen.employeeportal.util.HeaderUtil;
import com.socgen.employeeportal.util.PaginationUtil;
import com.socgen.employeeportal.util.ResponseUtil;
import com.socgen.employeeportal.web.rest.errors.BadRequestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.socgen.employeeportal.domain.Employee}.
 * <p>
 * Employee Resource
 * Class contains all the exposed apis corresponding to the
 * entity {@link com.socgen.employeeportal.service.dto.EmployeeDTO}
 *
 * @author: Ravi Kumar Soni
 * @authorLinkedProfile: https://www.linkedin.com/in/tenaciousprevailer
 * @authorContact: 8955047966
 * @purpose: Societe Generale Test
 */
@Api(value = "EmployeeResource", description = "Resource to handle Employee related operations")
@RestController
@RequestMapping("")
@Slf4j
public class EmployeeResource {

    private static final String ENTITY_NAME = "employee";

    @Value("${application.name}")
    private String applicationName;

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * {@code POST  /employees} : Create a new employee.
     *
     * @param employeeDTO the employeeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new employeeDTO, or with status {@code 400 (Bad Request)} if the employee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @ApiOperation(value = "Api to create an employee", response = EmployeeDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Employee Created"),
            @ApiResponse(code = 400, message = "Employee Not Created, Bad Request")})
    @PostMapping("/v1/employees")
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) throws URISyntaxException {
        log.info("REST request to save Employee : {}", employeeDTO);
        if (employeeDTO.getId() != null) {
            throw new BadRequestException("A new employee cannot already have an ID");
        }
        EmployeeDTO result = employeeService.create(employeeDTO);
        return ResponseEntity.created(new URI("/api/employees/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
                .body(result);
    }

    /**
     * {@code PUT  /employees} : Updates an existing employee.
     *
     * @param employeeDTO the employeeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated employeeDTO,
     * or with status {@code 400 (Bad Request)} if the employeeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the employeeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @ApiOperation(value = "Api to update an employee details", response = EmployeeDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee Updated"),
            @ApiResponse(code = 400, message = "Employee Not Updated, Bad Request")})
    @PutMapping("/v1/employees")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO) throws URISyntaxException {
        log.info("REST request to update Employee : {}", employeeDTO);
        if (employeeDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        EmployeeDTO result = employeeService.update(employeeDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, employeeDTO.getId()))
                .body(result);
    }

    /**
     * {@code GET  /employees} : get all the employees.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of employees in body.
     */
    @ApiOperation(value = "Api to fetch all employees details")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "List of Employees")})
    @GetMapping("/v1/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(Pageable pageable) {
        log.info("REST request to get a page of Employees");
        Page<EmployeeDTO> page = employeeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /employees/:id} : get the "id" employee.
     *
     * @param id the id of the employeeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the employeeDTO, or with status {@code 404 (Not Found)}.
     */
    @ApiOperation(value = "Api to get a specific employee details", response = EmployeeDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee Found"),
            @ApiResponse(code = 400, message = "Employee Not Found, Resource Not Found")})
    @GetMapping("/v1/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable String id) {
        log.info("REST request to get Employee : {}", id);
        Optional<EmployeeDTO> employeeDTO = employeeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(employeeDTO);
    }

    /**
     * {@code DELETE  /employees/:id} : delete the "id" employee.
     *
     * @param id the id of the employeeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @ApiOperation(value = "Api to delete an employee details", response = EmployeeDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Employee Deleted")})
    @DeleteMapping("/v1/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        log.info("REST request to delete Employee : {}", id);
        employeeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    /**
     * {@code SEARCH  /_search/employees?query=:query} : search for the employee corresponding
     * to the query.
     *
     * @param query    the query of the employee search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @ApiOperation(value = "Api to search for employees")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "List of employees for the query")})
    @GetMapping("/v1/_search/employees")
    public ResponseEntity<List<EmployeeDTO>> searchEmployees(@RequestParam String query, Pageable pageable) {
        log.info("REST request to search for a page of Employees for query {}", query);
        Page<EmployeeDTO> page = employeeService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
