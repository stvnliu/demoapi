package me.imsonmia.demoapi.employee;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAllByEmpLevel(Employee.Level emplevel);

    List<Employee> findAllByEmpRole(Employee.Role emprole);
}
