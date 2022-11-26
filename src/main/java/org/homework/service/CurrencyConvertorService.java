package org.homework.service;

import org.homework.di.Service;
import org.homework.repository.CurrencyRepository;

@Service
public class CurrencyConvertorService {

    CurrencyRepository rep;

    public CurrencyConvertorService(CurrencyRepository rep) {
        this.rep = rep;
    }

    public double getRate(String from, String to) {
        return rep.getValue(to).getRate() / rep.getValue(from).getRate();
    }

}
