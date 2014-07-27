package com.quickrant.api.services;

import java.util.List;
import java.util.Map;

import org.javalite.activejdbc.Model;

import com.quickrant.api.AbstractService;
import com.quickrant.api.models.Question;

public class QuestionService extends AbstractService {

	@Override
	public Long count() {
		return Question.count();
	}

	@Override
	public List<Model> findAll() {
		return Question.findAll();
	}
	
	@Override
	public List<Model> find(String subQuery, Object params) {
		return Question.find(subQuery, params);
	}
	
	@Override
	public List<Model> findBySql(String sql) {
		return Question.findBySQL(sql);
	}	

	@Override
	public Question findById(int id) {
		return Question.findById(id);
	}

	@Override
	public Question findFirst(String subQuery, Object value) {
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
