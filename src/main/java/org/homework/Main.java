package org.homework;

import com.google.gson.Gson;
import org.homework.controller.AccountController;
import org.homework.controller.impl.AccountControllerImpl;
import org.homework.entity.Account;

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

        String PATH_TO_FILE="/home/victor/IdeaProjects/vv_accounts_lessons/accounts_formatted.json";

        Gson gson = new Gson();

        try (FileReader accountsFile = new FileReader(PATH_TO_FILE)) {

            BufferedReader accountsReader = new BufferedReader(accountsFile);
            Account[] accounts = gson.fromJson(accountsReader, Account[].class);
            Map<String,Account> accountsMap= Arrays.stream(accounts).collect(Collectors.toMap(Account::getId, Function.identity()));

            System.out.println(accountsMap.get("Petja"));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //        Account testAcc = gson.fromJson("{\"accountName\":\"Vasja\",\"currencyAbbrev\":\"USD\",\"balance\":500.0}", Account.class);


    }
    
}
