package edu.ycp.cs320.acadman.model;

import java.util.List;

public class Year {

	private int thisYear;
	private List<Program> programs;
	
	public Year(){
		
	}

	public int getThisYear() {
		return thisYear;
	}

	public void setThisYear(int thisYear) {
		this.thisYear = thisYear;
	}

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}
}