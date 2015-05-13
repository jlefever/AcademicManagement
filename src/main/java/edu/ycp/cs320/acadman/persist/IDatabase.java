package edu.ycp.cs320.acadman.persist;

import java.util.List;

import edu.ycp.cs320.acadman.model.Indicator;
import edu.ycp.cs320.acadman.model.Measurement;
import edu.ycp.cs320.acadman.model.Outcome;
import edu.ycp.cs320.acadman.model.Program;
import edu.ycp.cs320.acadman.model.Rubric;
import edu.ycp.cs320.acadman.model.User;

public interface IDatabase {
	
	public void Setup();
	
	public List<Program> retrieveAllPrograms();
	
	public List<Program> retrievePrograms(int yearId);

	public List<Outcome> retrieveOutcomes(int programId);

	public List<Indicator> retrieveIndicators(int outcomeId);

	public List<Measurement> retrieveMeasurements(int indicatorId);
	
	public Program getProgram(int id);
	
	public Outcome getOutcome(int id);
	
	public Indicator getIndicator(int id);
	
	public Measurement getMeasurement(int id);
	
	public Program addProgram(String name, String description, int yearId);
	
	public Outcome addOutcome(String name, String description, int minMet, int programId);
	
	public Indicator addIndicator(String name, String description, int minMet, int outcomeId);
	
	public Measurement addMeasurement(String name, String description, int indicatorId);
	
	public void deleteProgram(int id);
	
	public void deleteOutcome(int id);
	
	public void deleteIndicator(int id);
	
	public void deleteMeasurement(int id);
	
	public Program editProgram(int id, String name, String description, int yearId);
	
	public Outcome editOutcome(int id, String name, String description, int minMet, int programId);
	
	public Indicator editIndicator(int id, String name, String description, int minMet, int outcomeId);
	
	public Measurement editMeasurement(int id, String name, String description, int indicatorId);
	
	public List<User> retrieveUsers();
	
	public User getUser(String username);
	
	public User addUser(String username, String email, String password, int permissions);
	
	public void deleteUser(String username);
	
	public User editUser(String username, String password, String email, int permissions);
	
	public Rubric getRubric(int measurementId);
	
	public Rubric addRubric(int measurementId, int below, int meets, int exceeds, int target);
	
	public Rubric editRubric(int measurementId, int below, int meets, int exceeds, int target);
	
	public void deleteRubric(int measurementId);
	
	public void updateMet(int measurementId, boolean met);
}
