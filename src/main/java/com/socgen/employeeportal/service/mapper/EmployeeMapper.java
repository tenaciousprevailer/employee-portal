package com.socgen.employeeportal.service.mapper;


import com.socgen.employeeportal.domain.Employee;
import com.socgen.employeeportal.service.dto.EmployeeDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Employee} and its DTO {@link EmployeeDTO}.
 *
 * @author: Ravi Kumar Soni
 * @authorLinkedProfile: https://www.linkedin.com/in/tenaciousprevailer
 * @authorContact: 8955047966
 * @purpose: Societe Generale Test
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {

    EmployeeDTO toDto(Employee employee);

    Employee toEntity(EmployeeDTO employeeDTO);

    default Employee fromId(String id) {
        if (id == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(id);
        return employee;
    }
}
