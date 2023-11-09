package me.imsonmia.demoapi.transaction;

import java.util.Date;

import com.google.gson.Gson;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Transaction {
    private @Id @GeneratedValue Long id;
    private int transactionAmountCents;
    private Long senderId;
    private Long receiverId;
    private String note;
    private Date transactionDate;

    Transaction() {
    };

    Transaction(
            int transactionAmountCents,
            Long senderId,
            Long receiverId,
            String note,
            Date date) {
        setTransactionAmountCents(transactionAmountCents);
        setSenderId(senderId);
        setReceiverId(receiverId);
        setNote(note);
        setTransactionDate(date);
    }

    public long getId() {
        return id;
    }

    public int getTransactionAmountCents() {
        return transactionAmountCents;
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

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTransactionAmountCents(int transactionAmountCents) {
        this.transactionAmountCents = transactionAmountCents;
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

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
