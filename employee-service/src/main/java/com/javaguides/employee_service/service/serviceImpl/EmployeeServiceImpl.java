package com.javaguides.employee_service.service.serviceImpl;

import com.javaguides.employee_service.dto.EmployeeDto;
import com.javaguides.employee_service.entity.Employee;
import com.javaguides.employee_service.exception.ResourceNotFoundException;
import com.javaguides.employee_service.repository.EmployeeRepository;
import com.javaguides.employee_service.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepo;
    private ModelMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepo, ModelMapper mapper) {
        this.employeeRepo = employeeRepo;
        this.mapper = mapper;
    }

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee savedEmployee = employeeRepo.save(mapper.map(employeeDto, Employee.class));
        return mapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto getEmployeeById(long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", String.valueOf(id))
        );

        return mapper.map(employee, EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        return employeeRepo.findAll().stream()
                .map(employee -> mapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployeeById(long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", String.valueOf(id))
        );
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartmentCode(employeeDto.getDepartmentCode());
        Employee updatedEmployee = employeeRepo.save(employee);
        return mapper.map(updatedEmployee, EmployeeDto.class);
    }

    @Override
    public String deleteEmployeeById(long id) {
        employeeRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", String.valueOf(id))
        );
        employeeRepo.deleteById(id);
        return "Employee deleted successfully.";
    }
    
}
