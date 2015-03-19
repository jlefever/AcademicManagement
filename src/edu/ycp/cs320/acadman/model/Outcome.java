package edu.ycp.cs320.acadman.model;

public class Outcome {

	private int id;
	private String name;
	private String description;
	private int minMet;
	private int programId;

	public Outcome() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMinMet() {
		return minMet;
	}

	public void setMinMet(int minMet) {
		this.minMet = minMet;
	}

	public int getProgramId() {
		return programId;
	}

	public void setProgramId(int programId) {
		this.programId = programId;
	}

	@Override
	public String toString() {
		return "Outcome [id=" + id + ", name=" + name + ", description="
				+ description + ", minMet=" + minMet + ", programId="
				+ programId + "]";
	}

}
