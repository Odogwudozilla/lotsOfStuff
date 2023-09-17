package com.odogwudozilla.algodaily.currencyconversion;

public class Rate {
    public String to;
    public String from;
    public Double number;

    public Rate(String from, String to, Double number){
        this.from = from;
        this.to = to;
        this.number = number;
    }
}