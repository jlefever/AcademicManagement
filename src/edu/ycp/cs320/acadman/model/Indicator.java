package edu.ycp.cs320.acadman.model;

public class Indicator {

	private int id;
	private String name;
	private String description;
	private int minMet;
	private int outcomeId;

	public Indicator() {

	}

	public Indicator(int id, String name, String description, int minMet,
			int outcomeId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.minMet = minMet;
		this.outcomeId = outcomeId;
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

	public int getOutcomeId() {
		return outcomeId;
	}

	public void setOutcomeId(int outcomeId) {
		this.outcomeId = outcomeId;
	}

	@Override
	public String toString() {
		return "Indicator [id=" + id + ", name=" + name + ", description="
				+ description + ", minMet=" + minMet + ", outcomeId="
				+ outcomeId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + minMet;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + outcomeId;
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
		Indicator other = (Indicator) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (minMet != other.minMet)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (outcomeId != other.outcomeId)
			return false;
		return true;
	}
}