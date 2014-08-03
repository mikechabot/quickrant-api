package com.quickrant.api.models;

import java.sql.Timestamp;

import org.javalite.activejdbc.Model;

public class DatabaseStats extends Model {

	public void setAvgPrepare(double avgPrepare) {
		set("avg_prepare", avgPrepare);
	}
	
	public double getAvgPrepare() {
		return getDouble("avg_prepare");
	}
	
	public void setAvgExecution(double avgExecution) {
		set("avg_execution", avgExecution);
	}
	
	public double getAvgExecution() {
		return getDouble("avg_execution");
	}
	
	public void setAvgConnectionWait(double avgConnectionWait) {
		set("avg_connection_wait", avgConnectionWait);
	}
	
	public double getAvgConnectionWait() {
		return getDouble("avg_connection_wait");
	}
	
	public void setTotalCreatedConnections(int totalCreatedConnections) {
		set("total_created_connections", totalCreatedConnections);
	}
	
	public int getTotalCreatedConnections() {
		return getInteger("total_created_connections");
	}
	
	public void setLeasedConnections(int leasedConnections) {
		set("leased_connections", leasedConnections);
	}
	
	public int getLeasedConnections() {
		return getInteger("leased_connections");
	}
	
	public void setFreeConnections(int freeConnections) {
		set("free_connections", freeConnections);
	}
	
	public int getFreeConnections() {
		return getInteger("free_connections");
	}
	
	public void setCacheHitRatio(double cacheHitRatio) {
		set("cache_hit_ratio", cacheHitRatio);
	}
	
	public double getCacheHitRatio() {
		return getDouble("cache_hit_ratio");
	}
	
	public void setCacheMisses(long cacheMisses) {
		set("cache_misses", cacheMisses);
	}
	
	public Long getCacheMisses() {
		return getLong("cache_misses");
	}
	
	public void setCacheHits(long cacheHits) {
		set("cache_hits", cacheHits);
	}
	
	public Long getCacheHits() {
		return getLong("cache_hits");
	}
	
	public void setCreatedAt(Timestamp createdAt) {
		set("created_at", createdAt);
	}
	
	public Timestamp getCreatedAt() {
		return getTimestamp("created_at");
	}

	public void setBonePartitions(int bonePartitions) {
		set("bone_partitions", bonePartitions);
	}
	
	public int getBonePartitions() {
		return getInteger("bone_partitions");
	}
	
	public void setBoneMaxConnections(int maxConnections) {
		set("bone_max_connections", maxConnections);		
	}
	
	public int getBoneMaxConnections() {
		return getInteger("bone_max_connections");
	}
	
	public void setBoneMinConnections(int minConnections) {
		set("bone_min_connections", minConnections);
	}
	
	public int getBoneMinConnections() {
		return getInteger("bone_min_connections");
	}
	
}
