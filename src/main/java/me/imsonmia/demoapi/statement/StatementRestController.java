package me.imsonmia.demoapi.statement;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.imsonmia.demoapi.employee.Employee;
import me.imsonmia.demoapi.employee.EmployeeRepository;
import me.imsonmia.demoapi.exceptionhandler.DataNotFoundException;
import me.imsonmia.demoapi.transaction.Transaction;
import me.imsonmia.demoapi.transaction.TransactionRepository;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
class StatementRestController {
    private final EmployeeRepository empRepo;
    private final TransactionRepository transactRepo;

    StatementRestController(EmployeeRepository empRepo, TransactionRepository transactRepo) {
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
        List<Transaction> allTransactions = transactRepo.findAllByReceiverId(id);
        int totalCompletedTransferAmountInCents = 0;
        for (Transaction t : allTransactions) {
            totalCompletedTransferAmountInCents += t.getTransactionAmountCents();
        }
        return paymentTargetInCents - totalCompletedTransferAmountInCents;
    }

    @GetMapping("/statement/{id}")
    Statement getStatement(
            @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PathVariable Long id) {
        List<Transaction> transactionsRelated = new ArrayList<>();
        transactionsRelated.addAll(transactRepo.findAllByReceiverId(id));
        transactionsRelated.addAll(transactRepo.findAllBySenderId(id));
        List<Transaction> transactionsFiltered = new ArrayList<>();
        for (Transaction t : transactionsRelated) {
            LocalDate transactTime = LocalDate.ofInstant(t.getTransactionDate().toInstant(), ZoneId.systemDefault());
            // filter invalid transactions (out of accepted date boundaries)
            if (!(transactTime.isBefore(endDate) &&
                    transactTime.isAfter(startDate))) {
                continue;
            }
            transactionsFiltered.add(t);
        }
        return new Statement(transactionsFiltered, id, startDate, endDate);
    }
}
