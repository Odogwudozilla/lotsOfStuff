package com.odogwudozilla.adventOfCode.event2022.day3;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.odogwudozilla.adventOfCode.IinitiateGeneralFunctions;

public class RucksackReorganization implements IinitiateGeneralFunctions {
    // the absolute path to the input data
    private static final Path filePath = Paths.get(ABSOLUTE_PATH + "\\src\\com\\odogwudozilla\\adventOfCode\\event2022\\day3\\inputRucksackReorganization.txt");

    public static void main(String[] args) {
        //determineSumOfPriorities();
        determineSumOfThreePriorities();
    }

    public static void determineSumOfPriorities()  {
        // create a map of alphabets (both uppercase and lowercase) with their priorities
        Map<Character, Integer> lowerCaseAlpabets = new HashMap<Character, Integer>();
        Map<Character, Integer> upperCaseAlpabets = new HashMap<Character, Integer>();
        int totalSum = 0;

        int priorityValue = 1;
        for (char uc = 'a'; uc <= 'z'; uc++) {
            lowerCaseAlpabets.put(uc, priorityValue);
            priorityValue++;
        }
        for (char uc = 'A'; uc <= 'Z'; uc++) {
            upperCaseAlpabets.put(uc, priorityValue);
            priorityValue++;
        }

        List<String> fileContent = new RucksackReorganization().copyFromFile(filePath);

        int itemCount = 1;
        for (String item : fileContent) {
            // split the string in half
            System.out.println("-------------------------");
            System.out.println("Item no " + itemCount + ": " + item);
            int halfLength = item.length() / 2;
            String s1 = item.substring(0, halfLength);
            String s2 = item.substring(halfLength);

            System.out.println("Side A: " + s1);
            System.out.println("Side B: " + s2);

            // find the common letter
            for (char c : s1.toCharArray()) {
                if (s2.indexOf(c) != -1) {
                    System.out.println(c + " is a common letter.");
                    // Check the case
                    int currentPriority = 0;
                    if (Character.isLowerCase(c)){
                        currentPriority = lowerCaseAlpabets.get(c);
                        System.out.println("The priority for char '" + c + "' is " + currentPriority);
                        totalSum += currentPriority;
                    }
                    if (Character.isUpperCase(c)){
                        currentPriority = upperCaseAlpabets.get(c);
                        System.out.println("The priority for char '" + c + "' is " + currentPriority);
                        totalSum += currentPriority;
                    }
                    System.out.println("We found a match so we quit checking this particular loop");
                    break;
                }
            }
            itemCount++;
        }

        System.out.println("The sum of the priorities of the item types is '" + totalSum +"'");

    }


    public static void determineSumOfThreePriorities()  {
        // create a map of alphabets (both uppercase and lowercase) with their priorities
        Map<Character, Integer> lowerCaseAlpabets = new HashMap<Character, Integer>();
        Map<Character, Integer> upperCaseAlpabets = new HashMap<Character, Integer>();
        int totalSum = 0;

        int priorityValue = 1;
        for (char uc = 'a'; uc <= 'z'; uc++) {
            lowerCaseAlpabets.put(uc, priorityValue);
            priorityValue++;
        }
        for (char uc = 'A'; uc <= 'Z'; uc++) {
            upperCaseAlpabets.put(uc, priorityValue);
            priorityValue++;
        }

        List<String> fileContent = new RucksackReorganization().copyFromFile(filePath);

        Queue<String> queueOfThree = new ArrayDeque<>();

        for (String item : fileContent) {
            // Group the list in 3s
            System.out.println("-------------------------");
            queueOfThree.offer(item);
            if (queueOfThree.size() != 3) {
                continue;
            }

            // find the common letter
            while (!queueOfThree.isEmpty()) {
                char[] s1 = queueOfThree.poll().toCharArray();
                String s2 = queueOfThree.poll();
                String s3 = queueOfThree.poll();

                for (char c : s1) {
                    if (s2.indexOf(c) != -1 && s3.indexOf(c) != -1) {
                        System.out.println(c + " is a common letter.");
                        // Check the case
                        int currentPriority = 0;
                        if (Character.isLowerCase(c)) {
                            currentPriority = lowerCaseAlpabets.get(c);
                            System.out.println("The priority for char '" + c + "' is " + currentPriority);
                            totalSum += currentPriority;
                        }
                        if (Character.isUpperCase(c)) {
                            currentPriority = upperCaseAlpabets.get(c);
                            System.out.println("The priority for char '" + c + "' is " + currentPriority);
                            totalSum += currentPriority;
                        }
                        System.out.println("We found a match so we quit checking this particular loop");
                        queueOfThree.clear();
                        break;
                    }
                }

            }
        }

        System.out.println("The sum of the priorities of the item types is '" + totalSum +"'");

    }

    @Override
    public List<String> copyFromFile(Path filepath) {
        return IinitiateGeneralFunctions.super.copyFromFile(filepath);
    }

}
