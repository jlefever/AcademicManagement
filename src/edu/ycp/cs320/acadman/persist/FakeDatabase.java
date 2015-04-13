package edu.ycp.cs320.acadman.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.acadman.model.Indicator;
import edu.ycp.cs320.acadman.model.Measurement;
import edu.ycp.cs320.acadman.model.Outcome;
import edu.ycp.cs320.acadman.model.Program;

public class FakeDatabase implements IDatabase {

	private List<Program> programs;
	private List<Outcome> outcomes;
	private List<Indicator> indicators;
	private List<Measurement> measurements;
	
	private int lastId;

	public FakeDatabase() {
		programs = new ArrayList<Program>();
		outcomes = new ArrayList<Outcome>();
		indicators = new ArrayList<Indicator>();
		measurements = new ArrayList<Measurement>();
		
		lastId = 0;
	}

	public void readInitialData() {
		try {
			programs.addAll(InitialData.readPrograms());
			outcomes.addAll(InitialData.readOutcomes());
			indicators.addAll(InitialData.readIndicators());
			measurements.addAll(InitialData.readMeasurements());
		} catch (IOException e) {
			throw new IllegalStateException("Could not read initial data", e);
		}
	}

	@Override
	public List<Program> retrievePrograms(int yearId) {
		List<Program> result = new ArrayList<Program>();
		for (Program x : programs) {
			if (x.getYearId() == yearId) {
				result.add(x);
			}
		}
		return result;
	}

	@Override
	public List<Outcome> retrieveOutcomes(int programId) {
		List<Outcome> result = new ArrayList<Outcome>();
		for (Outcome x : outcomes) {
			if (x.getProgramId() == programId) {
				result.add(x);
			}
		}
		return result;
	}

	@Override
	public List<Indicator> retrieveIndicators(int outcomeId) {
		List<Indicator> result = new ArrayList<Indicator>();
		for (Indicator x : indicators) {
			if (x.getOutcomeId() == outcomeId) {
				result.add(x);
			}
		}
		return result;
	}

	@Override
	public List<Measurement> retrieveMeasurements(int indicatorId) {
		List<Measurement> result = new ArrayList<Measurement>();
		for (Measurement x : measurements) {
			if (x.getIndicatorId() == indicatorId) {
				result.add(x);
			}
		}
		return result;
	}

	@Override
	public void addProgram(String name, String description, int yearId) {
		lastId += 1;
		Program p = new Program();
		p.setId(lastId);
		p.setName(name);
		p.setDescription(description);
		p.setYearId(yearId);
		programs.add(p);
	}

	@Override
	public void addOutcome(String name, String description, int minMet,
			int programId) {
		lastId += 1;
		Outcome o = new Outcome();
		o.setId(lastId);
		o.setName(name);
		o.setDescription(description);
		o.setMinMet(minMet);
		o.setProgramId(programId);
		outcomes.add(o);
	}

	@Override
	public void addIndicator(String name, String description, int minMet,
			int outcomeId) {
		lastId += 1;
		Indicator i = new Indicator();
		i.setId(lastId);
		i.setName(name);
		i.setDescription(description);
		i.setMinMet(minMet);
		i.setOutcomeId(outcomeId);
		indicators.add(i);
	}

	@Override
	public void addMeasurement(String name, String description, boolean isMet,
			int indicatorId) {
		lastId += 1;
		Measurement m = new Measurement();
		m.setId(lastId);
		m.setName(name);
		m.setDescription(description);
		m.setMet(isMet);
		m.setIndicatorId(indicatorId);
		measurements.add(m);
	}
}