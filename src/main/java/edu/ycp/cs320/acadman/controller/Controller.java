package edu.ycp.cs320.acadman.controller;

import java.util.List;

import edu.ycp.cs320.acadman.model.Indicator;
import edu.ycp.cs320.acadman.model.Measurement;
import edu.ycp.cs320.acadman.model.Outcome;
import edu.ycp.cs320.acadman.model.Program;
import edu.ycp.cs320.acadman.model.Rubric;
import edu.ycp.cs320.acadman.model.User;
import edu.ycp.cs320.acadman.persist.DatabaseProvider;
import edu.ycp.cs320.acadman.persist.IDatabase;

public class Controller {
	
	public static void Setup(){
		IDatabase db = DatabaseProvider.getInstance();
		db.Setup();
	}
	
	public static List<Program> retrievePrograms(int yearId) {
		IDatabase db = DatabaseProvider.getInstance();
		return db.retrievePrograms(yearId);
	}
	
	public static List<Outcome> retrieveOutcomes(int programId) {
		IDatabase db = DatabaseProvider.getInstance();
		return db.retrieveOutcomes(programId);
	}
	
	public static List<Indicator> retrieveIndicators(int outcomeId) {
		IDatabase db = DatabaseProvider.getInstance();
		return db.retrieveIndicators(outcomeId);
	}
	
	public static List<Measurement> retrieveMeasurements(int indicatorId) {
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
	
	public static void addMeasurement(String name, String description, int indicatorId) {
		IDatabase db = DatabaseProvider.getInstance();
		Measurement temp = db.addMeasurement(name, description, indicatorId);
		db.addRubric(temp.getId(), 0, 0, 0, 0);
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
		db.deleteRubric(id);
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
	public static void editMeasurement(int id, String name, String description,int indicatorId){
		IDatabase db = DatabaseProvider.getInstance();
		db.editMeasurement(id, name, description, indicatorId);
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
	
	public static List<User> retrieveUsers(){
		IDatabase db = DatabaseProvider.getInstance();
		return db.retrieveUsers();
	}
	
	public static Rubric getRubric(int measurementId){
		IDatabase db = DatabaseProvider.getInstance();
		return db.getRubric(measurementId);
	}
	
	public static Rubric editRubric(int measurementId, int below, int meets, int exceeds, int target){
		IDatabase db = DatabaseProvider.getInstance();
		return db.editRubric(measurementId, below, meets, exceeds, target);
	}
	
	public static void Update(Rubric rubric){
		IDatabase db = DatabaseProvider.getInstance();
		
		double met = rubric.getMeets() + rubric.getExceeds();
		double total = rubric.getBelow() + met;
		
		if (((met/total) * 100) >= (rubric.getTarget())){
			db.updateMet(rubric.getMeasurementId(), true);
		}
		else{
			db.updateMet(rubric.getMeasurementId(), false);
		}
	}
}
