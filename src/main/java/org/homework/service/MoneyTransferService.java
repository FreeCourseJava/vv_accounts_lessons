package org.homework.service;

import org.homework.entity.Account;
import org.homework.repository.AccountsRepository;

public class MoneyTransferService {

    AccountsRepository rep;
    CurrencyConvertorService convertor;

    public MoneyTransferService(AccountsRepository rep, CurrencyConvertorService convertor) {
        this.rep = rep;
        this.convertor = convertor;
    }

    public void run(String from, String to, double amount){

        Account fromAccount = rep.getValue(from);
        Account toAccount = rep.getValue(to);
        if ( fromAccount != null && toAccount != null) {
            if (fromAccount.deductMoney(amount)) {
                toAccount.addMoney(amount * convertor.getRate(fromAccount.getCurrency(),toAccount.getCurrency()));
                rep.sync();
            }
        }
    }
}
