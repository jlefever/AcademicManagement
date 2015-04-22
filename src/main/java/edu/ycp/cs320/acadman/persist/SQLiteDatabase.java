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
	public Measurement addMeasurement(final String name, final String description, final boolean isMet,
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
					stmt.setBoolean(4, isMet);

					stmt.executeUpdate();
					
					// get generated keys
					genKeys = stmt.getGeneratedKeys();
					genKeys.next(); // one tuple should be returned
				
					int generatedId = genKeys.getInt(1);
					System.out.println("Added measurement with id=" + generatedId);
					
					return new Measurement(generatedId, name, description, isMet, indicatorId);
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
		program.setYearId(resultSet.getInt(index++));
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
							"set name=?, description=?, min_met=? program_id=?" +
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
	
	public Measurement editMeasurement(final int id, final String name, final String description, final boolean isMet, final int indicatorId){
		return executeTransaction(new Transaction<Measurement>() {
			@Override
			public Measurement execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {

					stmt = conn.prepareStatement(
							"update Measurements " +
							"set name=?, description=?, met=?, indicator_id=?" +
							"where id = ?"
					);

					
					stmt.setString(1, name);
					stmt.setString(2, description);
					stmt.setBoolean(3, isMet);
					stmt.setInt(4, indicatorId);
					stmt.setInt(5, id);

					stmt.executeUpdate();
					
					return new Measurement(id, name, description, isMet, indicatorId);
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
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		SQLiteDatabase db = new SQLiteDatabase();
		db.createTables();

		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		for (Program i : db.retrievePrograms(3)){
			System.out.println(i);
		}
		
		db.editProgram(1, "edited", "new descript", 3);
		
		for (Program i : db.retrievePrograms(3)){
			System.out.println(i);
		}
		
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

					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
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

				try {
					ProgramList = InitialData.readPrograms();
					OutcomeList = InitialData.readOutcomes();
					IndicatorList = InitialData.readIndicators();
					MeasurementList = InitialData.readMeasurements();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertProgram = null;
				PreparedStatement insertOutcome = null;
				PreparedStatement insertIndicator = null;
				PreparedStatement insertMeasurement = null;

				try {
					insertProgram = conn
							.prepareStatement("insert into Programs values (?, ?, ?, ?)");
					for (Program program : ProgramList) {
						insertProgram.setInt(1, program.getId());
						insertProgram.setString(2, program.getName());
						insertProgram.setString(3, program.getDescription());
						insertProgram.setInt(4, program.getYearId());
						insertProgram.addBatch();
					}
					insertProgram.executeBatch();
					// finish
					insertOutcome = conn
							.prepareStatement("insert into Outcomes values (?, ?, ?, ?,?)");
					for (Outcome outcome : OutcomeList) {
						insertOutcome.setInt(1, outcome.getId());
						insertOutcome.setString(2, outcome.getName());
						insertOutcome.setString(3, outcome.getDescription());
						insertOutcome.setInt(4, outcome.getProgramId());
						insertOutcome.setInt(5, outcome.getMinMet());
						insertOutcome.addBatch();
					}
					insertOutcome.executeBatch();

					insertIndicator = conn
							.prepareStatement("insert into Indicators values (?, ?, ?, ?,?)");
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

					insertMeasurement = conn
							.prepareStatement("insert into Measurements values (?, ?, ?, ?,?)");
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

					return true;
				} finally {
					DBUtil.closeQuietly(insertProgram);
					DBUtil.closeQuietly(insertOutcome);
					DBUtil.closeQuietly(insertIndicator);
					DBUtil.closeQuietly(insertMeasurement);
				}
			}
		});
	}

}
