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
import edu.ycp.cs320.acadman.model.Year;

public class InitialDataTest {
	@Test
	public void testReadYears() throws IOException{
		List<Year> desired = new ArrayList<Year>();
		desired.add(new Year(1, 2013));
		desired.add(new Year(2, 2014));
		desired.add(new Year(3, 2015));
		
		List<Year> actual = InitialData.readYears();
		
		assertTrue(desired.size() == actual.size());
		
		for (Year y : desired) {
			assertTrue(actual.contains(y));
		}
	}
	
	@Test
	public void testReadPrograms() throws IOException{
		List<Program> desired = new ArrayList<Program>();
		desired.add(new Program(1, "Computer Science","Science with computers (also math)",3));
		desired.add(new Program(2,"Philosophy","Think about stuff",3));
		desired.add(new Program(3,"Computer Science","Science with computers",2));
		
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
}
