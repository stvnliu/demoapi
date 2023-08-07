package me.imsonmia.demoapi.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.datafaker.Faker;

@Configuration
class LoadTransactionsDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadTransactionsDatabase.class);

    @Bean
    CommandLineRunner initTransactDatabase(TransactionRepository transactionRepository) {
        return args -> {
            log.info("Preloading transaction repository...");
            // Faker faker = new Faker();
            // for (int i = 0; i < 1000; i++) {
            // transactionRepository
            // .save(new Transaction(
            // faker.random().nextInt(0, 1000000),
            // faker.random().nextLong(1, 101),
            // faker.random().nextLong(1, 101),
            // "Debug payment",
            // faker.date().birthday()));
            // }
            for (Transaction t : transactionRepository.findAll()) {
                log.info("Found transaction " + t.toString());
            }
        };
    }
}