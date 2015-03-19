package edu.ycp.cs320.acadman.persist;

import java.util.List;

import edu.ycp.cs320.acadman.model.Indicator;
import edu.ycp.cs320.acadman.model.Measurement;
import edu.ycp.cs320.acadman.model.Outcome;
import edu.ycp.cs320.acadman.model.Program;
import edu.ycp.cs320.acadman.model.Year;

public interface IDatabase {
	public List<Year> retrieveYears();

	public List<Program> retrievePrograms(Year year);

	public List<Outcome> retrieveOutcomes(Program program);

	public List<Indicator> retrieveIndicators(Outcome outcome);

	public List<Measurement> retrieveMeasurement(Indicator indicator);
}
