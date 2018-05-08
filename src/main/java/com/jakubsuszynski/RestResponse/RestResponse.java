package com.jakubsuszynski.RestResponse;

import java.util.ArrayList;

public class RestResponse {
    private String table;
    private String currency;
    private String code;
    private ArrayList<Rates> rates;

    public RestResponse() {
    }

    public String getTable() {

        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Rates> getRates() {
        return rates;
    }

    public void setRates(ArrayList<Rates> rates) {
        this.rates = rates;
    }

}
