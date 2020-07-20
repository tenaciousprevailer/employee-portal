package com.socgen.employeeportal.service;

import com.socgen.employeeportal.service.dto.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * EmployeeService
 * <p>
 * Employee Service Interface
 *
 * @author: Ravi Kumar Soni
 * @authorLinkedProfile: https://www.linkedin.com/in/tenaciousprevailer
 * @authorContact: 8955047966
 * @purpose: Societe Generale Test
 */
public interface EmployeeService {

    /**
     * Create an employee.
     *
     * @param employeeDTO the entity to save.
     * @return the persisted entity.
     */
    EmployeeDTO create(EmployeeDTO employeeDTO);

    /**
     * Update an employee.
     *
     * @param employeeDTO the entity to save.
     * @return the persisted entity.
     */
    EmployeeDTO update(EmployeeDTO employeeDTO);

    /**
     * Get all the employees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EmployeeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" employee.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EmployeeDTO> findOne(String id);

    /**
     * Delete the "id" employee.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    /**
     * Search for the employee corresponding to the query.
     *
     * @param query    the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EmployeeDTO> search(String query, Pageable pageable);
}
