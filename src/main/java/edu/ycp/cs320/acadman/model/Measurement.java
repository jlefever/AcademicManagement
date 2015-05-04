package edu.ycp.cs320.acadman.model;

public class Measurement {

	private int id;
	private String name;
	private String description;
	private boolean isMet;
	private int indicatorId;

	public Measurement() {

	}

	public Measurement(int id, String name, String description, boolean isMet,
			int indicatorId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.isMet = isMet;
		this.indicatorId = indicatorId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + indicatorId;
		result = prime * result + (isMet ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Measurement other = (Measurement) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (indicatorId != other.indicatorId)
			return false;
		if (isMet != other.isMet)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}