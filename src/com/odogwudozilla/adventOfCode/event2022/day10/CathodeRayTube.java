package com.odogwudozilla.adventOfCode.event2022.day10;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.odogwudozilla.adventOfCode.IinitiateGeneralFunctions;

import static com.odogwudozilla.adventOfCode.event2022.day10.SignalInstructions.ADD_X;
import static com.odogwudozilla.adventOfCode.event2022.day10.SignalInstructions.NOOP;

public class CathodeRayTube implements IinitiateGeneralFunctions {
    // the absolute path to the input data
    private static final Path filePath = Paths.get(ABSOLUTE_PATH + "\\src\\com\\odogwudozilla\\adventOfCode\\event2022\\day10\\inputCathodeRayTube.txt");
    private static final int TWENTIETH = 20;
    private static final int SIXTIETH = 60;
    private static final int HUNDREDTH = 100;
    private static final int ONE_FORTIETH = 140;
    private static final int ONE_EIGHTIETH = 180;
    private static final int TWO_TWENTIETH = 220;

    private static int valueAtCount20;
    private static int valueAtCount60;
    private static int valueAtCount100;
    private static int valueAtCount140;
    private static int valueAtCount180;
    private static int valueAtCount220;


    public static void main(String[] args) {
        findSumOfSignalStrength();

    }

    public static void findSumOfSignalStrength()  {

        List<String> fileContent = new CathodeRayTube().copyFromFile(filePath);

        int totalCycleCount = 0;
        int registerValue = 1;
        int totalStrength;

        for (String item : fileContent) {
            String [] itemArray = item.split(" ");
            String currentInstruction = item.split(" ")[0];
            int currentInstructionValue = 0;
            if (itemArray.length > 1){
                currentInstructionValue = Integer.parseInt(item.split(" ")[1]);
            }

            if (NOOP.instruction.equals(currentInstruction)) {
                for (int i=0; i < NOOP.cycleTime; i++) {
                    totalCycleCount += 1;
                    determineCountCycle(totalCycleCount, registerValue);
                }
                continue;
            }
            if (ADD_X.instruction.equals(currentInstruction)) {
                for (int i=0; i < ADD_X.cycleTime; i++) {
                    totalCycleCount += 1;
                    determineCountCycle(totalCycleCount, registerValue);
                }
                registerValue += currentInstructionValue;
            }


        }
        totalStrength = valueAtCount20 + valueAtCount60 + valueAtCount100 + valueAtCount140 + valueAtCount180 + valueAtCount220;

        System.out.println("The sum of these signal strengths is: " + totalStrength);
    }

    private static void determineCountCycle(int currentCount, int currentValue) {
        switch (currentCount) {
            case TWENTIETH:
                valueAtCount20 = TWENTIETH * currentValue;
                System.out.println(TWENTIETH + "th value is :" + valueAtCount20);
                break;
            case SIXTIETH:
                valueAtCount60 = SIXTIETH * currentValue;
                System.out.println(SIXTIETH + "th value is :" + valueAtCount60);
                break;
            case HUNDREDTH:
                valueAtCount100 = HUNDREDTH * currentValue;
                System.out.println(HUNDREDTH + "th value is :" + valueAtCount100);
                break;
            case ONE_FORTIETH:
                valueAtCount140 = ONE_FORTIETH * currentValue;
                System.out.println(ONE_FORTIETH + "th value is :" + valueAtCount140);
                break;
            case ONE_EIGHTIETH:
                valueAtCount180 = ONE_EIGHTIETH * currentValue;
                System.out.println(ONE_EIGHTIETH + "th value is :" + valueAtCount180);
                break;
            case TWO_TWENTIETH:
                valueAtCount220 = TWO_TWENTIETH * currentValue;
                System.out.println(TWO_TWENTIETH + "th value is :" + valueAtCount220);
                break;
        }
    }


    @Override
    public List<String> copyFromFile(Path filepath) {
        return IinitiateGeneralFunctions.super.copyFromFile(filepath);
    }

}
