package com.odogwudozilla.hackerrank;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There is a large pile of socks that must be paired by color. Given an array of integers representing the color of each sock, determine how many pairs of socks with matching
 * colors there are.
 *
 * Function Description
 *
 * Complete the sockMerchant function in the editor below.
 *
 * sockMerchant has the following parameter(s):
 *
 * int n: the number of socks in the pile
 * int ar[n]: the colors of each sock
 * Returns
 *
 * int: the number of pairs
 */
public class SalesByMatch {
    public static void main(String[] args) {

    }

    /*
     * Complete the 'sockMerchant' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY ar
     */
    public static int sockMerchant(int n, List<Integer> ar) {
        int result = 0;

        // Put in a hash
        Map<Integer, Integer> arHash = new HashMap<>();
        for (int elem : ar) {
            if (!arHash.containsKey(elem)) {
                arHash.put(elem, 1);
            } else {
                arHash.computeIfPresent(elem, (key, value) -> value + 1);
            }
        }

        // count the pairs
        for (Integer eleme : arHash.keySet()) {
            result += (arHash.get(eleme)/2);
        }

        return result;

    }
}
