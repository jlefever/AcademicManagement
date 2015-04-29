package edu.ycp.cs320.acadman.controller;

import java.util.List;

import edu.ycp.cs320.acadman.model.Indicator;
import edu.ycp.cs320.acadman.model.Measurement;
import edu.ycp.cs320.acadman.model.Outcome;
import edu.ycp.cs320.acadman.model.Program;
import edu.ycp.cs320.acadman.model.User;
import edu.ycp.cs320.acadman.persist.DatabaseProvider;
import edu.ycp.cs320.acadman.persist.IDatabase;

public class Controller {
	
	public static void Setup(){
		IDatabase db = DatabaseProvider.getInstance();
		db.Setup();
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
	
	public static void deleteProgram(int id){
		IDatabase db = DatabaseProvider.getInstance();
		db.deleteProgram(id);
	}
	
	public static void deleteOutcome(int id){
		IDatabase db = DatabaseProvider.getInstance();
		db.deleteOutcome(id);
	}
	
	public static void deleteIndicator(int id){
		IDatabase db = DatabaseProvider.getInstance();
		db.deleteIndicator(id);
	}
	
	public static void deleteMeasurement(int id){
		IDatabase db = DatabaseProvider.getInstance();
		db.deleteMeasurement(id);
	}
	
	public static void addUser(String username, String email, String password, int permissions) {
		IDatabase db = DatabaseProvider.getInstance();
		db.addUser(username, email, password, permissions);
	}
	
	public static void editProgram(int id, String name, String description, int yearId){
		IDatabase db = DatabaseProvider.getInstance();
		db.editProgram(id, name, description, yearId);
	}
	
	public static void editOutcome(int id, String name, String description, int minMet, int programId){
		IDatabase db = DatabaseProvider.getInstance();
		db.editOutcome(id, name, description, minMet, programId);
	}
	public static void editIndicator(int id, String name, String description, int minMet, int outcomeId){
		IDatabase db = DatabaseProvider.getInstance();
		db.editIndicator(id, name, description, minMet, outcomeId);
	}
	public static void editMeasurement(int id, String name, String description, boolean isMet, int indicatorId){
		IDatabase db = DatabaseProvider.getInstance();
		db.editMeasurement(id, name, description, isMet, indicatorId);
	}
	
	public static void deleteUser(String username) {
		IDatabase db = DatabaseProvider.getInstance();
		db.deleteUser(username);
	}
	
	public static void editUser(String username, String email, String password, int permissions) {
		IDatabase db = DatabaseProvider.getInstance();
		db.editUser(username, email, password, permissions);
	}
	
	public static User getUser(String username) {
		IDatabase db = DatabaseProvider.getInstance();
		return db.getUser(username);
	}
	
	public static Program getProgram(int id) {
		IDatabase db = DatabaseProvider.getInstance();
		return db.getProgram(id);
	}
	
	public static Outcome getOutcome(int id) {
		IDatabase db = DatabaseProvider.getInstance();
		return db.getOutcome(id);
	}
	
	public static Indicator getIndicator(int id) {
		IDatabase db = DatabaseProvider.getInstance();
		return db.getIndicator(id);
	}
	
	public static Measurement getMeasurement(int id) {
		IDatabase db = DatabaseProvider.getInstance();
		return db.getMeasurement(id);
	}
	
	public static List<User> getUsers(){
		IDatabase db = DatabaseProvider.getInstance();
		return db.retrieveUsers();
	}
}
