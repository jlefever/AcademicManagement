package edu.ycp.cs320.acadman.persist;

import java.util.List;

import edu.ycp.cs320.acadman.model.Indicator;
import edu.ycp.cs320.acadman.model.Measurement;
import edu.ycp.cs320.acadman.model.Outcome;
import edu.ycp.cs320.acadman.model.Program;
import edu.ycp.cs320.acadman.model.Year;

public interface IDatabase {
	
	public void readInitialData();
	
	public List<Year> retrieveYears();

	public List<Program> retrievePrograms(int yearId);

	public List<Outcome> retrieveOutcomes(int programId);

	public List<Indicator> retrieveIndicators(int outcomeId);

	public List<Measurement> retrieveMeasurements(int indicatorId);
	
	public void addYear(int year);
	
	public void addProgram(String name, String description, int yearId);
	
	public void addOutcome(String name, String description, int minMet, int programId);
	
	public void addIndicator(String name, String description, int minMet, int outcomeId);
	
	public void addMeasurement(String name, String description, boolean isMet, int indicatorId);
}
