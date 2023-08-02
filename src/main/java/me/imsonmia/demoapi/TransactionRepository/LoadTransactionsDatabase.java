package me.imsonmia.demoapi.TransactionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadTransactionsDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadTransactionsDatabase.class);

    @Bean
    CommandLineRunner initTransactDatabase(TransactionRepository transactionRepository) {
        return args -> {
            log.info("Preloading transaction repository...");
            for (Transaction t : transactionRepository.findAll()) {
                log.info("Found transaction " + t.toString());
            }
        };
    }
}