import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import qroktask.components.CustomAuthProvider;
import qroktask.security.SecurityService;
import qroktask.security.SecurityServiceImpl;
import qroktask.security.UserDetailsServiceImpl;

/**
 * Created by Gvozd on 12.07.2017.
 */
@Configuration
public class TestSecurityConfig {

    @Bean
    SecurityService securityService(){
        return new SecurityServiceImpl();
    }

    @Bean
    UserDetailsService userDetailsService(){return new UserDetailsServiceImpl(); }

    @Bean
    AuthenticationProvider authenticationProvider(){
        CustomAuthProvider provider = new CustomAuthProvider();
        return provider;
    }
}
