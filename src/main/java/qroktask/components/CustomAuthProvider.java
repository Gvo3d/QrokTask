package qroktask.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import qroktask.security.AuthorizedUser;
import qroktask.security.Role;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

    @Value("${admin.login}")
    private String adminLogin;
    @Value("${admin.password}")
    private String adminPass;
    @Value("${user.login}")
    private String userLogin;
    @Value("${user.password}")
    private String userPass;

    private PasswordEncoder passwordEncoder;

    private Set<AuthorizedUser> users;

    public CustomAuthProvider(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.users = new HashSet<>();

    }

    @PostConstruct
    public void makeUsers() {
        AuthorizedUser user = new AuthorizedUser(userLogin, passwordEncoder.encode(userPass), Role.USER);
        AuthorizedUser admin = new AuthorizedUser(adminLogin, passwordEncoder.encode(adminPass), Role.ADMIN);
        users.add(user);
        users.add(admin);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getDetails();
        AuthorizedUser user = new AuthorizedUser(username, password);
        for (AuthorizedUser userToAuthorize : users) {
            if (userToAuthorize.equals(user)) {
                GrantedAuthority authority = new SimpleGrantedAuthority(userToAuthorize.getRole().toString());
                List<GrantedAuthority> authorityList = new ArrayList<>();
                authorityList.add(authority);
                return new UsernamePasswordAuthenticationToken(username, password, authorityList);
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}