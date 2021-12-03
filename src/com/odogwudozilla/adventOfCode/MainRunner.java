package com.odogwudozilla.adventOfCode;

import java.net.URISyntaxException;

import com.odogwudozilla.adventOfCode.sonarSweep.SonarSweep;

public class MainRunner {

	public static void main(String[] args) throws URISyntaxException {
		SonarSweep sonar = new SonarSweep();
		System.out.println("Total matched: " + (sonar.calculateIncreaseCount()));
	}

}
