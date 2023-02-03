package com.example.Employee.Management.System.service;

import com.example.Employee.Management.System.model.Employee;
import com.example.Employee.Management.System.repository.Employee_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class Employee_service {

    @Autowired
    Employee_repository repository;

    public List<Employee> getAllEmployees (){
        return repository.findAll();
    }


    // for add employee button
    public void saveEmployee(Employee employee){
        this.repository.save(employee);
    }

    // for update button
    // get by ID
    public Employee getEmployeeById(long id) {
        Optional< Employee > optional = repository.findById(id);
        Employee employee = null;
        if (optional.isPresent()) {
            employee = optional.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return employee;
    }

    // Delete employee by ID

    public void deleteEmployeeById(long id) {
        this.repository.deleteById(id);
    }

    //For Pagination or Sorting..(Sorting feature is implement after pagination ,we can use both feature at a one time)

    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.repository.findAll(pageable);
    }


}
