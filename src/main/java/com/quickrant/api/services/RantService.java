package com.quickrant.api.services;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.javalite.activejdbc.Model;

import com.quickrant.api.ModelService;
import com.quickrant.api.models.Emotion;
import com.quickrant.api.models.Question;
import com.quickrant.api.models.Rant;
import com.quickrant.api.models.Visitor;
import com.quickrant.api.utils.TimeUtils;

public class RantService extends ModelService {
	
	public static final String FETCH_TOP_40_RANTS = "select id, created_at, emotion_id, question_id, rant, visitor_name, location from rants order by id desc limit 40";
	public static Logger log = Logger.getLogger(RantService.class);
	
	private static EmotionService emotionSvc = new EmotionService();
	private static VisitorService visitorSvc = new VisitorService();
	private static QuestionService questionSvc = new QuestionService();
	
	@Override
	protected Long getCount() {
		return Rant.count();
	}
	
	@Override
	protected List<Model> findAll() {
		return Rant.findBySQL(FETCH_TOP_40_RANTS);
	}
	
	@Override
	protected List<Model> findAll(String subQuery, Object params) {
		return Rant.find(subQuery, params);
	}
	
	@Override
	protected List<Model> findBySql(String sql) {
		return Rant.findBySQL(sql);
	}
	
	@Override
	protected Rant findById(int id) {
		return Rant.findById(id);
	}
	
	@Override
	protected Rant findFirst(String subQuery, Object value) {
		return Rant.findFirst(subQuery, value);
	}
	
	@Override
	public Rant parse(Map<String, String> map) {		
		Rant rant = new Rant();
		parse(rant, map);
		setDefaults(rant);
		return rant;
	}
	
	@Override
	public boolean save(Map<String, String> map) {		
		/* Parse emotion and question from input */
		Rant rant = parse(map);
		Emotion emotion = emotionSvc.parse(map);
		Visitor visitor = visitorSvc.parse(map);
		Question question = questionSvc.parse(map);

		/* Fetch question and emotion */
		visitor = (Visitor) visitorSvc.fetchFirst("cookie = ?", visitor.getCookie());
		emotion = (Emotion) emotionSvc.fetchFirst("emotion = ?", emotion.getEmotion());
		question = (Question) questionSvc.fetchFirst("question = ?", question.getQuestion());
		
		/* Set last rant time */
		visitor.setLastRant(TimeUtils.getNowTimestamp());
		
		/* Set foreign keys */
		rant.setVisitorId((int) visitor.getId());
		rant.setEmotionId((int) emotion.getId());
		rant.setQuestionId((int) question.getId());

		/* Check if rant is valid, then save */
		if (!rant.isValid()) return false;
		if (!visitor.isValid()) return false;
		save(rant); 
		save(visitor);
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

}