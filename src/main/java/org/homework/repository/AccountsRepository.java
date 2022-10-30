package org.homework.repository;

import org.homework.entity.Account;

public class AccountsRepository extends Repository<String, Account> {

    public AccountsRepository(String filePath) {
        super(filePath, Account[].class);

    }
}
