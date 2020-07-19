package com.socgen.employeeportal.repository.search;

import com.socgen.employeeportal.domain.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Employee} entity.
 *
 * @author: Ravi Kumar Soni
 * @authorLinkedProfile: https://www.linkedin.com/in/tenaciousprevailer
 * @authorContact: 8955047966
 * @purpose: Societe Generale Test
 */
public interface EmployeeSearchRepository extends ElasticsearchRepository<Employee, String> {
}
