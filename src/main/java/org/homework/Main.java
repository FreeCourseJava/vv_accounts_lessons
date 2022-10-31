package org.homework;

import org.homework.repository.AccountsRepository;
import org.homework.repository.CurrencyRepository;
import org.homework.service.CurrencyConvertorService;
import org.homework.service.MoneyTransferService;

public class Main {

    public static void main(String[] args) {
        
//        AccountController accountController = new AccountControllerImpl();
//        accountController.receiveCommand();


        AccountsRepository accountsRepository = new AccountsRepository("/home/victor/IdeaProjects/vv_accounts_lessons/accounts_formatted.json");
        CurrencyRepository currencyRepository = new CurrencyRepository("/home/victor/IdeaProjects/vv_accounts_lessons/currencies_formatted.json");
        CurrencyConvertorService convertorService = new CurrencyConvertorService(currencyRepository);
        MoneyTransferService transferService = new MoneyTransferService(accountsRepository, convertorService);

        System.out.println(accountsRepository.getValue("Alexey"));
        System.out.println(accountsRepository.getValue("Sasha"));
        transferService.run("Alexey", "Sasha", 99.99);
        System.out.println(accountsRepository.getValue("Alexey"));
        System.out.println(accountsRepository.getValue("Sasha"));

//        System.out.println(currencyRepository.getValue("USD"));
//        System.out.println(convertorService.getRate(currencyRepository.getValue("RUB"),currencyRepository.getValue("EUR")));
//
//
//        System.out.println(accountsRepository.getValue("Petja"));
//        accountsRepository.updateValue("Petja", new Account("Petja", "RUB",2500));
//        System.out.println(accountsRepository.getValue("Petja"));

        //        Account testAcc = gson.fromJson("{\"accountName\":\"Vasja\",\"currencyAbbrev\":\"USD\",\"balance\":500.0}", Account.class);


    }
    
}
