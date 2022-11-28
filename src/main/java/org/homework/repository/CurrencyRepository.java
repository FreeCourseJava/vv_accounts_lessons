package org.homework.repository;

import org.homework.di.DefaultValue;
import org.homework.di.Service;
import org.homework.entity.Currency;

@Service
public class CurrencyRepository extends Repository<String, Currency> {

    public CurrencyRepository(@DefaultValue("./data/currencies_formatted.json") String filePath) {
        super(filePath, Currency[].class);
    }
}
