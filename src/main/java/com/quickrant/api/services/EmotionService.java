package com.quickrant.api.services;

import java.util.List;
import java.util.Map;

import org.javalite.activejdbc.Model;

import com.quickrant.api.AbstractService;
import com.quickrant.api.models.Emotion;

public class EmotionService extends AbstractService {

	@Override
	public Long count() {
		return Emotion.count();
	}

	@Override
	public List<Model> findAll() {
		return Emotion.findAll();
	}
	
	@Override
	public List<Model> find(String subQuery, Object params) {
		return Emotion.find(subQuery, params);
	}
	
	@Override
	public List<Model> findBySql(String sql) {
		return Emotion.findBySQL(sql);
	}

	@Override
	public Emotion findById(int id) {
		return Emotion.findById(id);
	}
	
	@Override
	public Emotion findFirst(String subQuery, Object value) {
		return Emotion.findFirst(subQuery, value);		
	}

	@Override
	public Emotion parse(Map<String, String> map) {
		Emotion emotion = new Emotion();
		parse(emotion, map);
		return emotion;
	}

	@Override
	public boolean save(Map<String, String> map) {
		// TODO Auto-generated method stub
		return false;
	}

}
