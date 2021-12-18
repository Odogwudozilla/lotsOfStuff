package com.odogwudozilla.adventOfCode;

import java.net.URISyntaxException;

import com.odogwudozilla.adventOfCode.binaryDiagnostic.BinaryDiagnostic;
import com.odogwudozilla.adventOfCode.dive.Dive;
import com.odogwudozilla.adventOfCode.sonarSweep.SonarSweep;

public class MainRunner {

	public static void main(String[] args) throws URISyntaxException {
//		SonarSweep sonar = new SonarSweep();
//		System.out.println("Total measurements larger than the previous measurement: " +
//				(sonar.calculateIncreaseCount())
//		);
//		System.out.println("Total sums larger than the previous sum: " +
//				(sonar.calculateSlidingWindow())
//		);
//
//		demarcation();
//
//		Dive diver = new Dive();
//		System.out.println("The final horizontal position multiplied by the final depth (Without aim):" +
//				diver.calculateHorizontalByDepthPosition(false)
//		);
//		System.out.println("The final horizontal position multiplied by the final depth (With aim):" +
//				diver.calculateHorizontalByDepthPosition(true)
//		);

		demarcation();

		BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic();
		System.out.println("Power consumption of the submarine: " + binaryDiagnostic.calculateGammaAndEpsilonRate());

	}

	public static void demarcation(){
		System.out.println("_________________________");
		System.out.println();
	}

}
