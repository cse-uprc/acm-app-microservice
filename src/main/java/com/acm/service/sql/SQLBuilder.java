package com.acm.service.sql;

import static com.acm.service.globals.enums.ACMSqlTags.NAME;
import static com.acm.service.globals.enums.ACMSqlTags.VALUE_ID;
import static com.acm.service.globals.enums.QueryStatements.WHERE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acm.service.activeprofile.ActiveProfile;
import com.acm.service.exceptions.InvalidParamValueException;
import com.acm.service.exceptions.NullParamsException;
import com.acm.service.globals.enums.QueryStatements;

/**
 * Used for reading and processing sql blocks of code. This file can only be
 * used on acmsql extensions and located in the resources folder under dao.
 * 
 * @author Sam Butler
 * @since July 25, 2020
 */
@Component
public class SQLBuilder {
	private static String queryFile;

	private static Map<String, Set<?>> params;

	public String getQueryFile() {
		return queryFile;
	}

	public void setQueryFile(String file) {
		queryFile = file;
	}

	public Map<String, Set<?>> getParams() {
		return params;
	}

	public void setParams(Map<String, Set<?>> values) {
		params = values;
	}

	@Autowired
	private ActiveProfile activeProfile;

	private static boolean validNextLine = true;
	private static boolean existsWhereClause = false;
	private static int lineNumber = 0;

