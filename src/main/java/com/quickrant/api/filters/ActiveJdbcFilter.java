package com.quickrant.api.filters;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

import com.quickrant.api.database.Database;

public class ActiveJdbcFilter implements Filter {

	private static Logger log = Logger.getLogger(ActiveJdbcFilter.class);

	@Override
	public void init(FilterConfig config) throws ServletException {
		log.info("Filter initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			Database.open();
			chain.doFilter(request, response);
		} catch (SQLException e) {
			log.error("Unable to execute transaction", e);
		} catch (Exception e) {
			log.error("Error during request", e);
		} finally {
			Database.close();
		}
	}
	
	@Override
	public void destroy() {
		log.info("Filter destroyed");		
	}
}