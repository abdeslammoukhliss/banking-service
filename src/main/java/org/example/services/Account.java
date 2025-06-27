package org.example.services;

import org.example.entites.Transaction;
import org.example.entites.TransactionType;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Account implements AccountService
{
    private int balance;
    private ArrayList<Transaction> transactions;
    private SimpleDateFormat dateFormat;

    public Account()
    {
        this.balance = 0;
        this.transactions = new ArrayList<>();
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }
    @Override
    public void disposit(int amount) {
        validateAmount(amount);
        balance += amount;
        transactions.add(new Transaction(amount, TransactionType.DEPOSIT, new Date(),balance));
    }

    @Override
    public void withdraw(int amount) {
        validateAmount(amount);
        if (balance < amount) {
            throw new IllegalArgumentException("Not enough balance");
        }
        balance -= amount;
        transactions.add(new Transaction(amount, TransactionType.WITHDRAWAL, new Date(),balance));
    }

    @Override
    public void printStatement() {
        System.out.println("Date || Amount || Balance");

        ArrayList<Transaction> reversedTransactions = new ArrayList<>(transactions);
        Collections.reverse(reversedTransactions);

        for (Transaction transaction : reversedTransactions) {
            System.out.println(formatTransaction(transaction));
        }
    }
    private void validateAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive. Provided: " + amount);
        }
    }
    public int getBalance() {
        return balance;
    }
    private String formatTransaction(Transaction transaction) {
        StringBuilder sb = new StringBuilder();
        sb.append(dateFormat.format(transaction.date()));
        sb.append(" || ");

        if (transaction.type() == TransactionType.DEPOSIT) {
            sb.append(transaction.amount());
        } else {
            sb.append("-").append(transaction.amount());
        }

        sb.append(" || ");
        sb.append(transaction.balanceAfterTransaction());

        return sb.toString();
    }
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
