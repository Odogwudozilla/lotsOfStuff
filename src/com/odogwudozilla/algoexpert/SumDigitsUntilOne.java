package com.odogwudozilla.algoexpert;

public class SumDigitsUntilOne {

    public static void main(String[] args) {

        SumDigitsUntilOne sumDigitsUntilOne = new SumDigitsUntilOne();

        System.out.println(sumDigitsUntilOne.sumDigits(439230));

    }
    private int sumDigits (int input) {
        if (String.valueOf(input).length() == 1) return input;

        char[] inputArray = String.valueOf(input).toCharArray();
        int sum = 0;
        for (char elem : inputArray) {
            sum += Integer.parseInt(String.valueOf(elem));
        }

        return sumDigits(sum);
    }
}
