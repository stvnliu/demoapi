package me.imsonmia.demoapi.Employee;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByEmpLevel(Employee.Level emplevel);

    List<Employee> findByEmpRole(Employee.Role emprole);
}
