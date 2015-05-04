package edu.ycp.cs320.acadman.persist;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.ycp.cs320.acadman.model.Indicator;
import edu.ycp.cs320.acadman.model.Measurement;
import edu.ycp.cs320.acadman.model.Outcome;
import edu.ycp.cs320.acadman.model.Program;
import edu.ycp.cs320.acadman.model.User;

public class InitialDataTest {
	
	@Test
	public void testReadPrograms() throws IOException{
		List<Program> desired = new ArrayList<Program>();
		desired.add(new Program(1, "Computer Science","Science with computers (also math)",2013));
		desired.add(new Program(2,"Philosophy","Think about stuff",2013));
		desired.add(new Program(3,"Computer Science","Science with computers",2012));
		
		List<Program> actual = InitialData.readPrograms();
		
		assertTrue(desired.size() == actual.size());
		
		for (Program y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testReadOutcomes() throws IOException{
		List<Outcome> desired = new ArrayList<Outcome>();
		desired.add(new Outcome(1,"An ability to function effectively on teams","description",2,1));
		desired.add(new Outcome(2,"An ability to apply knowledge of computing and mathematics appropriate to the discipline","description",3,1));
		
		List<Outcome> actual = InitialData.readOutcomes();
		
		assertTrue(desired.size() == actual.size());
		
		for (Outcome y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testReadIndicators() throws IOException{
		List<Indicator> desired = new ArrayList<Indicator>();
		desired.add(new Indicator(1,"Has not killed any team members","description",1,1));
		
		List<Indicator> actual = InitialData.readIndicators();
		
		assertTrue(desired.size() == actual.size());
		
		for (Indicator y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testReadMeasurements() throws IOException{
		List<Measurement> desired = new ArrayList<Measurement>();
		desired.add(new Measurement(1,"Has killed less than 1 team member","description",false,1));
		
		List<Measurement> actual = InitialData.readMeasurements();
		
		assertTrue(desired.size() == actual.size());
		
		for (Measurement y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testReadUsers() throws IOException{
		List<User> desired = new ArrayList<User>();
		desired.add(new User("bob", "bob@aol.com", "iambob", 1));
		desired.add(new User("sally", "sally@aol.com", "iamsally", 2));
		desired.add(new User("admin", "admin@aol.com", "iamadmin", 3));
		
		List<User> actual = InitialData.readUsers();
		
		assertTrue(desired.size() == actual.size());
		
		for (User y: desired){
			assertTrue(actual.contains(y));
		}
	}
}
