package edu.ycp.cs320.acadman.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.acadman.model.Indicator;
import edu.ycp.cs320.acadman.model.Measurement;
import edu.ycp.cs320.acadman.model.Outcome;
import edu.ycp.cs320.acadman.model.Program;

public class InitialData {
	public static List<Program> readPrograms() throws IOException {
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
	
	public static List<Outcome> readOutcomes() throws IOException {
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
	
	public static List<Indicator> readIndicators() throws IOException {
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
	
	public static List<Measurement> readMeasurements() throws IOException {
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
