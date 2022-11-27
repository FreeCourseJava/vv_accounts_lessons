package org.homework;

import org.homework.controller.AccountControllerStdIn;
import org.homework.di.Injector;
import org.homework.repository.AccountsRepository;
import org.homework.repository.CurrencyRepository;
import org.homework.service.CurrencyConvertorService;
import org.homework.service.MoneyTransferService;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {

//        AccountsRepository accountsRepository = new AccountsRepository("./accounts_formatted.json");
//        CurrencyRepository currencyRepository = new CurrencyRepository("./currencies_formatted.json");
//        CurrencyConvertorService convertorService = new CurrencyConvertorService(currencyRepository);
//        MoneyTransferService transferService = new MoneyTransferService(accountsRepository, convertorService);
//        AccountControllerStdIn accountController = new AccountControllerStdIn(transferService);
//
//        System.out.println(accountsRepository.getValue("Alexey"));
//        System.out.println(accountsRepository.getValue("Sasha"));
//
//        accountController.receiveCommand();
//
//        System.out.println(accountsRepository.getValue("Alexey"));
//        System.out.println(accountsRepository.getValue("Sasha"));

        Injector inj = new Injector();
        inj.createServices();
        inj.start();

    }

}
