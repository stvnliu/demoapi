package me.imsonmia.demoapi.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadEmployeeDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadEmployeeDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            log.info("Database is being initiated...");
            for (Employee employee : repository.findAll()) {
                log.info("Found employee: " + employee.toString());
            }
        };
    }
}
