package com.odogwudozilla.algodaily.currencyconversion;

import java.util.*;

public class CurrencyConverter {
    private CurrencyConverter() {

    }

    public static void main(String[] args) {
        Rate rate1 = new Rate("USD", "JPY", 110.0);
        Rate rate2 = new Rate("USD", "AUD", 1.45);
        Rate rate3 = new Rate("JPY", "GPB", 0.0070);
        Rate wantedRate = new Rate("GPB", "AUD", 0.0);
        List<Rate> inputRates = List.of(rate1, rate2, rate3);

        CurrencyConverter.solveCurrency(inputRates, wantedRate);

    }
    public static Double solveCurrency(List<Rate> rates, Rate wanted) {

        Map<String, ArrayList<Currency>> map = prepareInputMapFromRates(rates);
        Deque<Currency> currenciesThatCanBeTraversed = new ArrayDeque<>();
        List<String> visited = new ArrayList<>();
        currenciesThatCanBeTraversed.push(new Currency(wanted.from, wanted.number));
        List<Currency> values;
        while (!currenciesThatCanBeTraversed.isEmpty()) {
            Currency currency = currenciesThatCanBeTraversed.pop();
            visited.add(currency.name);
            values = map.get(currency.name);
            if (Objects.nonNull(values)) {
                for (Currency cur : values) {
                    if (!visited.contains(cur.name)) {
                        currenciesThatCanBeTraversed.push(new Currency(cur.name, cur.number * currency.number));
                        if (cur.name.equals(wanted.to)) {
                            double result1 = currency.number * cur.number;
                            System.out.println("Final: " + result1);
                            return Math.round(result1 * 100.0) / 100.0;
                        }
                    }
                }
            }
        }
        return -1.0;
    }

    private static Map<String, ArrayList<Currency>> prepareInputMapFromRates(List<Rate> rates) {
        Map<String, ArrayList<Currency>> map = new HashMap<>();
        for (Rate rate : rates) {
            String from = rate.from;
            String to = rate.to;
            Double number = rate.number;
            Currency firstCur = new Currency(to, number);
            Currency secondCur = new Currency(from, 1 / number);
            ArrayList<Currency> curList;
            if (map.containsKey(from)) {
                curList = map.get(from);
            } else {
                curList = new ArrayList<>();
            }
            curList.add(firstCur);
            map.put(from, curList);

            if (map.containsKey(to)) {
                map.get(to).add(secondCur);
            } else {
                curList = new ArrayList<>();
                curList.add(secondCur);
                map.put(to, curList);
            }
        }
        return map;
    }
}