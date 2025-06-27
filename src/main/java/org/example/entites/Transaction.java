package org.example.entites;


import java.util.Date;

public record Transaction(int amount, TransactionType type, Date date , int balanceAfterTransaction) { }