package com.odogwudozilla.adventOfCode.event2021.dive;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.odogwudozilla.adventOfCode.IinitiateGeneralFunctions;

/**
 * --- Day 2: Dive! ---
 * AdventOfcode: https://adventofcode.com/2021/day/2
 */
public class Dive  implements IinitiateGeneralFunctions {
	// the absolute path to the input data
	private static final Path filepath = Paths.get(ABSOLUTE_PATH + "\\src\\com\\odogwudozilla\\adventOfCode\\dive\\inputDive.txt");

	public static final String MOVE_FORWARD = "forward";
	public static final String MOVE_DOWN = "down";
	public static final String MOVE_UP = "up";
	private int finalHorizontalPosition;
	private int finalDepthLevel;
	private int aim;
	private List<String> listInput = new ArrayList<>();


	/**
	 * It seems like the submarine can take a series of commands like forward 1, down 2, or up 3:
	 *
	 * forward X increases the horizontal position by X units.
	 * down X increases the depth by X units.
	 * up X decreases the depth by X units.
	 * --- Part Two ---
	 * Based on your calculations, the planned course doesn't seem to make any sense. You find the submarine manual and discover that the process is actually slightly more complicated.
	 *
	 * In addition to horizontal position and depth, you'll also need to track a third value, aim, which also starts at 0. The commands also mean something entirely different than you first thought:
	 *
	 * down X increases your aim by X units.
	 * up X decreases your aim by X units.
	 * forward X does two things:
	 * It increases your horizontal position by X units.
	 * It increases your depth by your aim multiplied by X.
	 * @param isWithAim indicates if 'aim' is included in the calculations
	 * @return the final horizontal and depth position of the submarine
	 */
	public Integer calculateHorizontalByDepthPosition(boolean isWithAim) {
		// empty the list of data input
		listInput.clear();

		// pull data from file
		listInput = copyFromFile(filepath);

		// Calculate the movements
		if (isWithAim) {
			reset();
			calculateMovementsWithAim(listInput);
		} else {
			reset();
			calculateMovements(listInput);
		}

		return (finalHorizontalPosition * finalDepthLevel);

	}

	/**
	 * Calculate the movements as represented by the input data.
	 * Checks 'forward', 'down' and 'up'.
	 * Updates horizontal position and depth.
	 * @param inputList the list containing individual movements and the values
	 */
	private void calculateMovements(List<String> inputList) {
		for(String item : inputList) {
			String movement = item.trim().split("\\s+")[0];
			int movementValue = Integer.parseInt(item.trim().split("\\s+")[1]);
			switch (movement) {
				case MOVE_FORWARD: finalHorizontalPosition += movementValue; break;
				case MOVE_DOWN: finalDepthLevel += movementValue; break;
				case MOVE_UP: finalDepthLevel -= movementValue; break;
			default: throw new IllegalStateException ("This should not happen. Crosscheck your code or input data");
			}
		}

	}
	/**
	 * Calculate the movements as represented by the input data.
	 * Checks 'forward', 'down', 'up' and 'aim'.
	 * Updates horizontal position and depth.
	 * @param inputList the list containing individual movements and the values
	 */
	private void calculateMovementsWithAim(List<String> inputList) {
		for(String item : inputList) {
			String movement = item.trim().split("\\s+")[0];
			int movementValue = Integer.parseInt(item.trim().split("\\s+")[1]);
			switch (movement) {
				case MOVE_FORWARD:
					finalHorizontalPosition += movementValue;
					finalDepthLevel += (aim * movementValue);
					break;
				case MOVE_DOWN: aim += movementValue; break;
				case MOVE_UP: aim -= movementValue; break;
			default: throw new IllegalStateException ("This should not happen. Crosscheck your code or input data");
			}
		}

	}

	/**
	 * resets the instance variables.
	 */
	void reset(){
		finalHorizontalPosition = 0;
		finalDepthLevel = 0;
		aim = 0;
	}



}
