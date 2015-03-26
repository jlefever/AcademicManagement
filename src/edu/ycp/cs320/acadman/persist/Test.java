package edu.ycp.cs320.acadman.persist;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		System.out.println(InitialData.readYears());
		System.out.println(InitialData.readPrograms());
		System.out.println(InitialData.readOutcomes());
		System.out.println(InitialData.readIndicators());
		System.out.println(InitialData.readMeasurements());
	}

}
