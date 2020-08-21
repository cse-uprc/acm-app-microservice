package com.acm.service.sql;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import com.acm.service.activeprofile.ActiveProfile;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

/**
 * Common SQL Service to make API calls to the database
 * 
 * @author Josue Van Dyke
 * @since 8/01/2020
 */
@Service
public class SqlClient {

	private static JdbcTemplate jdbcTemplateObject;
	private static final DriverManagerDataSource source = new DriverManagerDataSource();
	private static final ActiveProfile profile = new ActiveProfile();

	Properties prop = new Properties();

	public SqlClient() {

		try (InputStream input = new FileInputStream(profile.getPropertyFilePath())) {
			prop.load(input);
		} catch (IOException io) {
			io.printStackTrace();
		}

		source.setDriverClassName(prop.getProperty("spring.datasource.driver-class-name"));
		source.setUrl(prop.getProperty("spring.datasource.url"));
		source.setUsername(prop.getProperty("spring.datasource.username"));
		source.setPassword(prop.getProperty("spring.datasource.password"));
		jdbcTemplateObject = new JdbcTemplate(source);
	}

	public static <T> List<T> getPage(String query, RowMapper<T> mapper) {
		return jdbcTemplateObject.query(query, mapper);
	}

	public static <T> T getTemplate(String query, RowMapper<T> mapper) {
		return jdbcTemplateObject.query(query, mapper).get(0);
	}

	public static void post(String query) {
		jdbcTemplateObject.execute(query);
	}
}