package me.imsonmia.demoapi.TransactionsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
@RequestMapping(value = "/transactions", produces = "application/json")
public class TransactionController {
    private final TransactionRepository repository;

    TransactionController(TransactionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/get")
    List<Transaction> getTransactionBySenderOrReceiverId(
            @RequestParam Optional<Long> senderId,
            @RequestParam Optional<Long> receiverId,
            @RequestParam Optional<Long> id) {
        List<Transaction> result = new ArrayList<>();
        if (senderId.isPresent()) {
            List<Transaction> intermediateResult = new ArrayList<>();
            intermediateResult.addAll(repository.findBySenderId(senderId.get()));
            if (receiverId.isPresent()) {
                // filter out transactions where receiver id does not match
                for (Transaction transaction : intermediateResult) {
                    if (transaction.getReceiverId() != receiverId.get()) {
                        continue;
                    }
                    result.add(transaction);
                }
            } else {
                result = intermediateResult;
            }
        } else if (receiverId.isPresent()) {
            result = repository.findByReceiverId(receiverId.get());
        }
        return result;
    }

    @GetMapping("/get/{id}")
    Transaction getOneTransaction(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException());
    }

    @PostMapping("/post")
    Transaction addOneTransaction(@RequestBody Transaction transaction) {
        return repository.save(transaction);
    }

    @PutMapping("/put/{id}")
    Transaction replaceTransaction(@RequestBody Transaction newTransaction, @PathVariable Long id) {
        return repository.findById(id)
                .map(transaction -> {
                    transaction.setTransactionAmountCents(newTransaction.getTransactionAmountCents());
                    transaction.setSenderId(newTransaction.getSenderId());
                    transaction.setReceiverId(newTransaction.getReceiverId());
                    transaction.setNote(newTransaction.getNote());

                    return repository.save(transaction);
                }).orElseGet(() -> {
                    newTransaction.setId(id);
                    return repository.save(newTransaction);
                });
    }

    @DeleteMapping("/delete/{id}")
    void deleteTransaction(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
