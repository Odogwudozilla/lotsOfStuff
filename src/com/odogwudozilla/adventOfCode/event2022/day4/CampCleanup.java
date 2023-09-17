package com.odogwudozilla.adventOfCode.event2022.day4;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.odogwudozilla.adventOfCode.IinitiateGeneralFunctions;

public class CampCleanup implements IinitiateGeneralFunctions {
    // the absolute path to the input data
    private static final Path filePath = Paths.get(ABSOLUTE_PATH + "\\src\\com\\odogwudozilla\\adventOfCode\\event2022\\day4\\inputCampCleanup.txt");

    public static void main(String[] args) {
        //determineSumOfPriorities();
    }

    public static void determineAssignmentPairs()  {

        List<String> fileContent = new CampCleanup().copyFromFile(filePath);
        for (String item : fileContent) {
            String [] itemArray = item.split(",");
            String rightRange = itemArray[0];
            String leftRange = itemArray[1];
            Integer rightRangeStart = Integer.parseInt(rightRange.split("-")[0]);
            Integer rightRangeEnd = Integer.parseInt(rightRange.split("-")[1]);
            Integer leftRangeStart = Integer.parseInt(leftRange.split("-")[0]);
            Integer leftRangeEnd = Integer.parseInt(leftRange.split("-")[1]);

            // Pair condition
            boolean startOk = rightRangeStart >= leftRangeStart;
        }


    }


    @Override
    public List<String> copyFromFile(Path filepath) {
        return IinitiateGeneralFunctions.super.copyFromFile(filepath);
    }

}
