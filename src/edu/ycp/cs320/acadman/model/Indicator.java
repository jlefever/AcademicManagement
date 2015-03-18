package edu.ycp.cs320.acadman.model;

import java.util.List;

public class Indicator {

	private String description;
	private List<Measurement> measurements;
	private int minMet;

	public Indicator() {

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Measurement> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}

	public int getMinMet() {
		return minMet;
	}

	public void setMinMet(int minMet) {
		this.minMet = minMet;
	}
}