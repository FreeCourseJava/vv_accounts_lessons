package org.homework.entity;

public class Account implements Indexable<String>{
    public String accountName;
    
    public String currencyAbbrev;
    
    public double balance;

    public Account(String accountName, String currencyAbbrev, double balance) {
        this.accountName = accountName;
        this.currencyAbbrev = currencyAbbrev;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountName='" + accountName + '\'' +
                ", currencyAbbrev='" + currencyAbbrev + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public String getId() {
        return this.accountName;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getCurrency() {
        return this.currencyAbbrev;
    }

    public boolean deductMoney (double amount) {
        if (amount >0 && this.balance > amount) {
            this.balance -= amount;
            return true;
        }

        return false;

    }

    public void addMoney(double amount) {
        this.balance += amount;
    }

}
