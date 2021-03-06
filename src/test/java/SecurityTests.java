import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import qroktask.dto.AuthForm;
import qroktask.security.SecurityService;

/**
 * Created by Gvozd on 13.07.2017.
 */
@ContextConfiguration(classes = {TestSecurityConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class SecurityTests extends AbstractTest{

    @Autowired
    SecurityService securityService;

    @Test
    public void authorize(){
        AuthForm form = new AuthForm();
        form.setUsername("${user.login}");
        form.setPassword("${user.password}");
        String result = securityService.autologin(form.getUsername(), form.getPassword());
        Assert.assertEquals("${user.login}", result);
    }
}
