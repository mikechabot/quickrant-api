package com.quickrant.api.models;

import java.sql.Timestamp;

import org.javalite.activejdbc.Model;

public class CacheStats extends Model { 
	
	public void setCacheName(String cacheName) {
		set("cache_name", cacheName);
	}
	
	public String getCacheName() {
		return getString("cache_name");
	}
	
	public void setEntries(int entries) {
		set("entries", entries);
	}
	
	public int getEntries() {
		return getInteger("entries");
	}
	
	public void setExpiry(long expiry) {
		set("expiry", expiry);
	}
	
	public Long getExpiry() {
		return getLong("expiry");
	}
	
	public void setNextRunTime(Timestamp nextRunTime) {
		set("next_run_time", nextRunTime);
	}
	
	public Timestamp getNextRunTime() {
		return getTimestamp("next_run_time");
	}
	
	public void setCreatedAt(Timestamp createdAt) {
		set("created_at", createdAt);
	}
	
	public Timestamp getCreatedAt() {
		return getTimestamp("created_at");
	}
	
}