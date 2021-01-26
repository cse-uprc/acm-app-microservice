package com.acm.service.sql;

import static com.acm.library.globals.variables.Environments.TEST_ENV;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.acm.library.globals.exceptions.InvalidParamValueException;
import com.acm.service.activeprofile.ActiveProfile;
import com.google.common.collect.Sets;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SQLBuilderTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@InjectMocks
	private SQLBuilder sqlBuilder;

	@Mock
	private ActiveProfile activeProfile;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		when(activeProfile.getEnvironmentUrl()).thenReturn(TEST_ENV);
	}

	@Test
	public void testGetValidQueryString() throws IOException, InvalidParamValueException {
		Map<String, Set<?>> params = new HashMap<>();
		params.put("testId", Sets.newHashSet(1));
		params.put("userId", Sets.newHashSet(2));

		sqlBuilder.setParams(params);
		sqlBuilder.setQueryFile("testDAO");

		assertEquals("Validate Query", sqlBuilder.getSql("testQuery"),
				"SELECT *	FROM test	WHERE testId = '1'	AND userId = '2'");
	}

	@Test
	public void testNullParamsException() throws Exception {
		thrown.expect(NullPointerException.class);

		sqlBuilder.setQueryFile("testDAO");
		sqlBuilder.getSql("testQuery");
	}

	@Test
	public void testInvalidParamValueException() {
		thrown.expect(InvalidParamValueException.class);

		Map<String, Set<?>> params = new HashMap<>();
		params.put("testId", Sets.newHashSet(1));

		sqlBuilder.setParams(params);
		sqlBuilder.setQueryFile("testDAO");

		sqlBuilder.getSql("testQueryInvalidParams");
	}
}
