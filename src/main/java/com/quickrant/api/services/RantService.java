package com.quickrant.api.services;

import java.util.List;
import java.util.Map;

import org.javalite.activejdbc.Model;

import com.quickrant.api.ModelService;
import com.quickrant.api.database.CustomSql;
import com.quickrant.api.models.Emotion;
import com.quickrant.api.models.Question;
import com.quickrant.api.models.Rant;

public class RantService extends ModelService {
	
	private static QuestionService questionSvc = new QuestionService();
	private static EmotionService emotionSvc = new EmotionService();
	
	@Override
	public List<Model> findAll() {
		return Rant.findBySQL(CustomSql.FETCH_TOP_40_RANTS);
	}
	
	@Override
	public Model findById(int id) {
		return Rant.findById(id);
	}
	
	@Override
	protected Model findFirst(String subQuery, Object value) {
		return Rant.findFirst(subQuery, value);
	}
	
	@Override
	protected Rant parse(Map<String, String> map) {		
		Rant rant = new Rant();
		rant.fromMap(map);
		setDefaults(rant);
		return rant;
	}
	
	@Override
	public boolean save(Map<String, String> map) {
		Rant rant = parse(map);
		
		/* Parse emotion and question from input */
		Emotion emotion = emotionSvc.parse(map);
		Question question = questionSvc.parse(map);

		/* Fetch question and emotion */
		emotion = (Emotion) emotionSvc.fetchFirst("emotion = ?", emotion.getEmotion());
		question = (Question) questionSvc.fetchFirst("question = ?", question.getQuestion());

		/* Set foreign keys */
		rant.setEmotionId((int) emotion.getId());
		rant.setQuestionId((int) question.getId());
		
		/* Check if rant is valid, then save */
		if (!rant.isValid()) return false;
		save(rant);		
		return true;
	}
		
	/**
	 * Set default data on some fields
	 * @param rant
	 */
	private void setDefaults(Rant rant) {
		if (rant.getVisitorName() == null || rant.getVisitorName().isEmpty()) {
			rant.setVisitorName("Anonymous");
		}
		if (rant.getLocation() == null || rant.getLocation().isEmpty()) {
			rant.setLocation("Earth");
		}
	}

	@Override
	protected List<Model> findAll(String subQuery, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

}