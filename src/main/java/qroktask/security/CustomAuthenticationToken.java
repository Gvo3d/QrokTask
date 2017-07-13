package qroktask.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomAuthenticationToken implements Authentication {

    private String login;
    private String password;
    private Role role;
    private boolean authenticated;

    CustomAuthenticationToken(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
        list.add(authority);
        return list;
    }

    @Override
    public Object getCredentials() {
        return login;
    }

    @Override
    public Object getDetails() {
        return password;
    }

    @Override
    public Object getPrincipal() {
        return login;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.authenticated = b;
    }

    @Override
    public String getName() {
        return login;
    }

    @Override
    public String toString() {
        return "CustomAuthenticationToken{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", authenticated=" + authenticated +
                '}';
    }
}
