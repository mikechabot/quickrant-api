package com.quickrant.api.services;

import java.util.List;
import java.util.Map;

import org.javalite.activejdbc.Model;

import com.quickrant.api.ModelService;
import com.quickrant.api.models.Emotion;

public class EmotionService extends ModelService {

	@Override
	protected List<Model> findAll() {
		return Emotion.findAll();
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
	protected Emotion parse(Map<String, String> map) {
		Emotion emotion = new Emotion();
		emotion.fromMap(map);
		return emotion;
	}

	@Override
	protected boolean save(Map<String, String> map) {
		// TODO Auto-generated method stub
		return false;
	}

}
