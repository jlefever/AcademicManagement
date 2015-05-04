package edu.ycp.cs320.acadman.controller;


import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.acadman.model.Indicator;
import edu.ycp.cs320.acadman.model.Measurement;
import edu.ycp.cs320.acadman.model.Outcome;
import edu.ycp.cs320.acadman.model.Program;
import edu.ycp.cs320.acadman.model.User;
import edu.ycp.cs320.acadman.persist.DatabaseProvider;
import edu.ycp.cs320.acadman.persist.FakeDatabase;
import edu.ycp.cs320.acadman.persist.IDatabase;

public class ControllerTest {
	
	@Before
	public void setUp(){
		FakeDatabase db = new FakeDatabase();
		db.readInitialData();
		DatabaseProvider.setInstance(db);
	}
	
	@Test
	public void testRetrievePrograms(){
		List<Program> desired = new ArrayList<Program>();
		desired.add(new Program(1,"Computer Science","Science with computers (also math)",2013));
		desired.add(new Program(2,"Philosophy","Think about stuff",2013));
		
		List<Program> actual = Controller.retrievePrograms(2013);
		
		assertTrue(desired.size() == actual.size());
		
		for (Program y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testRetrieveOutcomes(){
		List<Outcome> desired = new ArrayList<Outcome>();
		desired.add(new Outcome(1,"An ability to function effectively on teams","description",2,1));
		desired.add(new Outcome(2,"An ability to apply knowledge of computing and mathematics appropriate to the discipline","description",3,1));
		
		List<Outcome> actual = Controller.retrieveOutcomes(1);
		
		assertTrue(desired.size() == actual.size());
		
		for (Outcome y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testRetrieveIndicators(){
		List<Indicator> desired = new ArrayList<Indicator>();
		desired.add(new Indicator(1,"Has not killed any team members","description",1,1));
		
		List<Indicator> actual = Controller.retrieveIndicators(1);
		
		assertTrue(desired.size() == actual.size());
		
		for (Indicator y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testRetrieveMeasurements(){
		List<Measurement> desired = new ArrayList<Measurement>();
		desired.add(new Measurement(1,"Has killed less than 1 team member","description",false,1));
		
		List<Measurement> actual = Controller.retrieveMeasurements(1);
		
		assertTrue(desired.size() == actual.size());
		
		for (Measurement y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testAddProgram(){
		List<Program> desired = new ArrayList<Program>();
		desired.add(new Program(1,"Computer Science","Science with computers (also math)",2013));
		desired.add(new Program(2,"Philosophy","Think about stuff",2013));
		desired.add(new Program(4,"Information Systems","description",2013));
		
		
		Controller.addProgram("Information Systems","description",2013);
		List<Program> actual = Controller.retrievePrograms(2013);
		assertTrue(desired.size() == actual.size());
		
		for (Program y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	
	//fix
	@Test
	public void testAddOutcome(){
		List<Outcome> desired = new ArrayList<Outcome>();
		desired.add(new Outcome(1,"An ability to function effectively on teams","description",2,1));
		desired.add(new Outcome(2,"An ability to apply knowledge of computing and mathematics appropriate to the discipline","description",3,1));
		desired.add(new Outcome(4,"Have the ability to create DBs","description",4, 1));
	
		Controller.addOutcome("Have the ability to create DBs","description",4, 1);
		List<Outcome> actual = Controller.retrieveOutcomes(1);
		assertTrue(desired.size() == actual.size());
		
		for (Outcome y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testAddIndicator(){
		List<Indicator> desired = new ArrayList<Indicator>();
		desired.add(new Indicator(1,"Has not killed any team members","description",1,1));
		desired.add(new Indicator(4,"Have the ability to create DB","description",3,1));
		
		Controller.addIndicator("Have the ability to create DB","description",3, 1);
		List<Indicator> actual = Controller.retrieveIndicators(1);
		assertTrue(desired.size() == actual.size());
		
		for (Indicator y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testAddMeasurement(){
		List<Measurement> desired = new ArrayList<Measurement>();
		desired.add(new Measurement(1,"Has killed less than 1 team member","description",false,1));
		desired.add(new Measurement(4,"Tests","description",false,1));
		
		Controller.addMeasurement("Tests","description",false,1);
		List<Measurement> actual = Controller.retrieveMeasurements(1);
		assertTrue(desired.size() == actual.size());
		
		for (Measurement y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testAddUser(){
		List<User> desired = new ArrayList<User>();
		desired.add(new User("bob", "bob@aol.com", "iambob",1));
		desired.add(new User("sally", "sally@aol.com", "iamsally",2));
		desired.add(new User("admin", "admin@aol.com", "iamadmin",3));
		desired.add(new User("Jsalaza1", "Jsalaza1@aol.com", "iamjuan", 1));
		
		Controller.addUser("Jsalaza1", "Jsalaza1@aol.com", "iamjuan", 1);
		List<User> actual = Controller.retrieveUsers();
		assertTrue(desired.size() == actual.size());
		
		for (User y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testEditProgram(){
		List<Program> desired = new ArrayList<Program>();
		desired.add(new Program(1,"Computer Science","Science with computers (also math)",2013));
		desired.add(new Program(2,"Philosophy","A change",2013));
		
		Controller.editProgram(2, "Philosophy","A change",2013);
		List<Program> actual = Controller.retrievePrograms(2013);
		assertTrue(desired.size() == actual.size());
		
		for (Program y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testEditOutcome(){
		List<Outcome> desired = new ArrayList<Outcome>();
		desired.add(new Outcome(1,"An ability to function effectively on teams","description",2,1));
		desired.add(new Outcome(2,"An ability to apply knowledge of computing and mathematics appropriate to the discipline","A change",3,1));
	
		Controller.editOutcome(2,"An ability to apply knowledge of computing and mathematics appropriate to the discipline","A change",3,1);
		List<Outcome> actual = Controller.retrieveOutcomes(1);
		assertTrue(desired.size() == actual.size());
		
		for (Outcome y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testEditIndicator(){
		List<Indicator> desired = new ArrayList<Indicator>();
		desired.add(new Indicator(1,"Has not killed any team members","A change",1,1));
		
		Controller.editIndicator(1,"Has not killed any team members","A change",1,1);
		List<Indicator> actual = Controller.retrieveIndicators(1);
		assertTrue(desired.size() == actual.size());
		
		for (Indicator y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testEditMeasurement(){
		List<Measurement> desired = new ArrayList<Measurement>();
		desired.add(new Measurement(1,"Has killed less than 1 team member","A change",false,1));
		
		Controller.editMeasurement(1,"Has killed less than 1 team member","A change",false,1);
		List<Measurement> actual = Controller.retrieveMeasurements(1);
		assertTrue(desired.size() == actual.size());
		
		for (Measurement y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testDeleteProgram(){
		List<Program> desired = new ArrayList<Program>();
		desired.add(new Program(1,"Computer Science","Science with computers (also math)",2013));
		
		Controller.deleteProgram(2);
		
		List<Program> actual = Controller.retrievePrograms(2013);
				
		assertTrue(desired.size() == actual.size());
		
		for (Program y : desired){
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testDeleteOutcome(){
		List<Outcome> desired = new ArrayList<Outcome>();
		desired.add(new Outcome(1,"An ability to function effectively on teams","description",2,1));
		
		Controller.deleteOutcome(2);
		
		List<Outcome> actual = Controller.retrieveOutcomes(1);
				
		assertTrue(desired.size() == actual.size());
		
		for (Outcome y : desired){
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testDeleteIndicator(){
		List<Indicator> desired = new ArrayList<Indicator>();
		
		Controller.deleteIndicator(1);
		
		List<Indicator> actual = Controller.retrieveIndicators(1);
				
		assertTrue(desired.size() == actual.size());
		
		assertTrue(actual.isEmpty());
	}
	
	@Test
	public void testDeleteMeasurement(){
		List<Measurement> desired = new ArrayList<Measurement>();
		
		Controller.deleteMeasurement(1);
		
		List<Measurement> actual = Controller.retrieveMeasurements(1);
				
		assertTrue(desired.size() == actual.size());
		
		assertTrue(actual.isEmpty());
	}
	
	@Test
	public void testDeleteUser(){
		List<User> desired = new ArrayList<User>();
		desired.add(new User("sally", "sally@aol.com", "iamsally",2));
		desired.add(new User("admin", "admin@aol.com", "iamadmin",3));
		
		Controller.deleteUser("bob");
		
		List<User> actual = Controller.retrieveUsers();
				
		assertTrue(desired.size() == actual.size());
		
		for (User y : desired){
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testGetUser(){
		User desired = new User("bob", "bob@aol.com", "iambob", 1);
		
		User actual = Controller.getUser("bob");
		
		assertTrue(actual.equals(desired));
	}
}
