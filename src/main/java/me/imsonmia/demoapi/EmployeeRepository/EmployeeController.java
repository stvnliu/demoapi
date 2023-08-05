package me.imsonmia.demoapi.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.imsonmia.demoapi.ExceptionHandler.DataNotFoundException;

@RestController
@RequestMapping(value = "/employees", produces = "application/json")
public class EmployeeController {
    private final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/get")
    List<Employee> getEmployeeByConstraint(
            @RequestParam Optional<String> level,
            @RequestParam Optional<String> role) {
        List<Employee> result = new ArrayList<>();
        if (level.isPresent()) {
            result = repository.findByEmpLevel(Employee.Level.valueOf(level.get()));
        } else if (role.isPresent()) {
            result = repository.findByEmpRole(Employee.Role.valueOf(role.get()));
        } else {
            // convert Iterator to List
            // https://stackoverflow.com/questions/10117026/convert-iterator-to-list
            result = StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
        }
        if (result.size() == 0) {
            throw new DataNotFoundException();
        }
        log.info("Found matches " + result.toString());
        return result;
    }

    @PostMapping("/post")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    @GetMapping("/get/{id}")
    Employee getOne(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException());
    };

    @PutMapping("/put/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setEmpLevel(newEmployee.getEmpLevel());
                    employee.setEmpRole(newEmployee.getEmpRole());
                    employee.setAge(newEmployee.getAge());
                    employee.setJoinDate(newEmployee.getJoinDate());
                    employee.setMonthlyPayrollCents(newEmployee.getMonthlyPayrollCents());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/delete/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
