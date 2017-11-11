package com.onyx.cryptocompare;

/**
 * Created by onyekaanene on 04/11/2017.
 */

public class ExchangeItem {
    private String currency;
    private String currencyName;
    private double btcRate;
    private double ethRate;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBtcRate() {
        return btcRate;
    }

    public void setBtcRate(double btcRate) {
        this.btcRate = btcRate;
    }

    public double getEthRate() {
        return ethRate;
    }

    public void setEthRate(double ethRate) {
        this.ethRate = ethRate;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}
