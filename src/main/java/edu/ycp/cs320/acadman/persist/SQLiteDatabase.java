package edu.ycp.cs320.acadman.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.acadman.model.Indicator;
import edu.ycp.cs320.acadman.model.Measurement;
import edu.ycp.cs320.acadman.model.Outcome;
import edu.ycp.cs320.acadman.model.Program;
import edu.ycp.cs320.acadman.model.Rubric;
import edu.ycp.cs320.acadman.model.User;

public class SQLiteDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load sqlite driver");
		}
	}

	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;

	public <ResultType> ResultType executeTransaction(
			Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new RuntimeException("Transaction failed", e);
		}
	}

	public <ResultType> ResultType doExecuteTransaction(
			Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();

		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;

			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null
							&& e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been
						// reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}

			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}

			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	private Connection connect() throws SQLException {
		String home = System.getProperty("user.home");

		Connection conn = DriverManager.getConnection("jdbc:sqlite:" + home
				+ "/assessment.db");

		// Set auto commit to false to allow multiple the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);

		return conn;
	}

	@Override
	public List<Program> retrievePrograms(final int yearId) {
		return executeTransaction(new Transaction<List<Program>>() {
			@Override
			public List<Program> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from programs where year = ?"
					);
					stmt.setInt(1, yearId);
					
					List<Program> result = new ArrayList<Program>();
					
					resultSet = stmt.executeQuery();
					
					while (resultSet.next()) {
						Program program = new Program();
						loadProgram(program, resultSet, 1);
						
						result.add(program);
					}
					
					return result;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public List<Outcome> retrieveOutcomes(final int programId) {
		return executeTransaction(new Transaction<List<Outcome>>() {
			@Override
			public List<Outcome> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from outcomes where program_id = ?"
					);
					stmt.setInt(1, programId);
					
					List<Outcome> result = new ArrayList<Outcome>();
					
					resultSet = stmt.executeQuery();
					
					while (resultSet.next()) {
						Outcome outcome = new Outcome();
						loadOutcome(outcome, resultSet, 1);
						
						result.add(outcome);
					}
					
					return result;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public List<Indicator> retrieveIndicators(final int outcomeId) {
		return executeTransaction(new Transaction<List<Indicator>>() {
			@Override
			public List<Indicator> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from indicators where outcome_id = ?"
					);
					stmt.setInt(1, outcomeId);
					
					List<Indicator> result = new ArrayList<Indicator>();
					
					resultSet = stmt.executeQuery();
					
					while (resultSet.next()) {
						Indicator indicator = new Indicator();
						loadIndicator(indicator, resultSet, 1);
						
						result.add(indicator);
					}
					
					return result;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public List<Measurement> retrieveMeasurements(final int indicatorId) {
		return executeTransaction(new Transaction<List<Measurement>>() {
			@Override
			public List<Measurement> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from measurements where indicator_id = ?"
					);
					stmt.setInt(1, indicatorId);
					
					List<Measurement> result = new ArrayList<Measurement>();
					
					resultSet = stmt.executeQuery();
					
					while (resultSet.next()) {
						Measurement measurement = new Measurement();
						loadMeasurement(measurement, resultSet, 1);
						
						result.add(measurement);
					}
					
					return result;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public Program addProgram(final String name, final String description, final int yearId) {
		return executeTransaction(new Transaction<Program>() {
			@Override
			public Program execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet genKeys = null;
				try {

					stmt = conn.prepareStatement(
							"insert into Programs (name, description, year) values (?, ?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS
					);

					
					stmt.setString(1, name);
					stmt.setString(2, description);
					stmt.setInt(3, yearId);

					stmt.executeUpdate();
					
					// get generated keys
					genKeys = stmt.getGeneratedKeys();
					genKeys.next(); // one tuple should be returned
				
					int generatedId = genKeys.getInt(1);
					System.out.println("Added program with id=" + generatedId);
					
					return new Program(generatedId, name, description, yearId);
				}

				finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(genKeys);
				}

			}
		});
	}

	@Override
	public Outcome addOutcome(final String name, final String description, final int minMet,
			final int programId) {
		return executeTransaction(new Transaction<Outcome>() {
			@Override
			public Outcome execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet genKeys = null;
				try {

					stmt = conn.prepareStatement(
							"insert into Outcomes (name, description, program_id, min_met) values (?, ?, ?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS
					);

					
					stmt.setString(1, name);
					stmt.setString(2, description);
					stmt.setInt(3, programId);
					stmt.setInt(4, minMet);

					stmt.executeUpdate();
					
					// get generated keys
					genKeys = stmt.getGeneratedKeys();
					genKeys.next(); // one tuple should be returned
				
					int generatedId = genKeys.getInt(1);
					System.out.println("Added outcome with id=" + generatedId);
					
					return new Outcome(generatedId, name, description, minMet, programId);
				}

				finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(genKeys);
				}

			}
		});
	}

	@Override
	public Indicator addIndicator(final String name, final String description,
			final int minMet, final int outcomeId) {
		return executeTransaction(new Transaction<Indicator>() {
			@Override
			public Indicator execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet genKeys = null;
				try {

					stmt = conn.prepareStatement(
							"insert into Indicators (name, description, outcome_id, min_met) values (?, ?, ?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
					);

					
					stmt.setString(1, name);
					stmt.setString(2, description);
					stmt.setInt(3, outcomeId);
					stmt.setInt(4, minMet);

					stmt.executeUpdate();
					
					// get generated keys
					genKeys = stmt.getGeneratedKeys();
					genKeys.next(); // one tuple should be returned
				
					int generatedId = genKeys.getInt(1);
					System.out.println("Added indicator with id=" + generatedId);
					
					return new Indicator(generatedId, name, description, minMet, outcomeId);
				}

				finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(genKeys);
				}

			}
		});
	}

	@Override
	public Measurement addMeasurement(final String name, final String description,
			final int indicatorId) {
		return executeTransaction(new Transaction<Measurement>() {
			@Override
			public Measurement execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet genKeys = null;
				try {

					stmt = conn.prepareStatement(
							"insert into Measurements (name, description, indicator_id, met) values (?, ?, ?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS
					);

					
					stmt.setString(1, name);
					stmt.setString(2, description);
					stmt.setInt(3, indicatorId);
					stmt.setBoolean(4, false);

					stmt.executeUpdate();
					
					// get generated keys
					genKeys = stmt.getGeneratedKeys();
					genKeys.next(); // one tuple should be returned
				
					int generatedId = genKeys.getInt(1);
					System.out.println("Added measurement with id=" + generatedId);
					
					return new Measurement(generatedId, name, description, false, indicatorId);
				}

				finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(genKeys);
				}

			}
		});
	}
	
	private void loadProgram(Program program, ResultSet resultSet, int index) throws SQLException {
		program.setId(resultSet.getInt(index++));
		program.setName(resultSet.getString(index++));
		program.setDescription(resultSet.getString(index++));
		program.setYear(resultSet.getInt(index++));
	}
	
	private void loadOutcome(Outcome outcome, ResultSet resultSet, int index) throws SQLException {
		outcome.setId(resultSet.getInt(index++));
		outcome.setName(resultSet.getString(index++));
		outcome.setDescription(resultSet.getString(index++));
		outcome.setProgramId(resultSet.getInt(index++));
		outcome.setMinMet(resultSet.getInt(index++));
	}
	
	private void loadIndicator(Indicator indicator, ResultSet resultSet, int index) throws SQLException {
		indicator.setId(resultSet.getInt(index++));
		indicator.setName(resultSet.getString(index++));
		indicator.setDescription(resultSet.getString(index++));
		indicator.setOutcomeId(resultSet.getInt(index++));
		indicator.setMinMet(resultSet.getInt(index++));
	}
	
	private void loadMeasurement(Measurement measurement, ResultSet resultSet, int index) throws SQLException {
		measurement.setId(resultSet.getInt(index++));
		measurement.setName(resultSet.getString(index++));
		measurement.setDescription(resultSet.getString(index++));
		measurement.setIndicatorId(resultSet.getInt(index++));
		measurement.setMet(resultSet.getBoolean(index++));
	}
	
	private void loadUser(User user, ResultSet resultSet, int index) throws SQLException {
		user.setUsername(resultSet.getString(index++));
		user.setEmail(resultSet.getString(index++));
		user.setPassword(resultSet.getString(index++));
		user.setPermissions(resultSet.getInt(index++));
	}
	
	private void loadRubric(Rubric rubric, ResultSet resultSet, int index) throws SQLException {
		rubric.setMeasurementId(resultSet.getInt(index++));
		rubric.setBelow(resultSet.getInt(index++));
		rubric.setMeets(resultSet.getInt(index++));
		rubric.setExceeds(resultSet.getInt(index++));
		rubric.setTarget(resultSet.getInt(index++));
	}
	
	@Override
	public void deleteProgram(final int id) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {

					stmt = conn.prepareStatement(
							"delete from Programs where id = ?"
					);

					
					stmt.setInt(1, id);

					stmt.executeUpdate();
					
					return true;
				}

				finally {
					DBUtil.closeQuietly(stmt);
				}

			}
		});
	}
	
	@Override
	public void deleteOutcome(final int id) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {

					stmt = conn.prepareStatement(
							"delete from Outcomes where id = ?"
					);

					
					stmt.setInt(1, id);

					stmt.executeUpdate();
					
					return true;
				}

				finally {
					DBUtil.closeQuietly(stmt);
				}

			}
		});
	}
	
	@Override
	public void deleteIndicator(final int id) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {

					stmt = conn.prepareStatement(
							"delete from Indicators where id = ?"
					);

					
					stmt.setInt(1, id);

					stmt.executeUpdate();
					
					return true;
				}

				finally {
					DBUtil.closeQuietly(stmt);
				}

			}
		});
	}
	
	@Override
	public void deleteMeasurement(final int id) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {

					stmt = conn.prepareStatement(
							"delete from Measurements where id = ?"
					);

					
					stmt.setInt(1, id);

					stmt.executeUpdate();
					
					return true;
				}

				finally {
					DBUtil.closeQuietly(stmt);
				}

			}
		});
	}
	
	public Outcome editOutcome(final int id, final String name, final String description, final int minMet, final int programId){
		return executeTransaction(new Transaction<Outcome>() {
			@Override
			public Outcome execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {

					stmt = conn.prepareStatement(
							"update Outcomes " +
							"set name=?, description=?, min_met=?, program_id=?" +
							"where id = ?"
					);

					
					stmt.setString(1, name);
					stmt.setString(2, description);
					stmt.setInt(3, minMet);
					stmt.setInt(4, programId);
					stmt.setInt(5, id);

					stmt.executeUpdate();
					
					
					return new Outcome(id, name, description, minMet, programId);
				}

				finally {
					DBUtil.closeQuietly(stmt);
				}

			}
		});
	}
	
	public Indicator editIndicator(final int id, final String name, final String description, final int minMet, final int outcomeId){
		return executeTransaction(new Transaction<Indicator>() {
			@Override
			public Indicator execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {

					stmt = conn.prepareStatement(
							"update Indicators " +
							"set name=?, description=?, min_met=? outcome_id=?" +
							"where id = ?"
					);

					
					stmt.setString(1, name);
					stmt.setString(2, description);
					stmt.setInt(3, minMet);
					stmt.setInt(4, outcomeId);
					stmt.setInt(5, id);

					stmt.executeUpdate();
					
					return new Indicator(id, name, description, minMet, outcomeId);
				}

				finally {
					DBUtil.closeQuietly(stmt);
				}

			}
		});
	}
	
	@Override
	public Measurement editMeasurement(final int id, final String name, final String description, final int indicatorId){
		return executeTransaction(new Transaction<Measurement>() {
			@Override
			public Measurement execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {

					stmt = conn.prepareStatement(
							"update Measurements " +
							"set name=?, description=?, indicator_id=?" +
							"where id = ?"
					);

					
					stmt.setString(1, name);
					stmt.setString(2, description);
					stmt.setInt(3, indicatorId);
					stmt.setInt(4, id);

					stmt.executeUpdate();
					
					return new Measurement(id, name, description, false, indicatorId);
				}

				finally {
					DBUtil.closeQuietly(stmt);
				}

			}
		});
	}
	
	public Program editProgram(final int id, final String name, final String description, final int yearId){
		return executeTransaction(new Transaction<Program>() {
			@Override
			public Program execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {

					stmt = conn.prepareStatement(
							"update Programs " +
							"set name=?, description=?, year=?" +
							"where id = ?"
					);

					
					stmt.setString(1, name);
					stmt.setString(2, description);
					stmt.setInt(3, yearId);
					stmt.setInt(4, id);

					stmt.executeUpdate();
					
					return new Program(id, name, description, yearId);
				}

				finally {
					DBUtil.closeQuietly(stmt);
				}

			}
		});
	}
	
	
	@Override
	public void Setup(){
		this.createTables();
		this.loadInitialData();
	}
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		SQLiteDatabase db = new SQLiteDatabase();
		db.createTables();

		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		for (Rubric i : db.retrieveRubrics()){
			System.out.println(i);
		}
		
		System.out.print(db.getRubric(1));
		
		System.out.println("Success!");
	}

	private void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;

				try {
					stmt1 = conn.prepareStatement("create table Programs("
							+ "    id integer primary key,"
							+ "    name varchar(200),"
							+ "    description varchar(2000),"
							+ "    year integer" + ")");
					stmt1.executeUpdate();

					stmt2 = conn.prepareStatement("create table Outcomes("
							+ "    id integer primary key,"
							+ "    name varchar(200),"
							+ "    description varchar(2000),"
							+ "    program_id integer," 
							+ "    min_met integer"
							+ ")");
					stmt2.executeUpdate();

					stmt3 = conn.prepareStatement("create table Indicators("
							+ "    id integer primary key,"
							+ "    name varchar(200),"
							+ "    description varchar(2000),"
							+ "    outcome_id integer,"
							+ "    min_met integer"
							+ ")");
					stmt3.executeUpdate();

					stmt4 = conn.prepareStatement("create table Measurements("
							+ "    id integer primary key,"
							+ "    name varchar(200),"
							+ "    description varchar(2000),"
							+ "    indicator_id integer," 
							+ "    met boolean"
							+ ")");
					stmt4.executeUpdate();
					
					stmt5 = conn.prepareStatement("create table Users("
							+ "    username varchar(200) primary key,"
							+ "    email varchar(2000),"
							+ "    password varchar(200),"
							+ "    permissions integer"
							+ ")");
					stmt5.executeUpdate();
					
					stmt6 = conn.prepareStatement("create table Rubrics("
							+ "    measurement_id integer primary key,"
							+ "    below integer,"
							+ "    meets integer,"
							+ "    exceeds integer,"
							+ "    target integer"
							+ ")");
					stmt6.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
				}
			}
		});
	}

	private void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<Program> ProgramList;
				List<Outcome> OutcomeList;
				List<Indicator> IndicatorList;
				List<Measurement> MeasurementList;
				List<User> UserList;
				List<Rubric> RubricList;

				try {
					ProgramList = InitialData.readPrograms();
					OutcomeList = InitialData.readOutcomes();
					IndicatorList = InitialData.readIndicators();
					MeasurementList = InitialData.readMeasurements();
					UserList = InitialData.readUsers();
					RubricList = InitialData.readRubrics();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertProgram = null;
				PreparedStatement insertOutcome = null;
				PreparedStatement insertIndicator = null;
				PreparedStatement insertMeasurement = null;
				PreparedStatement insertUser = null;
				PreparedStatement insertRubric = null;

				try {
					insertProgram = conn
							.prepareStatement("insert into Programs values (?, ?, ?, ?)");
					for (Program program : ProgramList) {
						insertProgram.setInt(1, program.getId());
						insertProgram.setString(2, program.getName());
						insertProgram.setString(3, program.getDescription());
						insertProgram.setInt(4, program.getYear());
						insertProgram.addBatch();
					}
					insertProgram.executeBatch();
					// finish
					insertOutcome = conn.prepareStatement("insert into Outcomes values (?, ?, ?, ?,?)");
					for (Outcome outcome : OutcomeList) {
						insertOutcome.setInt(1, outcome.getId());
						insertOutcome.setString(2, outcome.getName());
						insertOutcome.setString(3, outcome.getDescription());
						insertOutcome.setInt(4, outcome.getProgramId());
						insertOutcome.setInt(5, outcome.getMinMet());
						insertOutcome.addBatch();
					}
					insertOutcome.executeBatch();

					insertIndicator = conn.prepareStatement("insert into Indicators values (?, ?, ?, ?,?)");
					for (Indicator indicator : IndicatorList) {
						insertIndicator.setInt(1, indicator.getId());
						insertIndicator.setString(2, indicator.getName());
						insertIndicator
								.setString(3, indicator.getDescription());
						insertIndicator.setInt(4, indicator.getOutcomeId());
						insertIndicator.setInt(5, indicator.getMinMet());
						insertIndicator.addBatch();
					}
					insertIndicator.executeBatch();

					insertMeasurement = conn.prepareStatement("insert into Measurements values (?, ?, ?, ?,?)");
					for (Measurement measurement : MeasurementList) {
						insertMeasurement.setInt(1, measurement.getId());
						insertMeasurement.setString(2, measurement.getName());
						insertMeasurement.setString(3,
								measurement.getDescription());
						insertMeasurement.setInt(4,
								measurement.getIndicatorId());
						insertMeasurement.setBoolean(5, measurement.isMet());
						insertMeasurement.addBatch();
					}
					insertMeasurement.executeBatch();

					insertUser = conn.prepareStatement("insert into Users values (?,?,?,?)");
					for (User user : UserList) {
						insertUser.setString(1, user.getUsername());
						insertUser.setString(2, user.getEmail());
						insertUser.setString(3, user.getPassword());
						insertUser.setInt(4, user.getPermissions());
						insertUser.addBatch();
					}
					insertUser.executeBatch();
					
					insertRubric = conn.prepareStatement("insert into Rubrics values (?,?,?,?,?)");
					for (Rubric rubric: RubricList){
						insertRubric.setInt(1, rubric.getMeasurementId());
						insertRubric.setInt(2, rubric.getBelow());
						insertRubric.setInt(3, rubric.getMeets());
						insertRubric.setInt(4, rubric.getExceeds());
						insertRubric.setInt(5, rubric.getTarget());
						insertRubric.addBatch();
					}
					insertRubric.executeBatch();
					
					return true;
				
				} finally {
					DBUtil.closeQuietly(insertProgram);
					DBUtil.closeQuietly(insertOutcome);
					DBUtil.closeQuietly(insertIndicator);
					DBUtil.closeQuietly(insertMeasurement);
					DBUtil.closeQuietly(insertUser);
					DBUtil.closeQuietly(insertRubric);
				}
			}
		});
	}

	@Override
	public List<User> retrieveUsers() {
		return executeTransaction(new Transaction<List<User>>() {
			@Override
			public List<User> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from Users"
					);
					
					List<User> result = new ArrayList<User>();
					
					resultSet = stmt.executeQuery();
					
					while (resultSet.next()) {
						User user = new User();
						loadUser(user, resultSet, 1);
						
						result.add(user);
					}
					
					return result;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public User getUser(final String username) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try{
					stmt = conn.prepareStatement(
						"select * from Users where username=?"	
					);
					
					stmt.setString(1, username);
					
					User result = new User();
					resultSet = stmt.executeQuery();
					if (resultSet.next() == false)
					{
						return null;
					}
					else{
						loadUser(result, resultSet, 1);
					}
					
					return result;
					
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				
			}
		});
	}

	
	@Override
	public Program getProgram(final int id) {
		return executeTransaction(new Transaction<Program>() {
			@Override
			public Program execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try{
					stmt = conn.prepareStatement(
						"select * from Programs where id=?"	
					);
					
					stmt.setInt(1, id);
					
					Program result = new Program();
					resultSet = stmt.executeQuery();
					if (resultSet.next() == false)
					{
						return null;
					}
					else{
						loadProgram(result, resultSet, 1);
					}
					
					return result;
					
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				
			}
		});
	}
	
	@Override
	public Outcome getOutcome(final int id) {
		return executeTransaction(new Transaction<Outcome>() {
			@Override
			public Outcome execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try{
					stmt = conn.prepareStatement(
						"select * from Outcomes where id=?"	
					);
					
					stmt.setInt(1, id);
					
					Outcome result = new Outcome();
					resultSet = stmt.executeQuery();
					if (resultSet.next() == false)
					{
						return null;
					}
					else{
						loadOutcome(result, resultSet, 1);
					}
					
					return result;
					
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				
			}
		});
	}
	
	@Override
	public Indicator getIndicator(final int id) {
		return executeTransaction(new Transaction<Indicator>() {
			@Override
			public Indicator execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try{
					stmt = conn.prepareStatement(
						"select * from Indicators where id=?"	
					);
					
					stmt.setInt(1, id);
					
					Indicator result = new Indicator();
					resultSet = stmt.executeQuery();
					if (resultSet.next() == false)
					{
						return null;
					}
					else{
						loadIndicator(result, resultSet, 1);
					}
					
					return result;
					
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				
			}
		});
	}
	
	@Override
	public Measurement getMeasurement(final int id) {
		return executeTransaction(new Transaction<Measurement>() {
			@Override
			public Measurement execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try{
					stmt = conn.prepareStatement(
						"select * from Measurements where id=?"	
					);
					
					stmt.setInt(1, id);
					
					Measurement result = new Measurement();
					resultSet = stmt.executeQuery();
					if (resultSet.next() == false)
					{
						return null;
					}
					else{
						loadMeasurement(result, resultSet, 1);
					}
					
					return result;
					
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				
			}
		});
	}
	
	@Override
	public User addUser(final String username, final String email, final String password, final int permissions){
		if(this.getUser(username) == null)
		{
			return executeTransaction(new Transaction<User>() {
				@Override
				public User execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					try {

						stmt = conn.prepareStatement(
								"insert into Users values (?, ?, ?, ?)"
						);

					
						stmt.setString(1, username);
						stmt.setString(2, email);
						stmt.setString(3, password);
						stmt.setInt(4, permissions);

						stmt.executeUpdate();
					
						return new User(username, email, password, permissions);
					}

					finally {
						DBUtil.closeQuietly(stmt);
					}

				}
			});
		}
		else{
			return null;
		}
	}

	@Override
	public void deleteUser(final String username) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {

					stmt = conn.prepareStatement(
							"delete from Users where username=?"
					);

					
					stmt.setString(1, username);

					stmt.executeUpdate();
					
					return true;
				}

				finally {
					DBUtil.closeQuietly(stmt);
				}

			}
		});
	}

	@Override
	public User editUser(final String username, final String email, final String password, final int permissions){
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {

					stmt = conn.prepareStatement(
							"update Users " +
							"set email=?, password=?, permissions=?" +
							"where username = ?"
					);

					
					stmt.setString(1, email);
					stmt.setString(2, password);
					stmt.setInt(3, permissions);
					stmt.setString(4, username);

					stmt.executeUpdate();
					
					return new User(username, email, password, permissions);
				}

				finally {
					DBUtil.closeQuietly(stmt);
				}

			}
		});
	}
	
	@Override
	public Rubric editRubric(final int measurementId, final int below, final int meets, final int exceeds, final int target){
		return executeTransaction(new Transaction<Rubric>() {
			@Override
			public Rubric execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {

					stmt = conn.prepareStatement(
							"update Rubrics " +
							"set below=?, meets=?, exceeds=?, target=?" +
							"where measurement_id = ?"
					);

					
					stmt.setInt(1, below);
					stmt.setInt(2, meets);
					stmt.setInt(3, exceeds);
					stmt.setInt(4, target);
					stmt.setInt(5, measurementId);

					stmt.executeUpdate();
					
					return new Rubric(measurementId, below, meets, exceeds, target);
				}

				finally {
					DBUtil.closeQuietly(stmt);
				}

			}
		});
	}
	
	@Override
	public void deleteRubric(final int measurementId) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {

					stmt = conn.prepareStatement(
							"delete from Rubrics where measurement_id=?"
					);

					
					stmt.setInt(1, measurementId);

					stmt.executeUpdate();
					
					return true;
				}

				finally {
					DBUtil.closeQuietly(stmt);
				}

			}
		});
	}
	
	@Override
	public Rubric addRubric(final int measurementId, final int below, final int meets, final int exceeds, final int target){
		if(this.getRubric(measurementId) == null)
		{
			return executeTransaction(new Transaction<Rubric>() {
				@Override
				public Rubric execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					try {

						stmt = conn.prepareStatement(
								"insert into Rubrics values (?, ?, ?, ?, ?)"
						);

					
						stmt.setInt(1, measurementId);
						stmt.setInt(2, below);
						stmt.setInt(3, meets);
						stmt.setInt(4, exceeds);
						stmt.setInt(5, target);

						stmt.executeUpdate();
					
						return new Rubric(measurementId, below, meets, exceeds, target);
					}

					finally {
						DBUtil.closeQuietly(stmt);
					}

				}
			});
		}
		else{
			return null;
		}
	}
	
	@Override
	public Rubric getRubric(final int measurementId) {
		return executeTransaction(new Transaction<Rubric>() {
			@Override
			public Rubric execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try{
					stmt = conn.prepareStatement(
						"select * from Rubrics where measurement_id=?"	
					);
					
					stmt.setInt(1, measurementId);
					
					Rubric result = new Rubric();
					resultSet = stmt.executeQuery();
					if (resultSet.next() == false)
					{
						return null;
					}
					else{
						loadRubric(result, resultSet, 1);
					}
					
					return result;
					
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
				
			}
		});
	}
	
	public List<Rubric> retrieveRubrics() {
		return executeTransaction(new Transaction<List<Rubric>>() {
			@Override
			public List<Rubric> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from Rubrics"
					);
					
					List<Rubric> result = new ArrayList<Rubric>();
					
					resultSet = stmt.executeQuery();
					
					while (resultSet.next()) {
						Rubric rubric = new Rubric();
						loadRubric(rubric, resultSet, 1);
						
						result.add(rubric);
					}
					
					return result;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public void updateMet(final int measurementId, final boolean met){
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {

					stmt = conn.prepareStatement(
							"update Measurements " +
							"set met=?" +
							"where id = ?"
					);

					
					stmt.setBoolean(1, met);
					stmt.setInt(2, measurementId);

					stmt.executeUpdate();
					
					return true;
				}

				finally {
					DBUtil.closeQuietly(stmt);
				}

			}
		});
	}
}
