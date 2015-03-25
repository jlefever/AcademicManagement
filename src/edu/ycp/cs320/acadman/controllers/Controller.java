package edu.ycp.cs320.acadman.controllers;

import java.util.List;

import edu.ycp.cs320.acadman.model.Indicator;
import edu.ycp.cs320.acadman.model.Measurement;
import edu.ycp.cs320.acadman.model.Outcome;
import edu.ycp.cs320.acadman.model.Program;
import edu.ycp.cs320.acadman.model.Year;
import edu.ycp.cs320.acadman.persist.DatabaseProvider;
import edu.ycp.cs320.acadman.persist.IDatabase;

public class Controller {
	public static List<Year> getYears() {
		IDatabase db = DatabaseProvider.getInstance();
		return db.retrieveYears();
	}
	
	public static List<Program> getPrograms(int yearId) {
		IDatabase db = DatabaseProvider.getInstance();
		return db.retrievePrograms(yearId);
	}
	
	public static List<Outcome> getOutcomes(int programId) {
		IDatabase db = DatabaseProvider.getInstance();
		return db.retrieveOutcomes(programId);
	}
	
	public static List<Indicator> getIndicators(int outcomeId) {
		IDatabase db = DatabaseProvider.getInstance();
		return db.retrieveIndicators(outcomeId);
	}
	
	public static List<Measurement> getMeasurements(int indicatorId) {
		IDatabase db = DatabaseProvider.getInstance();
		return db.retrieveMeasurements(indicatorId);
	}
	
	public static void addYear(int year) {
		IDatabase db = DatabaseProvider.getInstance();
		db.addYear(year);
	}
	
	public static void addProgram(String name, String description, int yearId) {
		IDatabase db = DatabaseProvider.getInstance();
		db.addProgram(name, description, yearId);
	}
	
	public static void addOutcome(String name, String description, int minMet, int programId) {
		IDatabase db = DatabaseProvider.getInstance();
		db.addOutcome(name, description, minMet, programId);
	}
	
	public static void addIndicator(String name, String description, int minMet, int outcomeId) {
		IDatabase db = DatabaseProvider.getInstance();
		db.addIndicator(name, description, minMet, outcomeId);
	}
	
	public static void addMeasurement(String name, String description, boolean isMet, int indicatorId) {
		IDatabase db = DatabaseProvider.getInstance();
		db.addMeasurement(name, description, isMet, indicatorId);
	}
}
