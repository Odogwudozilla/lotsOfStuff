package com.odogwudozilla.adventOfCode.event2022.day1;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.odogwudozilla.adventOfCode.IinitiateGeneralFunctions;

public class CalorieCounting implements IinitiateGeneralFunctions {
    // the absolute path to the input data
    private static final Path filePath = Paths.get(ABSOLUTE_PATH + "\\src\\com\\odogwudozilla\\adventOfCode\\event2022\\day1\\inputCalorieCounting.txt");

    public static void main(String[] args) {
        findMostCalories();
    }

    public static void findMostCalories ()  {

        List<String> fileContent = new CalorieCounting().copyFromFile(filePath);

       List<Integer> sections = new ArrayList<>();
       int total = 0;

        for (String item : fileContent) {
            if (item.isEmpty()) {
                sections.add(total);
                total = 0;
                continue;
            }
            total += Integer.parseInt(item);
        }
        int count = 3;
        int highest = 0;
        while (count > 0) {
            int currentHighest = sections.stream().max(Integer::compare).get();
            System.out.println("current highest:-> " + currentHighest);
            sections.remove((Object)currentHighest);
            highest += currentHighest;
            count -= 1;

        }
        System.out.println("total Highest:-> " + highest);

    }

    @Override
    public List<String> copyFromFile(Path filepath) {
        return IinitiateGeneralFunctions.super.copyFromFile(filepath);
    }

}
