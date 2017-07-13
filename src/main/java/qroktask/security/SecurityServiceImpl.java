package qroktask.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import qroktask.dto.AuthForm;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    public String authorize(AuthForm authForm) {
        Authentication authentication = new CustomAuthenticationToken(authForm.getUsername(), authForm.getPassword());
        Authentication result = authenticationProvider.authenticate(authentication);
        if (null!=result) {
            return (String) result.getPrincipal();
        } else return null;
    }
}
