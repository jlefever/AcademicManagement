package edu.ycp.cs320.acadman.model;

public class Outcome {

	private int id;
	private String name;
	private String description;
	private int minMet;
	private int programId;

	public Outcome() {

	}

	public Outcome(int id, String name, String description, int minMet,
			int programId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.minMet = minMet;
		this.programId = programId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + minMet;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + programId;
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
		Outcome other = (Outcome) obj;
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
		if (programId != other.programId)
			return false;
		return true;
	}

}
