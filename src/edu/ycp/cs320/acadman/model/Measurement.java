package edu.ycp.cs320.acadman.model;

public class Measurement {

	private int id;
	private String name;
	private String description;
	private boolean isMet;
	private int indicatorId;

	public Measurement() {

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

	public boolean isMet() {
		return isMet;
	}

	public void setMet(boolean isMet) {
		this.isMet = isMet;
	}

	public int getIndicatorId() {
		return indicatorId;
	}

	public void setIndicatorId(int indicatorId) {
		this.indicatorId = indicatorId;
	}

	@Override
	public String toString() {
		return "Measurement [id=" + id + ", name=" + name + ", description="
				+ description + ", isMet=" + isMet + ", indicatorId="
				+ indicatorId + "]";
	}

}