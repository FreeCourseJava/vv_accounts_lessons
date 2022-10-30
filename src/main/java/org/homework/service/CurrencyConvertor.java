package org.homework.service;

import org.homework.entity.Currency;
import org.homework.repository.CurrencyRepository;


public class CurrencyConvertor {

    CurrencyRepository rep;

    public CurrencyConvertor(CurrencyRepository rep) {
        this.rep = rep;
    }

    public double getRate(Currency from, Currency to) {

        return Math.floor(from.getRate() / to.getRate() * 100 + 0.5) / 100;
    }

}
