package me.imsonmia.demoapi.TransactionsRepository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Transaction {
    private @Id @GeneratedValue Long id;
    private int TransactionAmountCents;
    private Long senderId;
    private Long receiverId;
    private String note;

    public long getId() {
        return id;
    }

    public int getTransactionAmountCents() {
        return TransactionAmountCents;
    }

    public long getSenderId() {
        return senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public String getNote() {
        return note;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTransactionAmountCents(int transactionAmountCents) {
        TransactionAmountCents = transactionAmountCents;
    }

    public void setSenderId(long senderID) {
        this.senderId = senderID;
    }

    public void setReceiverId(long receiverID) {
        this.receiverId = receiverID;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
