package edu.ycp.cs320.acadman.model;

public class Rubric {
	private int id;
	private int meets;
	private int below;
	private int exceeds;
	private int target;
	private int measurementID;
	
	public Rubric() {

	}
	public Rubric(int id, int meets, int below, int exceeds,
			int target, int measurementID) {
		super();
		this.id = id;
		this.meets = meets;
		this.below = below;
		this.exceeds = exceeds;
		this.target = target;
		this.measurementID = measurementID;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMeets() {
		return meets;
	}
	public void setMeets(int meets) {
		this.meets = meets;
	}
	public int getBelow() {
		return below;
	}
	public void setBelow(int below) {
		this.below = below;
	}
	public int getExceeds() {
		return exceeds;
	}
	public void setExceeds(int exceeds) {
		this.exceeds = exceeds;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public int getMeasurementID() {
		return measurementID;
	}
	public void setMeasurementID(int measurementID) {
		this.measurementID = measurementID;
	}
	
	@Override
	public String toString() {
		return "Measurement [id=" + id + ", meets=" + meets + ", below="
				+ below + ", exceeds=" + exceeds + ", target="
				+ target + ", measurementID=" + measurementID +"]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rubric other = (Rubric) obj;
		if (id != other.id)
			return false;
		if (meets != other.meets)
			return false;
		if (below != other.below)
			return false;
		if (exceeds != other.exceeds)
			return false;
		if (target != other.target)
			return false;
		if (measurementID != other.measurementID)
			return false;
		return true;
	}
}
