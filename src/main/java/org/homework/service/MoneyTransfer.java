package org.homework.service;

import org.homework.entity.Account;
import org.homework.repository.AccountsRepository;

public class MoneyTransfer {

    AccountsRepository rep;
    CurrencyConvertor convertor;

    public MoneyTransfer(AccountsRepository rep, CurrencyConvertor convertor) {
        this.rep = rep;
        this.convertor = convertor;
    }

    public static void run(Account from, Account to, double amount){
        if (from.getBalance() > amount) {

        }
    }
}
