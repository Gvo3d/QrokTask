import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import qroktask.dto.AuthForm;

import static org.apache.log4j.MDC.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@AutoConfigureMockMvc
@ContextConfiguration(classes = {TestDaoConfig.class, TestSecurityConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class ServletTests extends AbstractTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private WebApplicationContext applicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(
                applicationContext).build();
    }

    @Test
    public void authorize() throws Exception {
        AuthForm authForm = new AuthForm();
        authForm.setUsername("user");
        authForm.setPassword("qwerty");
        mockMvc.perform(get("/auth/").andExpect(status().isOk()).andExpect(jsonPath("$",Matchers.hasSize(1)));
    }
}