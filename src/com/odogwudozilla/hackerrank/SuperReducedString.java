package com.odogwudozilla.hackerrank;

import java.util.ArrayList;
import java.util.List;

public class SuperReducedString {
    public static void main(String[] args) {

        System.out.println(reduceStrings("baab"));

    }

    public static  String reduceStrings (String inputString) {
        //Empty and single-letter strings return same
        if(inputString.isEmpty() || inputString.length() == 1) return inputString;

        char[] newChar = inputString.toCharArray();
        List<String> listStr = new ArrayList<>();
        for(char elem : newChar) {
            listStr.add(String.valueOf(elem));
        }

        for (int index=listStr.size()-1; index>=0; index--) {

            if (index == 0) break;

            String first = listStr.get(index);
            String second = listStr.get(index-1);

            if(first.equals(second)) {
                listStr.remove(index);
                listStr.remove(index-1);
                index = listStr.size();
            }

        }

        if (listStr.isEmpty()) {
            return "Empty String";
        }
        return  String.join("", listStr);
    }
}
