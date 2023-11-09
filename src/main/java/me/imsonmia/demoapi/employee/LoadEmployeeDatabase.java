package me.imsonmia.demoapi.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// import me.imsonmia.demoapi.employee.Employee.Level;
// import me.imsonmia.demoapi.employee.Employee.Role;
// import net.datafaker.Faker;

@Configuration
public class LoadEmployeeDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadEmployeeDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            log.info("Preloading employee repository...");
            // Faker faker = new Faker();
            // for (int i = 0; i < 100; i++) {
            // final String[] employeeLevelsForFaker = { "BOARD", "UPPER", "MIDDLE",
            // "EMPLOYEE", "TEMP" };
            // final String[] employeeRolesForFaker = { "MAINTENANCE", "DEVELOPMENT",
            // "LOGISTICS", "MANAGEMENT" };
            // repository.save(new Employee(
            // faker.name().fullName(),
            // faker.random().nextInt(0, 1000000),
            // faker.random().nextInt(0, 100),
            // faker.date().birthday(),
            // Level.valueOf(employeeLevelsForFaker[faker.random().nextInt(employeeLevelsForFaker.length)]),
            // Role.valueOf(employeeRolesForFaker[faker.random().nextInt(employeeRolesForFaker.length)])));
            // }
            for (Employee employee : repository.findAll()) {
                log.info("Found employee: " + employee.toString());
            }
        };
    }
}
