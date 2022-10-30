package org.homework.repository;

import org.homework.entity.Currency;

public class CurrencyRepository extends Repository<String, Currency>{

    public CurrencyRepository(String filePath) {
        super(filePath, Currency[].class);
    }
}
