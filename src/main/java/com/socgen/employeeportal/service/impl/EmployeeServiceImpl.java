package com.socgen.employeeportal.service.impl;

import com.socgen.employeeportal.domain.Employee;
import com.socgen.employeeportal.repository.database.EmployeeRepository;
import com.socgen.employeeportal.repository.search.EmployeeSearchRepository;
import com.socgen.employeeportal.service.EmployeeService;
import com.socgen.employeeportal.service.dto.EmployeeDTO;
import com.socgen.employeeportal.service.mapper.EmployeeMapper;
import com.socgen.employeeportal.util.EmployeePortalConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing {@link Employee}.
 *
 * @author: Ravi Kumar Soni
 * @authorLinkedProfile: https://www.linkedin.com/in/tenaciousprevailer
 * @authorContact: 8955047966
 * @purpose: Societe Generale Test
 */
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeSearchRepository employeeSearchRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               EmployeeMapper employeeMapper,
                               EmployeeSearchRepository employeeSearchRepository) {

        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.employeeSearchRepository = employeeSearchRepository;
    }

    private EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEntity(employeeDTO);
        employee = employeeRepository.save(employee);
        EmployeeDTO result = employeeMapper.toDto(employee);
        employeeSearchRepository.save(employee);
        return result;
    }

    @CachePut(cacheNames = EmployeePortalConstants.EMPLOYEE_BY_ID_CACHE_NAME, key = "#result.id")
    @CacheEvict(cacheNames = {
            EmployeePortalConstants.EMPLOYEES_BY_PAGE_CACHE_NAME,
            EmployeePortalConstants.EMPLOYEES_BY_QUERY_CACHE_NAME
    }, allEntries = true)
    @Override
    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        log.info("Request to create Employee : {}", employeeDTO);
        return save(employeeDTO);
    }

    @CachePut(cacheNames = EmployeePortalConstants.EMPLOYEE_BY_ID_CACHE_NAME, key = "#employeeDTO.id")
    @CacheEvict(cacheNames = {
            EmployeePortalConstants.EMPLOYEES_BY_PAGE_CACHE_NAME,
            EmployeePortalConstants.EMPLOYEES_BY_QUERY_CACHE_NAME
    }, allEntries = true)
    @Override
    public EmployeeDTO update(EmployeeDTO employeeDTO) {
        log.info("Request to update Employee : {}", employeeDTO);
        return save(employeeDTO);
    }

    @Cacheable(cacheNames = EmployeePortalConstants.EMPLOYEES_BY_PAGE_CACHE_NAME)
    @Override
    public Page<EmployeeDTO> findAll(Pageable pageable) {
        log.info("Request to get all Employees");
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return employeeRepository.findAll(pageable)
                .map(employeeMapper::toDto);
    }

    @Cacheable(cacheNames = EmployeePortalConstants.EMPLOYEE_BY_ID_CACHE_NAME)
    @Override
    public Optional<EmployeeDTO> findOne(String id) {
        log.info("Request to get Employee : {}", id);
        return employeeRepository.findById(id)
                .map(employeeMapper::toDto);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = EmployeePortalConstants.EMPLOYEE_BY_ID_CACHE_NAME),
            @CacheEvict(cacheNames = {
                    EmployeePortalConstants.EMPLOYEES_BY_PAGE_CACHE_NAME,
                    EmployeePortalConstants.EMPLOYEES_BY_QUERY_CACHE_NAME
            }, allEntries = true)
    })
    @Override
    public void delete(String id) {
        log.info("Request to delete Employee : {}", id);
        employeeRepository.deleteById(id);
        employeeSearchRepository.deleteById(id);
    }

    @Cacheable(cacheNames = EmployeePortalConstants.EMPLOYEES_BY_QUERY_CACHE_NAME)
    @Override
    public Page<EmployeeDTO> search(String query, Pageable pageable) {
        log.info("Request to search for a page of Employees for query {}", query);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return employeeSearchRepository.search(queryStringQuery(query), pageable)
                .map(employeeMapper::toDto);
    }
}
