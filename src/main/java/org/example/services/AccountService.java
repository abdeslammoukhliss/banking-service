package org.example.services;

    public interface AccountService {
        void disposit(int amount);
        void withdraw(int amount);
        void printStatement();
    }
