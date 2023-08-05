package me.imsonmia.demoapi.transaction;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findAllBySenderId(Long senderId);

    List<Transaction> findAllByReceiverId(Long receiverId);
}
