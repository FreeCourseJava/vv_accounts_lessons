package org.homework;

import org.homework.controller.AccountControllerStdIn;
import org.homework.repository.AccountsRepository;
import org.homework.repository.CurrencyRepository;
import org.homework.service.CurrencyConvertorService;
import org.homework.service.MoneyTransferService;

public class Main {

    public static void main(String[] args) {

        AccountsRepository accountsRepository = new AccountsRepository("/home/victor/IdeaProjects/vv_accounts_lessons/accounts_formatted.json");
        CurrencyRepository currencyRepository = new CurrencyRepository("/home/victor/IdeaProjects/vv_accounts_lessons/currencies_formatted.json");
        CurrencyConvertorService convertorService = new CurrencyConvertorService(currencyRepository);
        MoneyTransferService transferService = new MoneyTransferService(accountsRepository, convertorService);
        AccountControllerStdIn accountController = new AccountControllerStdIn(transferService);

        System.out.println(accountsRepository.getValue("Alexey"));
        System.out.println(accountsRepository.getValue("Sasha"));

        System.out.println(convertorService.getRate("KZT","USD"));
        accountController.receiveCommand();

//        transferService.run("Alexey", "Sasha", 99.99);
        System.out.println(accountsRepository.getValue("Alexey"));
        System.out.println(accountsRepository.getValue("Sasha"));

    }
    
}
