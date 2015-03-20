package edu.ycp.cs320.acadman.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.acadman.model.Indicator;
import edu.ycp.cs320.acadman.model.Measurement;
import edu.ycp.cs320.acadman.model.Outcome;
import edu.ycp.cs320.acadman.model.Program;
import edu.ycp.cs320.acadman.model.Year;

public class InitialData {
	public static List<Year> getYears() throws IOException {
		List<Year> yearList = new ArrayList<Year>();
		ReadCSV readYears = new ReadCSV("initial_years.csv");
		
		try {
			while(true) {
				List<String> tuple = readYears.next();
				if (tuple == null) break;	
				Iterator<String> i = tuple.iterator();
			
				Year year = new Year();
				year.setId(Integer.parseInt(i.next()));
				year.setYear(Integer.parseInt(i.next()));
				yearList.add(year);
			}
			return yearList;
		} finally {
			readYears.close();
		}
	}
	
	public static List<Program> getPrograms() throws IOException {
		List<Program> list = new ArrayList<Program>();
		ReadCSV read = new ReadCSV("initial_programs.csv");
		
		try {
			while(true) {
				List<String> tuple = read.next();
				if (tuple == null) break;	
				Iterator<String> i = tuple.iterator();
			
				Program thing = new Program();
				thing.setId(Integer.parseInt(i.next()));
				thing.setName(i.next());
				thing.setDescription(i.next());
				thing.setYearId(Integer.parseInt(i.next()));
				list.add(thing);
			}
			return list;
		} finally {
			read.close();
		}
	}
	
	public static List<Outcome> getOutcomes() throws IOException {
		List<Outcome> list = new ArrayList<Outcome>();
		ReadCSV read = new ReadCSV("initial_outcomes.csv");
		
		try {
			while(true) {
				List<String> tuple = read.next();
				if (tuple == null) break;	
				Iterator<String> i = tuple.iterator();
			
				Outcome thing = new Outcome();
				thing.setId(Integer.parseInt(i.next()));
				thing.setName(i.next());
				thing.setDescription(i.next());
				thing.setMinMet(Integer.parseInt(i.next()));
				thing.setProgramId(Integer.parseInt(i.next()));
				list.add(thing);
			}
			return list;
		} finally {
			read.close();
		}
	}
	
	public static List<Indicator> getIndicators() throws IOException {
		List<Indicator> list = new ArrayList<Indicator>();
		ReadCSV read = new ReadCSV("initial_indicators.csv");
		
		try {
			while(true) {
				List<String> tuple = read.next();
				if (tuple == null) break;	
				Iterator<String> i = tuple.iterator();
			
				Indicator thing = new Indicator();
				thing.setId(Integer.parseInt(i.next()));
				thing.setName(i.next());
				thing.setDescription(i.next());
				thing.setMinMet(Integer.parseInt(i.next()));
				thing.setOutcomeId(Integer.parseInt(i.next()));
				list.add(thing);
			}
			return list;
		} finally {
			read.close();
		}
	}
	
	public static List<Measurement> getMeasurements() throws IOException {
		List<Measurement> list = new ArrayList<Measurement>();
		ReadCSV read = new ReadCSV("initial_measurements.csv");
		
		try {
			while(true) {
				List<String> tuple = read.next();
				if (tuple == null) break;	
				Iterator<String> i = tuple.iterator();
			
				Measurement thing = new Measurement();
				thing.setId(Integer.parseInt(i.next()));
				thing.setName(i.next());
				thing.setDescription(i.next());
				thing.setMet(Boolean.parseBoolean(i.next()));
				thing.setIndicatorId(Integer.parseInt(i.next()));
				list.add(thing);
			}
			return list;
		} finally {
			read.close();
		}
	}
}
