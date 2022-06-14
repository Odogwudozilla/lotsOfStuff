package com.odogwudozilla.hackerrank;

/**
 * An avid hiker keeps meticulous records of their hikes. During the last hike that took exactly  steps, for every step it was noted if it was an uphill,U, or a downhill, D step.
 * Hikes always start and end at sea level, and each step up or down represents a  unit change in altitude. We define the following terms:
 *
 * A mountain is a sequence of consecutive steps above sea level, starting with a step up from sea level and ending with a step down to sea level.
 * A valley is a sequence of consecutive steps below sea level, starting with a step down from sea level and ending with a step up to sea level.
 * Given the sequence of up and down steps during a hike, find and print the number of valleys walked through.
 *
 * Function Description
 *
 * Complete the countingValleys function in the editor below.
 *
 * countingValleys has the following parameter(s):
 *
 * int steps: the number of steps on the hike
 * string path: a string describing the path
 * Returns
 *
 * int: the number of valleys traversed
 */
public class CountingValleys {
    public static void main(String[] args) {

        String pathe = "UDDDUDUU";
        System.out.println("valleys = " + countingValleys(8, pathe));

    }

    /*
     * Complete the 'countingValleys' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER steps
     *  2. STRING path
     */
    public static int countingValleys(int steps, String path) {
        int valleys = 0;
        int pathCounter = 0;

        String[] pathArr = path.split("");

        for(int i=0; i<pathArr.length; i++) {
            if (pathArr[i].equals("U")) {
                pathCounter += 1;
                // We can create a valley iff we are going uphill and hit the sea level
                if (pathCounter == 0) valleys++;
            } else if (pathArr[i].equals("D")) {
                pathCounter -= 1;
            }
        }

        return valleys;

    }
}
