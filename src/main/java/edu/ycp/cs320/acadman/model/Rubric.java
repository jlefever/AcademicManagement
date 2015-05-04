package edu.ycp.cs320.acadman.model;

public class Rubric {
	private int meets;
	private int below;
	private int exceeds;
	private int target;
	private int measurementId;
	
	public Rubric() {

	}
	public Rubric(int measurementId, int meets, int below, int exceeds,
			int target) {
		super();
		this.meets = meets;
		this.below = below;
		this.exceeds = exceeds;
		this.target = target;
		this.measurementId = measurementId;
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
	public int getMeasurementId() {
		return measurementId;
	}
	public void setMeasurementId(int measurementId) {
		this.measurementId = measurementId;
	}
	
	@Override
	public String toString() {
		return "Measurement [meets=" + meets + ", below="
				+ below + ", exceeds=" + exceeds + ", target="
				+ target + ", measurementId=" + measurementId +"]";
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
		if (meets != other.meets)
			return false;
		if (below != other.below)
			return false;
		if (exceeds != other.exceeds)
			return false;
		if (target != other.target)
			return false;
		if (measurementId != other.measurementId)
			return false;
		return true;
	}
}
