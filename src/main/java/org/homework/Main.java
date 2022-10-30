package org.homework;

import com.google.gson.Gson;
import org.homework.controller.AccountController;
import org.homework.controller.impl.AccountControllerImpl;
import org.homework.entity.Account;
import org.homework.repository.AccountsRepository;
import org.homework.repository.CurrencyRepository;
import org.homework.service.CurrencyConvertor;
import org.homework.service.MoneyTransfer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        
//        AccountController accountController = new AccountControllerImpl();
//        accountController.receiveCommand();


        AccountsRepository accountsRepository = new AccountsRepository("/home/victor/IdeaProjects/vv_accounts_lessons/accounts_formatted.json");
        CurrencyRepository currencyRepository = new CurrencyRepository("/home/victor/IdeaProjects/vv_accounts_lessons/currencies_formatted.json");
        CurrencyConvertor convertorService = new CurrencyConvertor(currencyRepository);
        MoneyTransfer transferService = new MoneyTransfer(accountsRepository, convertorService);

        //stransferService.run(

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
