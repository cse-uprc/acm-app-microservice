package com.acm.service.sql;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.acm.service.activeprofile.ActiveProfile;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

	/**
	 * Constructor method to intialize all the objects and datasource values
	 */
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

	/**
	 * Gets a a page of rows from the given query
	 * 
	 * @param <T>    - Class object to return as
	 * @param query  - Query to execute
	 * @param mapper - The mapper to manipulate the data as
	 * @return Generic object
	 */
	public <T> List<T> getPage(String query, RowMapper<T> mapper) {
		return jdbcTemplateObject.query(query, mapper);
	}

	/**
	 * Gets a single row from the given query
	 * 
	 * @param <T>    - Class object to return as
	 * @param query  - Query to execute
	 * @param mapper - The mapper to manipulate the data as
	 * @return Generic object
	 */
	public <T> T getTemplate(String query, RowMapper<T> mapper) {
		return jdbcTemplateObject.query(query, mapper).get(0);
	}

	/**
	 * Execute the delete query
	 * 
	 * @param query - Query to be executed
	 */
	public void delete(String query) {
		jdbcTemplateObject.execute(query);
	}

	/**
	 * Common post method to be used when doing inserts into the database
	 * 
	 * @param query - The insert query to be run
	 * @return Integer value of the auto_increment id if there is one
	 */
	public Optional<Integer> post(String query) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplateObject.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			return ps;
		}, keyHolder);
		try {
			return Optional.of(keyHolder.getKey().intValue());
		} catch (Exception e) {
			return Optional.of(-1);
		}

	}
}