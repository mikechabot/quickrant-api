package com.quickrant.api.models;

import org.javalite.activejdbc.Model;

public class Question extends Model {

	public void setQuestion(String question) {
		setString("question", question);
	}
	
	public String getQuestion() {
		return getString("question");
	}
	
	public void setEmotionId(int emotionId) {
		setString("emotion_id", emotionId);
	}
	
	public String getEmotionId() {
		return getString("emotion_id");
	}

}
