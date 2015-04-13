package edu.ycp.cs320.acadman.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
			
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new	RuntimeException("Transaction failed", e);
		}
	}
	
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
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
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
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
		
		Connection conn = DriverManager.getConnection("jdbc:sqlite:" + home + "/assessment.db");
		
		// Set auto commit to false to allow multiple the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	@Override
	public void readInitialData() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Program> retrievePrograms(int yearId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Outcome> retrieveOutcomes(int programId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Indicator> retrieveIndicators(int outcomeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Measurement> retrieveMeasurements(int indicatorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addProgram(String name, String description, int yearId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addOutcome(String name, String description, int minMet,
			int programId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addIndicator(String name, String description, int minMet,
			int outcomeId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addMeasurement(String name, String description, boolean isMet,
			int indicatorId) {
		// TODO Auto-generated method stub

	}
	
	// The main method creates the database tables and loads the initial data.
		public static void main(String[] args) throws IOException {
			System.out.println("Creating tables...");
			SQLiteDatabase db = new SQLiteDatabase();
			db.createTables();
			
			System.out.println("Loading initial data...");
			db.loadInitialData();
			
			System.out.println("Success!");
		}

		private void createTables() {
			// TODO Auto-generated method stub
			executeTransaction(new Transaction<Boolean>() {
				@Override
				public Boolean execute(Connection conn) throws SQLException {
					PreparedStatement stmt1 = null;
					PreparedStatement stmt2 = null;
					PreparedStatement stmt3 = null;
					PreparedStatement stmt4 = null;
					
					try {
						stmt1 = conn.prepareStatement(
								"create table programs(" +
								"    id integer primary key," +
								"    name varchar(200)," +
								"    description varchar(2000)" +
								"    year integer," +
								")");
						stmt1.executeUpdate();
						
						stmt2 = conn.prepareStatement(
								"create table outcomes(" +
								"    id integer primary key," +
								"    name varchar(200)," +
								"    description varchar(2000)" +
								"    program_id integer," +
								"    min_met integer," +
								")");
						stmt2.executeUpdate();
						
						stmt3 = conn.prepareStatement(
								"create table indicators(" +
								"    id integer primary key," +
								"    name varchar(200)," +
								"    description varchar(2000)" +
								"    outcome_id integer," +
								"    min_met integer," +
								")");
						stmt3.executeUpdate();
						
						stmt4 = conn.prepareStatement(
								"create table measurement(" +
								"    id integer primary key," +
								"    name varchar(200)," +
								"    description varchar(2000)" +
								"    indicator_id integer," +
								"    met boolean," +
								")");
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
						insertProgram = conn.prepareStatement("insert into Programs values (?, ?, ?)");
						for (Program program : ProgramList) {
							insertProgram.setInt(1, program.getId());
							insertProgram.setString(2, program.getName());
							insertProgram.setString(3, program.getDescription());
							insertProgram.addBatch();
						}
						insertProgram.executeBatch();
						//finish 
						insertOutcome = conn.prepareStatement("insert into Outcomes values (?, ?, ?, ?,?)");
						for (Outcome outcome : OutcomeList) {
							insertOutcome.setInt(1, outcome.getId());
							insertOutcome.setInt(2, outcome.getProgramId());
							insertOutcome.setString(3, outcome.getName());
							insertOutcome.setString(4, outcome.getDescription());
							insertOutcome.setInt(5, outcome.getMinMet());
							insertOutcome.addBatch();
						}
						insertOutcome.executeBatch();
						
						insertIndicator = conn.prepareStatement("insert into Idicator values (?, ?, ?, ?,?)");
						for (Indicator indicator : IndicatorList) {
							insertIndicator.setInt(1, indicator.getId());
							insertIndicator.setInt(2, indicator.getOutcomeId());
							insertIndicator.setString(3, indicator.getName());
							insertIndicator.setString(4, indicator.getDescription());
							insertIndicator.setInt(5, indicator.getMinMet());
							insertIndicator.addBatch();
						}
						insertIndicator.executeBatch();
						
						insertMeasurement = conn.prepareStatement("insert into measurement values (?, ?, ?, ?,?)");
						for (Measurement measurement : MeasurementList) {
							insertMeasurement.setInt(1, measurement.getId());
							insertMeasurement.setInt(2, measurement.getIndicatorId());
							insertMeasurement.setString(3, measurement.getName());
							insertMeasurement.setString(4, measurement.getDescription());
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
