package edu.ycp.cs320.acadman.persist;

import java.util.List;

import edu.ycp.cs320.acadman.model.Indicator;
import edu.ycp.cs320.acadman.model.Measurement;
import edu.ycp.cs320.acadman.model.Outcome;
import edu.ycp.cs320.acadman.model.Program;
import edu.ycp.cs320.acadman.model.Year;

public class FakeDatabase implements IDatabase {
	
	private List<Year> years;
	private List<Program> programs;
	private List<Outcome> outcomes;
	private List<Indicator> indicator;
	private List<Measurement> measurements;
	
	public FakeDatabase() {
		
	}
	
	@Override
	public List<Year> retrieveYears() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Program> retrievePrograms(Year year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Outcome> retrieveOutcomes(Program program) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Indicator> retrieveIndicators(Outcome outcome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Measurement> retrieveMeasurement(Indicator indicator) {
		// TODO Auto-generated method stub
		return null;
	}

}
