package com.quickrant.api.services;

import java.util.List;
import java.util.Map;

import org.javalite.activejdbc.Model;

import com.quickrant.api.AbstractService;
import com.quickrant.api.models.CacheStats;

public class CacheStatsService extends AbstractService {

	@Override
	public Long count() {
		return CacheStats.count();
	}

	@Override
	public List<Model> findAll() {
		return CacheStats.findAll();
	}

	@Override
	public List<Model> find(String subQuery, Object params) {
		return CacheStats.find(subQuery, params);
	}

	@Override
	public List<Model> findBySql(String sql) {
		return CacheStats.findBySQL(sql);
	}

	@Override
	public CacheStats findById(int id) {
		return CacheStats.findById(id); 
	}

	@Override
	public CacheStats findFirst(String subQuery, Object params) {
		return CacheStats.findFirst(subQuery, params);
	}

	@Override
	public CacheStats parse(Map<String, String> map) {
		CacheStats cacheInfo = new CacheStats();
		parse(cacheInfo, map);
		return cacheInfo;
	}

	@Override
	public boolean save(Map<String, String> map) {
		CacheStats cacheInfo = parse(map);
		save(cacheInfo);
		return true;
	}
	
	public void save(CacheStats cacheStats) {
		super.save(cacheStats);
	}

}