	/**
	 * Method called to get a sql block and returns a SqlClient object so that it
	 * can be executed and get the expected result from the query.
	 * 
	 * @param queryName - Name of the query that we want to execute
	 * @return a String
	 */
	public String getSql(String queryName) {
		String query = "";
		existsWhereClause = false;
		lineNumber = 0;
		validNextLine = true;

		try {
			query = getQueryString(queryName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return query;
	}

	/**
	 * Method to get the query string from a the set file based on the given name
	 * that we are looking for.
	 * 
	 * @param name - Name of the query we are trying to receive
	 * @return A String of the query with all the filled in values
	 * @throws IOException
	 * @throws InvalidParamValueException
	 * @throws NullParamsException
	 */
	public String getQueryString(String name) throws IOException, InvalidParamValueException, NullParamsException {
		String returnQuery = "";
		BufferedReader br = new BufferedReader(new FileReader(
				String.format("%s/resources/dao/%s.acmsql", activeProfile.getEnvironmentUrl(), queryFile)));

		String line;
		while ((line = br.readLine()) != null) {
			lineNumber++;
			if (line.contains(String.format("@NAME(%s)", name)))
				break;
		}

		while ((line = br.readLine()) != null && !line.contains(NAME.text())) {
			lineNumber++;
			returnQuery += parseQueryLine(line);
		}

		br.close();
		return returnQuery.replaceAll("\\s{2,}", " ").trim();
	}

	/**
	 * Inserts any property values that have been set
	 * 
	 * @param line - Line of the query being studied
	 * @return A String of the updated line
	 * @throws InvalidParamValueException
	 * @throws NullParamsException
	 */
	private String parseQueryLine(String line) throws InvalidParamValueException, NullParamsException {
		String queryLine = "";
		QueryStatements annotation = QueryStatements.getEnumFromString(line);

		if (annotation.isQueryStatement())
			queryLine = insertCondition(line, annotation);
		else if (VALUE_ID.in(line))
			queryLine = insertPropertyValue(line);
		else
			queryLine = line;

		return queryLine;
	}

	/**
	 * If there was a colon in the query line then it will update that line with
	 * it's associated property.
	 * 
	 * @param line - Line of the query being studied
	 * @return A String of the updated line with the new property value
	 * @throws InvalidParamValueException
	 */
	private String insertPropertyValue(String line) throws InvalidParamValueException {
		if (validNextLine) {
			return line.replace(line, replacePropertyValues(line));
		} else {
			validNextLine = true;
			return "";
		}
	}

	/**
	 * Adds the query condition if the value exists in the param's set, if it does
	 * not then the condition will be ignored.
	 * 
	 * @param line - line of the query being studied
	 * @return A String of the updated line with the new condition value
	 * @throws NullParamsException
	 */
	private String insertCondition(String line, QueryStatements type) throws NullParamsException {
		if (!doesContainKey(line)) {
			validNextLine = false;
			return "";
		} else {
			if (!existsWhereClause) {
				existsWhereClause = true;
				return line.replace(String.format("%s(:%s:)", type.annotation(), getReplaceValue(line)), WHERE.text());
			} else {
				return line.replace(String.format("%s(:%s:)", type.annotation(), getReplaceValue(line)), type.text());
			}
		}
	}

	/**
	 * Appends all the values in a comma separated string
	 * 
	 * @param paramKey - key of the values to get
	 * @return A string containing all the values in the set of objects
	 * @throws InvalidParamValueException
	 */
	private String parseParamValue(String paramKey) throws InvalidParamValueException {
		String returnValue = "";
		Set<?> paramValue = params.get(paramKey);

		if (paramValue == null)
			throw new InvalidParamValueException("Param value does not exist:", paramKey, getQueryFile(), lineNumber);

		for (Object value : paramValue) {
			returnValue += String.format("'%s',", value);
		}

		return returnValue.substring(0, returnValue.length() - 1);
	}

	/**
	 * Replace the line with all the correct values set in the param's
	 * 
	 * @param line - line of the query being studied
	 * @return string of the updated line
	 * @throws InvalidParamValueException
	 */
	public String replacePropertyValues(String line) throws InvalidParamValueException {
		List<Integer> colonIndexes = getValueIndexes(line, VALUE_ID.text());
		String newLine = line;

		for (int i = 0; i < colonIndexes.size(); i += 2) {
			String replaceValue = getReplaceValue(line, colonIndexes.get(i), colonIndexes.get(i + 1));
			String paramValue = parseParamValue(replaceValue);

			newLine = newLine.replace(String.format(":%s:", replaceValue), paramValue);
		}
		return newLine;
	}

	/**
	 * Get a list of all the locations of a colon, given a string and a character to
	 * search for
	 * 
	 * @param line      - line of the query being studied
	 * @param character - the character to get the indexes for
	 * @return List of integers
	 */
	public List<Integer> getValueIndexes(String line, String character) {
		List<Integer> indexes = new ArrayList<>();

		for (int index = line.indexOf(character); index >= 0; index = line.indexOf(character, index + 1)) {
			indexes.add(index);
		}

		return indexes;
	}

	/**
	 * Gets the value between the colons that we want to replace in the query
	 * 
	 * @param line - line of the query being studied
	 * @return string of the replace value
	 */
	public String getReplaceValue(String line) {
		int startColonIndex = line.indexOf(VALUE_ID.text()) + 1;
		int lastColonIndex = line.lastIndexOf(VALUE_ID.text());

		return line.substring(startColonIndex, lastColonIndex);
	}

	/**
	 * Gets the value between the colons that we want to replace in the query, given
	 * two indexes
	 * 
	 * @param line  - line of the query being studied
	 * @param start - start index of the first colon
	 * @param end   - end index of the second colon
	 * @return string of the replace value
	 */
	public String getReplaceValue(String line, int start, int end) {
		return line.substring(start + 1, end);
	}

	/**
	 * If the value we are wanting to replace between the colons exists in the
	 * param's then this method will return true, otherwise false.
	 * 
	 * @param line - line of the query being studied
	 * @return boolean if the key exists in the param's
	 * @throws NullParamsException
	 */
	public boolean doesContainKey(String line) throws NullParamsException {
		int startColonIndex = line.indexOf(VALUE_ID.text()) + 1;
		int lastColonIndex = line.lastIndexOf(VALUE_ID.text());

		try {
			return params.containsKey(line.substring(startColonIndex, lastColonIndex));
		} catch (Exception e) {
			throw new NullParamsException(line.substring(startColonIndex, lastColonIndex));
		}

	}
}
