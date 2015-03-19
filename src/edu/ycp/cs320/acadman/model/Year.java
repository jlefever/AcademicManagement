package edu.ycp.cs320.acadman.model;

public class Year {

	private int id;
	private int year;

	public Year() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Year [id=" + id + ", year=" + year + "]";
	}
}