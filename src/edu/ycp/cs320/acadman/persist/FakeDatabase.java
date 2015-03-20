package edu.ycp.cs320.acadman.persist;

import java.io.IOException;
import java.util.ArrayList;
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
	private List<Indicator> indicators;
	private List<Measurement> measurements;

	public FakeDatabase() {
		years = new ArrayList<Year>();
		programs = new ArrayList<Program>();
		outcomes = new ArrayList<Outcome>();
		indicators = new ArrayList<Indicator>();
		measurements = new ArrayList<Measurement>();
	}

	public void readInitialData() {
		try {
			years.addAll(InitialData.getYears());
			programs.addAll(InitialData.getPrograms());
			outcomes.addAll(InitialData.getOutcomes());
			indicators.addAll(InitialData.getIndicators());
			measurements.addAll(InitialData.getMeasurements());
		} catch (IOException e) {
			throw new IllegalStateException("Could not read initial data", e);
		}
	}

	@Override
	public List<Year> retrieveYears() {
		return years;
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
	public List<Measurement> retrieveMeasurement(int indicatorId) {
		List<Measurement> result = new ArrayList<Measurement>();
		for (Measurement x : measurements) {
			if (x.getIndicatorId() == indicatorId) {
				result.add(x);
			}
		}
		return result;
	}
}