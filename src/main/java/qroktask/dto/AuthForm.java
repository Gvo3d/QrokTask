package qroktask.dto;

import lombok.Data;

@Data
public class AuthForm {
    private String username;
    private String password;

    public boolean validate(){
        return (null!=username && null!=password);
    }
}
