package edu.ycp.cs320.acadman.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.acadman.model.Indicator;
import edu.ycp.cs320.acadman.model.Measurement;
import edu.ycp.cs320.acadman.model.Outcome;
import edu.ycp.cs320.acadman.model.Program;
import edu.ycp.cs320.acadman.model.User;

public class FakeDatabase implements IDatabase {

	private List<Program> programs;
	private List<Outcome> outcomes;
	private List<Indicator> indicators;
	private List<Measurement> measurements;
	private List<User> users;
	
	private int lastId;

	public FakeDatabase() {
		programs = new ArrayList<Program>();
		outcomes = new ArrayList<Outcome>();
		indicators = new ArrayList<Indicator>();
		measurements = new ArrayList<Measurement>();
		users = new ArrayList<User>();
		
		lastId = 3;
	}
	
	@Override
	public void Setup(){
		this.readInitialData();
	}
	
	public void readInitialData() {
		try {
			programs.addAll(InitialData.readPrograms());
			outcomes.addAll(InitialData.readOutcomes());
			indicators.addAll(InitialData.readIndicators());
			measurements.addAll(InitialData.readMeasurements());
			users.addAll(InitialData.readUsers());
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
	public List<User> retrieveUsers() {
		List<User> result = new ArrayList<User>();
		for (User x : users) {
				result.add(x);
		}
		return result;
	}
	
	@Override
	public Program addProgram(String name, String description, int yearId) {
		lastId += 1;
		Program p = new Program();
		p.setId(lastId);
		p.setName(name);
		p.setDescription(description);
		p.setYearId(yearId);
		programs.add(p);
		return p;
	}

	@Override
	public Outcome addOutcome(String name, String description, int minMet,
			int programId) {
		lastId += 1;
		Outcome o = new Outcome();
		o.setId(lastId);
		o.setName(name);
		o.setDescription(description);
		o.setMinMet(minMet);
		o.setProgramId(programId);
		outcomes.add(o);
		return o;
	}

	@Override
	public Indicator addIndicator(String name, String description, int minMet,
			int outcomeId) {
		lastId += 1;
		Indicator i = new Indicator();
		i.setId(lastId);
		i.setName(name);
		i.setDescription(description);
		i.setMinMet(minMet);
		i.setOutcomeId(outcomeId);
		indicators.add(i);
		return i;
	}

	@Override
	public Measurement addMeasurement(String name, String description, boolean isMet,
			int indicatorId) {
		lastId += 1;
		Measurement m = new Measurement();
		m.setId(lastId);
		m.setName(name);
		m.setDescription(description);
		m.setMet(isMet);
		m.setIndicatorId(indicatorId);
		measurements.add(m);
		return m;
	}

	@Override
	public void deleteProgram(int id){
		Program toremove = new Program();
		for(Program i : programs)
		{
			if(i.getId() == id)
			{
				toremove = i;
			}
		}
		programs.remove(toremove);
	}
	
	@Override
	public void deleteOutcome(int id){
		Outcome toremove = new Outcome();
		for(Outcome i : outcomes)
		{
			if(i.getId() == id)
			{
				toremove = i;
			}
		}
		outcomes.remove(toremove);
	}
	
	@Override
	public void deleteIndicator(int id){
		Indicator toremove = new Indicator();
		for(Indicator i : indicators)
		{
			if(i.getId() == id)
			{
				toremove = i;
			}
		}
		indicators.remove(toremove);
	}
	
	@Override
	public void deleteMeasurement(int id){
		Measurement toremove = new Measurement();
		for(Measurement i : measurements)
		{
			if(i.getId() == id)
			{
				toremove = i;
				break;
			}
		}
		measurements.remove(toremove);
	}
	
	@Override
	public Program editProgram(int id, String name, String description, int yearId){
		Program edited = new Program();
		for(Program i : programs)
		{
			if(i.getId() == id)
			{
				i.setName(name);
				i.setDescription(description);
				i.setYearId(yearId);
				edited = i;
			}
		}
		return edited;
	}
	
	@Override
	public Outcome editOutcome(int id, String name, String description, int minMet, int programId){
		Outcome edited = new Outcome();
		for(Outcome i : outcomes)
		{
			if(i.getId() == id)
			{
				i.setName(name);
				i.setDescription(description);
				i.setMinMet(minMet);
				i.setProgramId(programId);
				edited = i;
			}
		}
		return edited;
	}
	
	@Override
	public Indicator editIndicator(int id, String name, String description, int minMet, int outcomeId){
		Indicator edited = new Indicator();
		for(Indicator i : indicators)
		{
			if(i.getId() == id)
			{
				i.setName(name);
				i.setDescription(description);
				i.setMinMet(minMet);
				i.setOutcomeId(outcomeId);
				edited = i;
			}
		}
		return edited;
	}
	
	@Override
	public Measurement editMeasurement(int id, String name, String description, boolean isMet, int indicatorId){
		Measurement edited = new Measurement();
		for(Measurement i : measurements)
		{
			if(i.getId() == id)
			{
				i.setName(name);
				i.setDescription(description);
				i.setMet(isMet);
				i.setIndicatorId(indicatorId);
				edited = i;
			}
		}
		return edited;
	}
	
	
	@Override
	public User getUser(String username) {

		for (User x : users) {
			
			if (x.getUsername().equals(username)){
				return x;
			}
		}
		return null;
	}

	public Program getProgram(int id) {

		for (Program x : programs) {
			
			if (x.getId() == id){
				return x;
			}
		}
		return null;
	}
	
	public Outcome getOutcome(int id) {

		for (Outcome x : outcomes) {
			
			if (x.getId() == id){
				return x;
			}
		}
		return null;
	}
	
	public Indicator getIndicator(int id) {

		for (Indicator x : indicators) {
			
			if (x.getId() == id){
				return x;
			}
		}
		return null;
	}
	
	public Measurement getMeasurement(int id) {

		for (Measurement x : measurements) {
			
			if (x.getId() == id){
				return x;
			}
		}
		return null;
	}
	
	@Override
	public User addUser(String username, String email, String password, int permissions) {
		User x = new User();
		x.setUsername(username);
		x.setEmail(email);
		x.setPassword(password);
		x.setPermissions(permissions);
		users.add(x);
		return x;
	}

	@Override
	public void deleteUser(String username) {
		Iterator<User> i = users.iterator();
		while (i.hasNext()) {
			User x = i.next();
			if (x.getUsername().equals(username)) {
				i.remove();
				return;
			}
		}
	}

	@Override
	public User editUser(String username, String password, String email, int permissions) {
		User edited = new User();
		for(User x : users)
		{
			if(x.getUsername().equals(username)) {
				x.setUsername(username);
				x.setPassword(password);
				x.setEmail(email);
				x.setPermissions(permissions);
				edited = x;
			}
		}
		return edited;
	}
}
