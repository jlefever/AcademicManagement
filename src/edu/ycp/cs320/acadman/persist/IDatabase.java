package edu.ycp.cs320.acadman.persist;

import java.util.List;

import edu.ycp.cs320.acadman.model.Indicator;
import edu.ycp.cs320.acadman.model.Measurement;
import edu.ycp.cs320.acadman.model.Outcome;
import edu.ycp.cs320.acadman.model.Program;
import edu.ycp.cs320.acadman.model.Year;

public interface IDatabase {
	public List<Year> retrieveYears();

	public List<Program> retrievePrograms(int yearId);

	public List<Outcome> retrieveOutcomes(int programId);

	public List<Indicator> retrieveIndicators(int outcomeId);

	public List<Measurement> retrieveMeasurement(int indicatorId);
}
