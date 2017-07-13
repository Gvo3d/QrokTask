package qroktask.security;

import qroktask.dto.AuthForm;

public interface SecurityService {
    boolean authorize(AuthForm authForm);
}
