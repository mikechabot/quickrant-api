package com.quickrant.api.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javalite.activejdbc.Model;

import com.quickrant.api.AbstractService;
import com.quickrant.api.Params;
import com.quickrant.api.models.Visitor;

public class VisitorService extends AbstractService {

	@Override
	public Long count() {
		return Visitor.count();
	}
	
	@Override
	public List<Model> findAll() {
		return Visitor.findAll();
	}
	
	@Override
	public List<Model> find(String subQuery, Object value) {
		return Visitor.find(subQuery, value);
	}
	
	@Override
	public List<Model> findBySql(String sql) {
		return Visitor.findBySQL(sql);
	}
	
	@Override
	public Visitor findById(int id) {
		return Visitor.findById(id);
	}

	@Override
	public Visitor findFirst(String subQuery, Object value) {
		return Visitor.findFirst(subQuery, value);
	}
	
	@Override
	public Visitor parse(Map<String, String> map) {
		Visitor visitor = new Visitor();
		parse(visitor, map);
		return visitor;
	}
	
	@Override
	public boolean save(Map<String, String> map) {
		Visitor visitor = parse(map);
		visitor.setComplete(false);
		save(visitor);
		return true;
	}

	/**
	 * Create a new site visitor and return an HTTP cookie
	 * @param params
	 * @return
	 */
	public void save(Params params, String cookieValue) {
		Map<String, String> map = new HashMap<String, String>(0);
		map.put("ip_address", params.getIpAddress());
		map.put("user_agent", params.getUserAgent());
		map.put("fingerprint", params.getIpAddress() + ":" + params.getUserAgent());
		map.put("cookie", cookieValue);
		save(map);
	}

	/**
	 * Complete a visitor when the /phonehome AJAX request is received
	 * @param existing
	 * @param params
	 * @param cookie
	 */
	public void completeVisitor(Visitor existing, Params params, String cookieValue) {
		/* Parse temp values from request parameters */
		Visitor temp = parse(params.getMap());
		/* Update the existing record with request data */
		existing.setScreenColor(temp.getScreenColor());
		existing.setScreenHeight(temp.getScreenHeight());
		existing.setScreenWidth(temp.getScreenWidth());
		existing.setFingerprint(getFingerprint(temp, existing));		
		existing.setCookie(cookieValue);
		existing.setComplete(true);
		/* Save updated record */
		save(existing);
	}
	
	/**
	 * Build a visitor fingerprint:
	 * 
	 *       "0:0:0:0:0:0:0:1:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 
	 *       (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36"
	 *       
	 * @param Visitor
	 * @param existing
	 * @return String
	 */
	private String getFingerprint(Visitor temp, Visitor existing) {
		StringBuilder sb = new StringBuilder();
		sb.append(existing.getFingerprint() + ":");
		sb.append(temp.getScreenHeight() + ":");
		sb.append(temp.getScreenWidth() + ":");
		sb.append(temp.getScreenColor());
		return sb.toString();
	}

	public Visitor getExistingVisitorFromCookie(String cookie) {
		if (cookie == null || cookie.isEmpty()) return null;
		return (Visitor) fetchFirst("cookie = ?", cookie);
	}
	
}
