package me.imsonmia.demoapi.TransactionRepository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findBySenderId(Long senderId);

    List<Transaction> findByReceiverId(Long receiverId);
}
