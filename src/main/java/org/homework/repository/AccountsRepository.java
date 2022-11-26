package org.homework.repository;

import org.homework.di.DefaultValue;
import org.homework.di.Service;
import org.homework.entity.Account;
@Service
public class AccountsRepository extends Repository<String, Account> {

    public AccountsRepository(@DefaultValue("./accounts_formatted.json") String filePath) {
        super(filePath, Account[].class);

    }
}
