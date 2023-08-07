package me.imsonmia.demoapi.statement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import me.imsonmia.demoapi.transaction.Transaction;

public class Statement {
    private String statementId;
    private StatementFormat statementFormat;
    private Long ownerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Transaction> revenueList;
    private List<Transaction> expenseList;
    private int balanceInCents;

    Statement(List<Transaction> transactions, Long ownerId, LocalDate startDate, LocalDate endDate) {
        setOwnerId(ownerId);
        setStatementFormat(StatementFormat.PDF_LIST);
        String newIdString = "[" + ownerId + "]" + "{" + startDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + "#"
                + endDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + "}";
        setStatementId(newIdString);
        setBalanceInCents(0);
        List<Transaction> tempRevenue = new ArrayList<>();
        List<Transaction> tempExpense = new ArrayList<>();
        for (Transaction t : transactions) {
            boolean isRevenue = t.getReceiverId() == ownerId;
            int diffInCents;
            if (isRevenue) {
                diffInCents = t.getTransactionAmountCents();
                tempRevenue.add(t);
            } else {
                diffInCents = -t.getTransactionAmountCents();
                tempExpense.add(t);
            }
            setBalanceInCents(getBalanceInCents() + diffInCents);
        }
        setRevenueList(tempRevenue);
        setExpenseList(tempExpense);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public StatementFormat getStatementFormat() {
        return statementFormat;
    }

    public String getStatementId() {
        return statementId;
    }

    public int getBalanceInCents() {
        return balanceInCents;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<Transaction> getRevenueList() {
        return revenueList;
    }

    public List<Transaction> getExpenseList() {
        return expenseList;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public void setStatementFormat(StatementFormat statementFormat) {
        this.statementFormat = statementFormat;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

    public void setBalanceInCents(int totalIncomeInCents) {
        this.balanceInCents = totalIncomeInCents;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setExpenseList(List<Transaction> expenseList) {
        this.expenseList = expenseList;
    }

    public void setRevenueList(List<Transaction> revenueList) {
        this.revenueList = revenueList;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
