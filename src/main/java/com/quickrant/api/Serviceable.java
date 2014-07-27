package com.quickrant.api;

import java.util.List;
import java.util.Map;

import org.javalite.activejdbc.Model;

public interface Serviceable {

	Long count();
	List<Model> findAll();
	List<Model> find(String subQuery, Object params);
	List<Model> findBySql(String sql); 
	Model findById(int id);
	Model findFirst(String subQuery, Object params);
	Model parse(Map<String, String> map);	
	boolean save(Map<String, String> map);
	
}
