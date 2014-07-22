package com.quickrant.api.services;

import java.util.List;
import java.util.Map;

import org.javalite.activejdbc.Model;

import com.quickrant.api.ModelService;
import com.quickrant.api.models.Question;

public class QuestionService extends ModelService {

	@Override
	public List<Model> findAll() {
		return Question.findAll();
	}

	@Override
	public Question findById(int id) {
		return Question.findById(id);
	}

	@Override
	protected Question findFirst(String subQuery, Object value) {
		return Question.findFirst(subQuery, value);
	}

	@Override
	protected Question parse(Map<String, String> map) {
		Question question = new Question();
		question.fromMap(map);
		return question;
	}

	@Override
	protected boolean save(Map<String, String> map) {
		// TODO Auto-generated method stub
		return false;
	}

}
