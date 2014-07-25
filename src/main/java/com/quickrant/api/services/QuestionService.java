package com.quickrant.api.services;

import java.util.List;
import java.util.Map;

import org.javalite.activejdbc.Model;

import com.quickrant.api.ModelService;
import com.quickrant.api.models.Question;

public class QuestionService extends ModelService {

	@Override
	protected Long getCount() {
		return Question.count();
	}

	@Override
	protected List<Model> findAll() {
		return Question.findAll();
	}
	
	@Override
	protected List<Model> findAll(String subQuery, Object params) {
		return Question.find(subQuery, params);
	}
	
	@Override
	protected List<Model> findBySql(String sql) {
		return Question.findBySQL(sql);
	}	

	@Override
	protected Question findById(int id) {
		return Question.findById(id);
	}

	@Override
	protected Question findFirst(String subQuery, Object value) {
		return Question.findFirst(subQuery, value);
	}

	@Override
	public Question parse(Map<String, String> map) {
		Question question = new Question();
		parse(question, map);
		return question;
	}

	@Override
	public boolean save(Map<String, String> map) {
		// TODO Auto-generated method stub
		return false;
	}

}
