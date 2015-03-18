package edu.ycp.cs320.acadman.model;

import java.util.List;

public class Program {
	
	private String name;
	private List<Outcome> outcomes;
	
	public Program(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Outcome> getOutcomes() {
		return outcomes;
	}

	public void setOutcomes(List<Outcome> outcomes) {
		this.outcomes = outcomes;
	}
}