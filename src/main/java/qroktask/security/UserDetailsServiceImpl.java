package qroktask.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Value("${admin.login}")
    private String adminLogin;
    @Value("${admin.password}")
    private String adminPass;
    @Value("${user.login}")
    private String userLogin;
    @Value("${user.password}")
    private String userPass;

    private Set<AuthorizedUser> users = new HashSet<>();

    @PostConstruct
    public void makeUsers() {
        AuthorizedUser user = new AuthorizedUser(userLogin, userPass, Role.USER);
        AuthorizedUser admin = new AuthorizedUser(adminLogin, adminPass, Role.ADMIN);
        users.add(user);
        users.add(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String s) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        AuthorizedUser credentials =null;
        for (AuthorizedUser user:users){
            if (user.getLogin().equals(s)){
                credentials = user;
            }
        }
        if (null==credentials || null==credentials.getRole()) {
            return null;
        }
            grantedAuthorities.add(new SimpleGrantedAuthority(credentials.getRole().name()));
        return new org.springframework.security.core.userdetails.User(credentials.getLogin(), credentials.getPassword(), grantedAuthorities);
    }
}
