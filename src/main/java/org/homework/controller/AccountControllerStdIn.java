package org.homework.controller;

import org.homework.service.MoneyTransferService;

import java.util.Scanner;

public class AccountControllerStdIn implements AccountController{

    MoneyTransferService service;

    public AccountControllerStdIn(MoneyTransferService service) {
        this.service = service;
    }

    @Override
    public void receiveCommand() {

        Scanner scan = new Scanner(System.in);
        System.out.println("Input the account name to transfer money from: ");
        String from= scan.nextLine();
        System.out.println("Input the account name to transfer money to: ");
        String to= scan.nextLine();
        System.out.println("Input the account name to transfer money to: ");
        double amount= scan.nextDouble();

        if (service.run(from,to,amount)) {
            System.out.println("Money transfer is successful");
        } else {
            System.out.println("Money transfer failed, call the support line");
        }


    }
}
