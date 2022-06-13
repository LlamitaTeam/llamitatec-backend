package com.llamitatec.backend.employee.api;

import com.llamitatec.backend.employee.domain.service.EmployeeService;
import com.llamitatec.backend.employee.mapping.EmployeeMapper;
import com.llamitatec.backend.employee.resource.CreateEmployeeResource;

import com.llamitatec.backend.employee.resource.EmployeeResource;
import com.llamitatec.backend.employee.resource.UpdateEmployeeResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/employees")
public class EmployeesController {
    private final EmployeeService employeeService;
    private final EmployeeMapper mapper;

    public EmployeesController(EmployeeService employeeService, EmployeeMapper mapper) {
        this.employeeService = employeeService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<EmployeeResource> getAllEmployees(Pageable pageable){
        return mapper.modelListPage(employeeService.getAll(),pageable);
    }

    @GetMapping("{userId}")
    public EmployeeResource getEmployeeById(@PathVariable("userId") Long userId){
        return mapper.toResource(employeeService.getById(userId));
    }

    @PostMapping
    public EmployeeResource createEmployee(@RequestBody CreateEmployeeResource resource){
        return mapper.toResource(employeeService.create(mapper.toModel(resource)));
    }

    @PutMapping("{employeeId}")
    private EmployeeResource updateEmployee(@PathVariable("employeeId") Long employeeId,@RequestBody UpdateEmployeeResource resource){
        return mapper.toResource(employeeService.update(employeeId,mapper.toModel(resource)));
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId, @PathVariable Long userId){
        return employeeService.delete(employeeId,userId);
    }
}
