package com.odogwudozilla.hackerrank;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Louise joined a social networking site to stay in touch with her friends. The signup page required her to input a name and a password. However, the password must be strong.
 * The website considers a password to be strong if it satisfies the following criteria:
 *
 * Its length is at least 6.
 * It contains at least one digit.
 * It contains at least one lowercase English character.
 * It contains at least one uppercase English character.
 * It contains at least one special character. The special characters are: !@#$%^&*()-+
 * She typed a random string of length 'n' in the password field but wasn't sure if it was strong. Given the string she typed, can you find the minimum number of characters she must
 * add to make her password strong?
 *
 * Note: Here's the set of types of characters in a form you can paste in your solution:
 *
 * numbers = "0123456789"
 * lower_case = "abcdefghijklmnopqrstuvwxyz"
 * upper_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
 * special_characters = "!@#$%^&*()-+"
 */
public class StrongPassword {
    public static void main(String[] args) {
        System.out.println(minimumNumber(11, "#HackerRank"));
        System.out.println(0x000F & 0x2222);
    }

    /*
     * Function Description
     * Complete the minimumNumber function in the editor below.
     * minimumNumber has the following parameters:
     *  int n: the length of the password
     *  string password: the password to test
     * Returns
     *  int: the minimum number of characters to add
     */

    public static int minimumNumber(int n, String password) {

        int numberOfCharsToAdd = 0;
        Pattern patternDigit = Pattern.compile("[0-9]");
        Matcher matcherDigit = patternDigit.matcher(password);
        Pattern patternLowercase = Pattern.compile("[a-z]");
        Matcher matcherLowercase = patternLowercase.matcher(password);
        Pattern patternUppercase = Pattern.compile("[A-Z]");
        Matcher matcherUppercase = patternUppercase.matcher(password);
        Pattern patternSpecialChar = Pattern.compile("[!@#$%^&*()-+]");
        Matcher matcherSpecialChar = patternSpecialChar.matcher(password);
        short s = 9;

        // * It contains at least one digit.
        if(!matcherDigit.find()) numberOfCharsToAdd++;
        // * It contains at least one lowercase English character.
        if(!matcherLowercase.find()) numberOfCharsToAdd++;
        // * It contains at least one uppercase English character.
        if(!matcherUppercase.find()) numberOfCharsToAdd++;
        // * It contains at least one special character. The special characters are: !@#$%^&*()-+
        if(!matcherSpecialChar.find()) numberOfCharsToAdd++;

        // Its length is at least 6
        int possibleShortfall = numberOfCharsToAdd + n;
        if(possibleShortfall < 6) {
            numberOfCharsToAdd = numberOfCharsToAdd + (6 - possibleShortfall);
        }

        // Return the minimum number of characters to make the password strong
        return numberOfCharsToAdd;
    }
}
