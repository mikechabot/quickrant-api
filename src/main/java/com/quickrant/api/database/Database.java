package com.quickrant.api.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.javalite.activejdbc.Base;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;
import com.jolbox.bonecp.Statistics;
import com.quickrant.api.Configuration;
import com.quickrant.api.models.DatabaseStats;

public class Database {

	private static Logger log = Logger.getLogger(Database.class);

	private static BoneCPDataSource dataSource;
	private static boolean doRunStats;
	private static int partitions;
	private static int minConnections;
	private static int maxConnections;
	public static String version;
	
	private Timer timer;
	
	static {
		try {
			/* Load the driver */
			Class.forName("org.postgresql.Driver");

			/* Obtain values from the configuration */
			Configuration config = Configuration.getInstance();
			String username = config.getRequiredString("postgres-username");
			String password = config.getRequiredString("postgres-password");
			String hostname = config.getRequiredString("postgres-hostname");
			String database = config.getRequiredString("postgres-database");

			/* Pool defaults: 5 thread pools with a max of 50 threads each */
			partitions = config.getOptionalInt("bonecp-partitions", 5);
			minConnections = config.getOptionalInt("bonecp-min-pool-size", 5);
			maxConnections = config.getOptionalInt("bonecp-max-pool-size", 50);
			doRunStats = config.getOptionalBoolean("bonecp-run-stats-job", false);

			/* Initialize the BoneCP connection pool */
			BoneCPConfig boneConfig = new BoneCPConfig();
			boneConfig.setJdbcUrl("jdbc:postgresql://" + hostname + "/"	+ database);
			boneConfig.setUsername(username);
			boneConfig.setPassword(password);
			boneConfig.setPartitionCount(partitions);
			boneConfig.setMinConnectionsPerPartition(minConnections);
			boneConfig.setMaxConnectionsPerPartition(maxConnections);

			/* Create the connection pool */
			dataSource = new BoneCPDataSource(boneConfig);
			
		} catch (ClassNotFoundException e) {
			log.error("Driver not found", e);
		} catch (Exception e) {
			log.error("Unable to initalize datasource", e);
		}
	}

	public Database() { }
	
	public void startStatisticsJob() {
		if (doRunStats) {
			log.info("Starting statistics job...");
			timer = new Timer();
	        timer.schedule(new RunStatistics(), 10000, 30000);	
		}
	}

	public static void open() throws SQLException {
		Base.open(dataSource);
	}

	public static void close() {
		Base.close();
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public BoneCP getPool() {
		return dataSource.getPool();
	}

	public Statement getStatement() throws SQLException {
		return Base.connection().createStatement();
	}

	public PreparedStatement getPreparedStatement(String sql) throws SQLException {
		return Base.connection().prepareStatement(sql);
	}

	public String getVersion() throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			open();
			preparedStatement = getPreparedStatement("select version()");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) version = resultSet.getString(1); 
		} finally {
			DatabaseUtil.close(resultSet);
			DatabaseUtil.close(preparedStatement);
			close();
		}
		return version;
	}
	
	public class RunStatistics extends TimerTask {
		
		private Statistics stats;
		private DatabaseStats model;
		
		@Override
		public void run() {
			try {
				open();
				saveStats();
			} catch (SQLException e) {
				log.error("Unable to save database stats", e);
			} finally {
				close();
			}
		}

		public void saveStats() {
			stats = new Statistics(getPool());
			model = new DatabaseStats();
			model.setBonePartitions(partitions);
			model.setBoneMaxConnections(maxConnections);
			model.setBoneMinConnections(minConnections);
			model.setAvgConnectionWait(stats.getConnectionWaitTimeAvg());
			model.setAvgExecution(stats.getStatementExecuteTimeAvg());
			model.setAvgPrepare(stats.getStatementPrepareTimeAvg());
			model.setCacheHits(stats.getCacheHits());
			model.setCacheMisses(stats.getCacheMiss());
			model.setCacheHitRatio(stats.getCacheHitRatio());
			model.setFreeConnections(stats.getTotalFree());
			model.setLeasedConnections(stats.getTotalLeased());
			model.setTotalCreatedConnections(stats.getTotalCreatedConnections());
			model.saveIt();
			log.info("Saving stats - " + model.toString());
		}
	}
}