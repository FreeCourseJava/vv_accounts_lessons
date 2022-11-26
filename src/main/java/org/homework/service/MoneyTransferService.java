package org.homework.service;

import org.homework.di.Service;
import org.homework.entity.Account;
import org.homework.repository.AccountsRepository;

@Service
public class MoneyTransferService {

    AccountsRepository rep;
    CurrencyConvertorService convertor;

    public MoneyTransferService(AccountsRepository rep, CurrencyConvertorService convertor) {
        this.rep = rep;
        this.convertor = convertor;
    }

    public Boolean run(String from, String to, double amount){

        Account fromAccount = rep.getValue(from);
        Account toAccount = rep.getValue(to);
        if ( fromAccount != null && toAccount != null) {
            if (fromAccount.deductMoney(amount)) {
                toAccount.addMoney(amount * convertor.getRate(fromAccount.getCurrency(),toAccount.getCurrency()));
                rep.sync();
                return true;
            }
        }

        return false;
    }
}
