package com.momoko.es;

import com.momoko.es.commons.mail.MailSender;
import com.momoko.es.commons.util.LecUtils;
import com.momoko.es.jpa.model.repository.UsuarioRepository;
import com.momoko.es.jpa.security.MomokoSecurityConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest({
	"logging.level.com.momoko.es=INFO", // logging.level.root=ERROR does not work: https://stackoverflow.com/questions/49048298/springboottest-not-overriding-logging-level
	"logging.level.org.springframework=INFO",
	"momoko.recaptcha.sitekey="
})
@AutoConfigureMockMvc(secure=false)
@ComponentScan(basePackages = "com.momoko.es.jpa")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQL)
@Sql({"/test-data/initialize.sql", "/test-data/finalize.sql"})
public abstract class AbstractMvcTests {
	
	protected static final Integer ADMIN_ID = 101;
	protected static final Integer UNVERIFIED_ADMIN_ID = 102;
	protected static final Integer BLOCKED_ADMIN_ID = 103;
	
	protected static final Integer USER_ID = 104;
	protected static final Integer UNVERIFIED_USER_ID = 105;
	protected static final Integer BLOCKED_USER_ID = 106;
	
	protected static final String ADMIN_EMAIL = "admin@example.com";
	protected static final String ADMIN_PASSWORD = "admin!";
	
	protected static final String USER_PASSWORD = "Sanjay99!";
	protected static final String UNVERIFIED_USER_EMAIL = "unverifieduser@example.com";
	
	protected Map<Integer, String> tokens = new HashMap<>(6);
	
    @Autowired
    protected MockMvc mvc;
    
    @Autowired
    protected UsuarioRepository usuarioRepository;
    
    @SpyBean
    protected MailSender<?> mailSender;
    
    protected String login(String userName, String password) throws Exception {

        MvcResult result = mvc.perform(post("/api/core/login")
                .param("username", userName)
                .param("password", password)
                .header("contentType",  MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is(200))
                .andReturn();

        return result.getResponse().getHeader(LecUtils.TOKEN_RESPONSE_HEADER_NAME);
    }
    
    @Before
    public void baseSetUp() throws Exception {
    	
		tokens.put(ADMIN_ID, login(ADMIN_EMAIL, ADMIN_PASSWORD));
		tokens.put(UNVERIFIED_ADMIN_ID, login("unverifiedadmin@example.com", ADMIN_PASSWORD));
		tokens.put(BLOCKED_ADMIN_ID, login("blockedadmin@example.com", ADMIN_PASSWORD));
		tokens.put(USER_ID, login("user@example.com", USER_PASSWORD));
		tokens.put(UNVERIFIED_USER_ID, login(UNVERIFIED_USER_EMAIL, USER_PASSWORD));
		tokens.put(BLOCKED_USER_ID, login("blockeduser@example.com", USER_PASSWORD));
    }
    
	
	protected void ensureTokenWorks(String token) throws Exception {

		mvc.perform(get("/api/core/context")
				.header(LecUtils.TOKEN_REQUEST_HEADER_NAME, token))
				.andExpect(status().is(200))
				.andExpect(jsonPath("$.user.id").value(UNVERIFIED_USER_ID));
	}
}
