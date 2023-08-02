package me.imsonmia.demoapi;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.imsonmia.demoapi.EmployeeRepository.Employee;
import me.imsonmia.demoapi.EmployeeRepository.EmployeeRepository;
import me.imsonmia.demoapi.ExceptionHandler.DataNotFoundException;
import me.imsonmia.demoapi.TransactionsRepository.Transaction;
import me.imsonmia.demoapi.TransactionsRepository.TransactionRepository;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
class GlobalRestController {
    private final EmployeeRepository empRepo;
    private final TransactionRepository transactRepo;

    GlobalRestController(EmployeeRepository empRepo, TransactionRepository transactRepo) {
        this.empRepo = empRepo;
        this.transactRepo = transactRepo;
    }

    @GetMapping("/pending/{id}")
    int getAllOutstandingPayments(@PathVariable Long id) {
        Employee targetEmployee = empRepo.findById(id).orElseThrow(() -> new DataNotFoundException());
        int salaryInCents = targetEmployee.getMonthlyPayrollCents();
        Calendar calendarInitial = Calendar.getInstance();
        calendarInitial.setTime(targetEmployee.getJoinDate());
        Calendar calendarCurrent = Calendar.getInstance();
        calendarCurrent.setTime(new Date());
        int months = (calendarCurrent.get(Calendar.YEAR) - calendarInitial.get(Calendar.YEAR)) * 12 +
                calendarCurrent.get(Calendar.MONTH) - calendarInitial.get(Calendar.MONTH);
        int paymentTargetInCents = salaryInCents * months;
        List<Transaction> allTransactions = transactRepo.findByReceiverId(id);
        int totalCompletedTransferAmountInCents = 0;
        for (Transaction t : allTransactions) {
            totalCompletedTransferAmountInCents += t.getTransactionAmountCents();
        }
        return paymentTargetInCents - totalCompletedTransferAmountInCents;
    }
}