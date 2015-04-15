package edu.ycp.cs320.acadman.persist;

import java.util.List;

import edu.ycp.cs320.acadman.model.Indicator;
import edu.ycp.cs320.acadman.model.Measurement;
import edu.ycp.cs320.acadman.model.Outcome;
import edu.ycp.cs320.acadman.model.Program;

public interface IDatabase {
	
	public void readInitialData();
	
	public List<Program> retrievePrograms(int yearId);

	public List<Outcome> retrieveOutcomes(int programId);

	public List<Indicator> retrieveIndicators(int outcomeId);

	public List<Measurement> retrieveMeasurements(int indicatorId);
	
	public Program addProgram(String name, String description, int yearId);
	
	public Outcome addOutcome(String name, String description, int minMet, int programId);
	
	public Indicator addIndicator(String name, String description, int minMet, int outcomeId);
	
	public Measurement addMeasurement(String name, String description, boolean isMet, int indicatorId);
	
	public void deleteProgram(int id);
	
	public void deleteOutcome(int id);
	
	public void deleteIndicator(int id);
	
	public void deleteMeasurement(int id);
	
	public Program editProgram(int id, String name, String description, int yearId);
	
	public Outcome editOutcome(int id, String name, String description, int minMet, int programId);
	
	public Indicator editIndicator(int id, String name, String description, int minMet, int outcomeId);
	
	public Measurement editMeasurement(int id, String name, String description, boolean isMet, int indicatorId);
}
