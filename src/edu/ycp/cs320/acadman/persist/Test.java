package edu.ycp.cs320.acadman.persist;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		System.out.println(InitialData.getYears());
		System.out.println(InitialData.getPrograms());
		System.out.println(InitialData.getOutcomes());
		System.out.println(InitialData.getIndicators());
		System.out.println(InitialData.getMeasurements());
	}

}
