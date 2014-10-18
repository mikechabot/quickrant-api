package com.quickrant.api.models;

import java.sql.Timestamp;
import java.util.List;

import org.javalite.activejdbc.Model;

import com.quickrant.api.utils.TimeUtils;

public class Rant extends Model {
	
	static { validatePresenceOf("rant", "emotion_id", "question_id"); }

	public void setVisitorId(int visitorId) {
		set("visitor_id", visitorId);
	}
	
	public int getVisitorId() {
		return getInteger("visitor_id");
	}

	public void setVisitorName(String visitorName) {
		set("visitor_name", visitorName);
	}
	
	public String getVisitorName() {
		return getString("visitor_name");
	}
	
	public void setRant(String rant) {
		set("rant", rant);
	}
	
	public String getRant() {
		return getString("rant");
	}
	
	public void setEmotionId(int emotionId) {
		set("emotion_id", emotionId);
	}
	
	public Integer getEmotionId() {
		return getInteger("emotion_id");
	}

	public void setQuestionId(int questionId) {
		set("question_id", questionId);
	}
	
	public Integer getQuestionId() {
		return getInteger("question_id");
	}
	
	public void setLocation(String location) {
		set("location", location);
	}
	
	public String getLocation() {
		return getString("location");
	}
	
	public void setCreated(Timestamp created) {
		set("created_at", created);
	}
	
	public String getCreated() {
		return TimeUtils.getFormattedDate(getTimestamp("created_at"));
	}
	
	public static List<Rant> getBySql(String sql) throws RantNotFoundException {
		List<Rant> rants = Rant.findBySQL(sql);
		if (rants != null && !rants.isEmpty()) {
			return rants;
		} else {
			throw new RantNotFoundException("Rants not found: " + sql);
		}
	}
	
	public static Rant getById(Object id) throws RantNotFoundException {
		Rant rant = Rant.findById(id);
		if (rant != null) {
			return rant;
		} else {
			throw new RantNotFoundException("Rant not found: " + id);
		}
	}
	
	public static class RantNotFoundException extends Exception {
		private static final long serialVersionUID = 7236482818893873830L;
		public RantNotFoundException() {}
	      public RantNotFoundException(String message) {
	         super(message);
	      }
	 }
}