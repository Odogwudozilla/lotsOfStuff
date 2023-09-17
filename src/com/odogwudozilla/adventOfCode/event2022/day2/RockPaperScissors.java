package com.odogwudozilla.adventOfCode.event2022.day2;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.odogwudozilla.adventOfCode.IinitiateGeneralFunctions;

public class RockPaperScissors implements IinitiateGeneralFunctions {
    // the absolute path to the input data
    private static final Path filePath = Paths.get(ABSOLUTE_PATH + "\\src\\com\\odogwudozilla\\adventOfCode\\event2022\\day2\\inputRockPaperScissors.txt");
    private static final String THEIR_ROCK = "A";
    private static final String THEIR_PAPER = "B";
    private static final String THEIR_SCISSORS = "C";
    private static final String MY_ROCK = "X";
    private static final String I_SHOULD_LOSE = "X";
    private static final String MY_PAPER = "Y";
    private static final String I_SHOULD_DRAW = "Y";
    private static final String MY_SCISSORS = "Z";
    private static final String I_SHOULD_WIN = "Z";
    private static final int MY_ROCK_SCORE = 1;
    private static final int MY_PAPER_SCORE = 2;
    private static final int MY_SCISSORS_SCORE = 3;
    private static final int WIN_GAME = 6;
    private static final int DRAW_GAME = 3;


    public static void main(String[] args) {
        //findSumOfSignalStrength();
        findHighestScorePart2();
    }

    public static void findHighestScore()  {

        List<String> fileContent = new RockPaperScissors().copyFromFile(filePath);

        int currentPoints = 0;

        for (String item : fileContent) {
            String theirCurrentPlay = item.split(" ")[0];
            String myCurrentPlay = item.split(" ")[1];
            // win situation
            if (theirCurrentPlay.equals(THEIR_SCISSORS) && myCurrentPlay.equals(MY_ROCK)
                    || theirCurrentPlay.equals(THEIR_PAPER) && myCurrentPlay.equals(MY_SCISSORS)
                    || theirCurrentPlay.equals(THEIR_ROCK) && myCurrentPlay.equals(MY_PAPER)) {
                currentPoints += (WIN_GAME + calculatePoints(myCurrentPlay));
                continue;
            }
            if (theirCurrentPlay.equals(THEIR_ROCK) && myCurrentPlay.equals(MY_ROCK)
                    || theirCurrentPlay.equals(THEIR_PAPER) && myCurrentPlay.equals(MY_PAPER)
                    || theirCurrentPlay.equals(THEIR_SCISSORS) && myCurrentPlay.equals(MY_SCISSORS)) {

                currentPoints += (DRAW_GAME + calculatePoints(myCurrentPlay));
                continue;
            } else {
                currentPoints += calculatePoints(myCurrentPlay);
            }

        }

        System.out.println("Total points:-> " + currentPoints);

    }


    public static void findHighestScorePart2()  {

        List<String> fileContent = new RockPaperScissors().copyFromFile(filePath);

        int currentPoints = 0;

        for (String item : fileContent) {
            String theirCurrentPlay = item.split(" ")[0];
            String myCurrentPlay = item.split(" ")[1];

            switch (myCurrentPlay) {
                case I_SHOULD_WIN:
                    myCurrentPlay = whatShouldIPlayToWin(theirCurrentPlay);
                    // win situation
                    if (theirCurrentPlay.equals(THEIR_SCISSORS) && myCurrentPlay.equals(MY_ROCK)
                            || theirCurrentPlay.equals(THEIR_PAPER) && myCurrentPlay.equals(MY_SCISSORS)
                            || theirCurrentPlay.equals(THEIR_ROCK) && myCurrentPlay.equals(MY_PAPER)) {
                        currentPoints += (WIN_GAME + calculatePoints(myCurrentPlay));
                    }
                    break;
                case I_SHOULD_DRAW:
                    // draw situation
                    myCurrentPlay = whatShouldIPlayToDraw(theirCurrentPlay);
                    currentPoints += (DRAW_GAME + calculatePoints(myCurrentPlay));
                    break;
                case I_SHOULD_LOSE:
                    myCurrentPlay = whatShouldIPlayToLose(theirCurrentPlay);
                    currentPoints += calculatePoints(myCurrentPlay);
                    break;
            }

        }

        System.out.println("Total point 2nd parts:-> " + currentPoints);

    }

    static String whatShouldIPlayToWin(String theirPlay) {
        switch (theirPlay) {
            case THEIR_SCISSORS: return MY_ROCK;
            case THEIR_PAPER: return MY_SCISSORS;
            case THEIR_ROCK: return MY_PAPER;
        }
        throw new IllegalArgumentException("This is an undesirable situation");
    }
    static String whatShouldIPlayToLose(String theirPlay) {
        switch (theirPlay) {
            case THEIR_SCISSORS: return MY_PAPER;
            case THEIR_PAPER: return MY_ROCK;
            case THEIR_ROCK: return MY_SCISSORS;
        }
        throw new IllegalArgumentException("This is an undesirable situation");
    }

    static String whatShouldIPlayToDraw(String theirPlay) {
        switch (theirPlay) {
            case THEIR_SCISSORS: return MY_SCISSORS;
            case THEIR_PAPER: return MY_PAPER;
            case THEIR_ROCK: return MY_ROCK;
        }
        throw new IllegalArgumentException("This is an undesirable situation");
    }

    static int calculatePoints(String myPlay) {
        switch (myPlay) {
            case MY_ROCK: return MY_ROCK_SCORE;
            case MY_PAPER: return MY_PAPER_SCORE;
            case MY_SCISSORS: return MY_SCISSORS_SCORE;
        }
        return 0;
    }



    @Override
    public List<String> copyFromFile(Path filepath) {
        return IinitiateGeneralFunctions.super.copyFromFile(filepath);
    }

}
