package edu.ycp.cs320.acadman.model;

public class Program {

	private int id;
	private String name;
	private String description;
	private int yearId;

	public Program() {

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

	public int getYearId() {
		return yearId;
	}

	public void setYearId(int yearId) {
		this.yearId = yearId;
	}

	@Override
	public String toString() {
		return "Program [id=" + id + ", name=" + name + ", description="
				+ description + ", yearId=" + yearId + "]";
	}

}