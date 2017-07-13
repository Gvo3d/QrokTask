package qroktask.configuration;

/**
 * Created by Gvozd on 31.12.2016.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import qroktask.components.CustomAuthProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.sessionManagement().sessionFixation().none();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);

        http.csrf().disable().httpBasic();

        http
                .authorizeRequests()
                .antMatchers("/auth").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/author/**").hasAuthority("ADMIN")
                .antMatchers("/**").hasAnyAuthority("ADMIN", "USER");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    AuthenticationProvider authenticationProvider(){
        return new CustomAuthProvider();
    }

}