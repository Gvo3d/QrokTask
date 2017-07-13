package qroktask.security;

import qroktask.dto.AuthForm;

public interface SecurityService {
    String authorize(AuthForm authForm);
}
