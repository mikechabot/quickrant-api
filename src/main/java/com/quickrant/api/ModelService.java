package com.quickrant.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.javalite.activejdbc.Model;

import com.quickrant.api.database.Database;
import com.quickrant.api.database.DatabaseUtil;

public abstract class ModelService {
		
	private static Logger log = Logger.getLogger(ModelService.class);

	protected abstract Long getCount();
	protected abstract List<Model> findAll();
	protected abstract List<Model> findAll(String subQuery, Object params);
	protected abstract List<Model> findBySql(String sql); 
	protected abstract Model findById(int id);
	protected abstract Model findFirst(String subQuery, Object params);
	public abstract Model parse(Map<String, String> map);	
	public abstract boolean save(Map<String, String> map);

	/**
	 * Get table row count
	 * @param id
	 * @return
	 */
	public Long count(int id) {
		Long count = (long) 0;
		Database database = null;
		try {
			database = new Database();
			database.open();
			count = getCount();
		} catch (SQLException e) {
			log.error("Unable to open connection to database", e);
		} finally {
			DatabaseUtil.close(database);
		}
		return count;
	}
	
	/**
	 * Fetch all records
	 * @return
	 */
	public List<Model> fetch() {
		List<Model> list = new ArrayList<Model>();
		Database database = null;
		try {
			database = new Database();
			database.open();
			List<Model> temp = findAll();
			/* Iterate to fetch */
			for (Model each : temp) {
				list.add(each);
			}
		} catch (SQLException e) {
			log.error("Unable to open connection to database", e);
		} finally {
			DatabaseUtil.close(database);
		}
		return list;
	}
	
	/**
	 * Fetch all records with query
	 * @param subQuery ("id = ?")
	 * @param params ("2")
	 * @return
	 */
	public List<Model> fetch(String subQuery, Object params) {
		List<Model> list = new ArrayList<Model>();
		Database database = null;
		try {
			database = new Database();
			database.open();
			List<Model> temp = findAll(subQuery, params);
			/* Iterate to fetch */
			for (Model each : temp) {
				list.add(each);
			}
		} catch (SQLException e) {
			log.error("Unable to open connection to database", e);
		} finally {
			DatabaseUtil.close(database);
		}
		return list;
	}
	
	/**
	 * Fetch records by sql
	 * @param id
	 * @return
	 */
	public List<Model> fetchBySql(String sql) {
		List<Model> list = new ArrayList<Model>();
		Database database = null;
		try {
			database = new Database();
			database.open();
			List<Model> temp = findBySql(sql);
			/* Iterate to fetch */
			for (Model each : temp) {
				list.add(each);
			}
		} catch (SQLException e) {
			log.error("Unable to open connection to database", e);
		} finally {
			DatabaseUtil.close(database);
		}
		return list;
	}

	/**
	 * Fetch single record
	 * @param id
	 * @return
	 */
	public Model fetchById(int id) {
		Model model = null;
		Database database = null;
		try {
			database = new Database();
			database.open();
			model = findById(id);
		} catch (SQLException e) {
			log.error("Unable to open connection to database", e);
		} finally {
			DatabaseUtil.close(database);
		}
		return model;
	}

	/**
	 * Fetch first record
	 * @param subQuery ("id = ?")
	 * @param params ("2")
	 * @return Model
	 */
	public Model fetchFirst(String subQuery, Object params) {
		Model model = null;
		Database database = null;
		try {
			database = new Database();
			database.open();
			model = findFirst(subQuery, params);
		} catch (SQLException e) {
			log.error("Unable to open connection to database", e);
		} finally {
			DatabaseUtil.close(database);
		}
		return model;
	}

	/**
	 * Fetch first record
	 * @param model
	 */
	public boolean save(Model model) {
		Database database = null;
		try {
			database = new Database();
			database.open();
			model.saveIt();
		} catch (SQLException e) {
			log.error("Unable to open connection to database", e);
		} finally {
			DatabaseUtil.close(database);
		}
		return true;
	}
	
	/**
	 * Parse a model from a map
	 * @param model
	 * @param map
	 * @return
	 */
	protected Model parse(Model model, Map<String, String> map) {
		Database database = null;
		try {
			database = new Database();
			database.open();
			model.fromMap(map);
		} catch (SQLException e) {
			log.error("Unable to open connection to database", e);
		} finally {
			DatabaseUtil.close(database);
		}
		return model;
	}
	
}