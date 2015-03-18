package edu.ycp.cs320.acadman.model;

import java.util.List;

public class Outcome {
	
	private String description;
	private List<Indicator> indicators;
	private int minMet;
	
	public Outcome(){
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Indicator> getIndicators() {
		return indicators;
	}

	public void setIndicators(List<Indicator> indicators) {
		this.indicators = indicators;
	}

	public int getMinMet() {
		return minMet;
	}

	public void setMinMet(int minMet) {
		this.minMet = minMet;
	}
}
