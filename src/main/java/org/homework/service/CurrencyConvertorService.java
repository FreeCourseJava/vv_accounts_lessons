package org.homework.service;

import org.homework.repository.CurrencyRepository;


public class CurrencyConvertorService {

    CurrencyRepository rep;

    public CurrencyConvertorService(CurrencyRepository rep) {
        this.rep = rep;
    }

    public double getRate(String from, String to) {
        return Math.floor((rep.getValue(to).getRate() / rep.getValue(from).getRate()) * 100d + 0.5d) / 100d;
    }

}
