package me.imsonmia.demoapi.transaction;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findAllBySenderId(Long senderId);

    List<Transaction> findAllByReceiverId(Long receiverId);

    // private Specification<Transaction> dateWithinDateTimeSpan(Date start, Date
    // end) {
    // return new Specification<Transaction>() {
    // @Override
    // public Predicate toPredicate(Root<Transaction> root,
    // CriteriaQuery<?> query,
    // CriteriaBuilder criteriaBuilder) {
    // return criteriaBuilder.between(root.get(), null, null)
    // }
    // }
    // };
}
