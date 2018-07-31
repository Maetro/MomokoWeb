package com.momoko.es;

import com.momoko.es.commons.util.LecUtils;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql({"/test-data/initialize.sql", "/test-data/finalize.sql", })
public class BasicMvcTests extends AbstractMvcTests {
	
	@Test
	public void testPing() throws Exception {
		
		mvc.perform(get("/api/core/ping"))
				.andExpect(status().is(204));
	}
	
	@Test
	public void testGetContextLoggedIn() throws Exception {
		
		mvc.perform(get("/api/core/context")
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, tokens.get(ADMIN_ID)))
				.andExpect(status().is(200))
				.andExpect(header().string(LecUtils.TOKEN_RESPONSE_HEADER_NAME, containsString(".")))
				.andExpect(MockMvcResultMatchers.jsonPath("user").value("ADMIN"));
				//.andExpect(jsonPath("$.context.reCaptchaSiteKey").isString())
//				.andExpect(jsonPath("$.user.id").value(ADMIN_ID))
//				.andExpect(jsonPath("$.user.roles[0]").value("ADMIN"));
	}
	
	@Test
	public void testGetContextWithoutLoggedIn() throws Exception {
		
		mvc.perform(get("/api/core/context"))
				.andExpect(status().is(200))
				.andExpect(header().doesNotExist(LecUtils.TOKEN_RESPONSE_HEADER_NAME))
				.andExpect(jsonPath("$.context.reCaptchaSiteKey").isString())
				.andExpect(jsonPath("$.user").doesNotExist());
	}	
}
