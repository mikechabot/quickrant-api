package com.quickrant.api.services;

import java.util.List;
import java.util.Map;

import org.javalite.activejdbc.Model;

import com.quickrant.api.ModelService;
import com.quickrant.api.models.Emotion;

public class EmotionService extends ModelService {

	@Override
	protected Long getCount() {
		return Emotion.count();
	}

	@Override
	protected List<Model> findAll() {
		return Emotion.findAll();
	}
	
	@Override
	protected List<Model> findAll(String subQuery, Object params) {
		return Emotion.find(subQuery, params);
	}
	
	@Override
	protected List<Model> findBySql(String sql) {
		return Emotion.findBySQL(sql);
	}

	@Override
	protected Emotion findById(int id) {
		return Emotion.findById(id);
	}
	
	@Override
	protected Emotion findFirst(String subQuery, Object value) {
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
