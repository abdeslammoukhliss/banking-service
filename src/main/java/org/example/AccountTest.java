package org.example;

import org.example.services.Account;
import org.example.services.AccountService;

import java.util.Date;

class AccountTest {
    public static void main(String[] args) {
        try {
            // Test using the AccountService interface
            System.out.println("=== Banking System Test (AccountService Interface) ===\n");

            AccountService account = new Account();

            // Test basic functionality with current dates
            System.out.println("Testing with current dates:");
            account.disposit(1000);
            account.disposit(2000);
            account.withdraw(500);
            account.printStatement();

            // Test the acceptance criteria with specific dates
            System.out.println("\n=== Acceptance Test with Specific Dates ===\n");
            Account testAccount = new Account();



            // Additional tests
            System.out.println("\n=== Additional Tests ===\n");
            testExceptions();
            testPerformance();

        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Helper method to create dates for testing.
     */
    private static Date createDate(int day, int month, int year) {
        return new Date(year - 1900, month - 1, day);
    }

    /**
     * Test exception handling using the AccountService interface.
     */
    private static void testExceptions() {
        System.out.println("Testing exception handling...");
        AccountService testAccount = new Account();

        try {
            testAccount.disposit(-100);
            System.out.println("ERROR: Should have thrown exception for negative deposit");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Correctly caught negative deposit: " + e.getMessage());
        }

        try {
            testAccount.withdraw(100);
            System.out.println("ERROR: Should have thrown exception for insufficient funds");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Correctly caught insufficient funds: " + e.getMessage());
        }

        try {
            testAccount.disposit(0);
            System.out.println("ERROR: Should have thrown exception for zero deposit");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Correctly caught zero deposit: " + e.getMessage());
        }


    }


    private static void testPerformance() {
        System.out.println("Testing performance with 10,000 transactions...");
        AccountService perfAccount = new Account();

        long startTime = System.currentTimeMillis();

        // Add many transactions
        for (int i = 0; i < 10000; i++) {
            perfAccount.disposit(1);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("✓ 10,000 deposits completed in " + (endTime - startTime) + "ms");

        // Cast to Account to access getBalance for testing
        Account accountImpl = (Account) perfAccount;
        System.out.println("✓ Final balance: " + accountImpl.getBalance());
        System.out.println("✓ Transaction count: " + accountImpl.getTransactions().size());

        System.out.println();
    }
}