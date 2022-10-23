package org.homework.entity;

public class Account implements Indexable<String>{
    public String accountName;
    
    public String currencyAbbrev;
    
    public double balance;

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
}
