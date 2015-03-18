package edu.ycp.cs320.acadman.model;

public class Measurement {

	private String description;
	private boolean isMet;

	public Measurement() {

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isMet() {
		return isMet;
	}

	public void setMet(boolean isMet) {
		this.isMet = isMet;
	}

}