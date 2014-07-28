package com.quickrant.api.services;

import java.util.List;
import java.util.Map;

import org.javalite.activejdbc.Model;

import com.quickrant.api.AbstractService;
import com.quickrant.api.models.DatabaseStats;

public class DatabaseStatsService extends AbstractService {

	@Override
	public Long count() {
		return DatabaseStats.count();
	}

	@Override
	public List<Model> findAll() {
		return DatabaseStats.findAll();
	}

	@Override
	public List<Model> find(String subQuery, Object params) {
		return DatabaseStats.find(subQuery, params);
	}

	@Override
	public List<Model> findBySql(String sql) {
		return DatabaseStats.findBySQL(sql);
	}

	@Override
	public DatabaseStats findById(int id) {
		return DatabaseStats.findById(id);
	}

	@Override
	public DatabaseStats findFirst(String subQuery, Object params) {
		return DatabaseStats.findFirst(subQuery, params);
	}

	@Override
	public DatabaseStats parse(Map<String, String> map) {
		DatabaseStats databaseStats = new DatabaseStats();
		parse(databaseStats, map);
		return databaseStats;
	}

	@Override
	public boolean save(Map<String, String> map) {
		DatabaseStats databaseStats = parse(map);
		save(databaseStats);
		return true;
	}
	
	public void save(DatabaseStats databaseStats) {
		super.save(databaseStats);
	}

}
