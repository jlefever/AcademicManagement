package edu.ycp.cs320.acadman.persist;

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

public class FakeDatabaseTest {
	
	FakeDatabase db;
	@Before
	public void setup(){
		db = new FakeDatabase();
		db.readInitialData();
	}
	
	@Test
	public void testRetrievePrograms(){
		List<Program> desired = new ArrayList<Program>();
		desired.add(new Program(1,"Computer Science","Science with computers (also math)",2013));
		desired.add(new Program(2,"Philosophy","Think about stuff",2013));
		
		List<Program> actual = db.retrievePrograms(2013);
		
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
		
		List<Outcome> actual = db.retrieveOutcomes(1);
		
		assertTrue(desired.size() == actual.size());
		
		for (Outcome y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testRetrieveIndicators(){
		List<Indicator> desired = new ArrayList<Indicator>();
		desired.add(new Indicator(1,"Has not killed any team members","description",1,1));
		
		List<Indicator> actual = db.retrieveIndicators(1);
		
		assertTrue(desired.size() == actual.size());
		
		for (Indicator y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testRetrieveMeasurements(){
		List<Measurement> desired = new ArrayList<Measurement>();
		desired.add(new Measurement(1,"Has killed less than 1 team member","description",false,1));
		
		List<Measurement> actual = db.retrieveMeasurements(1);
		
		assertTrue(desired.size() == actual.size());
		
		for (Measurement y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testRetrieveUsers(){
		List<User> desired = new ArrayList<User>();
		desired.add(new User("bob", "bob@aol.com", "iambob", 1));
		desired.add(new User("sally", "sally@aol.com", "iamsally", 2));
		desired.add(new User("admin", "admin@aol.com", "iamadmin", 3));
		
		List<User> actual = db.retrieveUsers();
		
		assertTrue(desired.size() == actual.size());
		
		for(User y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	@Test
	public void testDeleteProgram(){
		List<Program> desired = new ArrayList<Program>();
		desired.add(new Program(1,"Computer Science","Science with computers (also math)",2013));
		
		db.deleteProgram(2);
		
		List<Program> actual = db.retrievePrograms(2013);
				
		assertTrue(desired.size() == actual.size());
		
		for (Program y : desired){
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testDeleteOutcome(){
		List<Outcome> desired = new ArrayList<Outcome>();
		desired.add(new Outcome(1,"An ability to function effectively on teams","description",2,1));
		
		db.deleteOutcome(2);
		
		List<Outcome> actual = db.retrieveOutcomes(1);
				
		assertTrue(desired.size() == actual.size());
		
		for (Outcome y : desired){
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testDeleteIndicator(){
		List<Indicator> desired = new ArrayList<Indicator>();
		
		db.deleteIndicator(1);
		
		List<Indicator> actual = db.retrieveIndicators(1);
				
		assertTrue(desired.size() == actual.size());
		
	
			assertTrue(actual.isEmpty());
		
	}
	
	@Test
	public void testDeleteMeasurement(){
		List<Measurement> desired = new ArrayList<Measurement>();
		
		db.deleteMeasurement(1);
		
		List<Measurement> actual = db.retrieveMeasurements(1);
				
		assertTrue(desired.size() == actual.size());
		
		assertTrue(actual.isEmpty());
		
	}
	
	@Test
	public void testRetrieveUser(){
		User desired = new User("bob", "bob@aol.com", "iambob", 1);
		
		User actual = db.getUser("bob");
		
		assertTrue(actual.equals(desired));
	}
}
