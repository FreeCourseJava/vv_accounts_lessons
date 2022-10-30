package org.homework.entity;

public class Currency implements Indexable<String>{
    
    public double rateToUsd;
    public String abbrev;

    @Override
    public String getId() {
        return this.abbrev;
    }

    public double getRate() {
        return this.rateToUsd;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "rateToUsd=" + rateToUsd +
                ", abbrev='" + abbrev + '\'' +
                '}';
    }

}
