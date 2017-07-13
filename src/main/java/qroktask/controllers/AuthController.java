package qroktask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import qroktask.dto.AuthForm;
import qroktask.dto.StringDTO;
import qroktask.security.SecurityService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth/")
public class AuthController {
    private static final String USERSESSIONPARAMETER = "User";

    @Autowired
    SecurityService securityService;

    @RequestMapping(value="/model",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public AuthForm getModel(){
        return new AuthForm();
    }

    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<StringDTO> authorize(@RequestBody AuthForm authForm, HttpServletRequest request){
        HttpStatus status;
        StringDTO result;
        if (authForm.validate()){
            status = HttpStatus.OK;
            result = new StringDTO(securityService.autologin(authForm.getUsername(), authForm.getPassword()));
            request.getSession().setAttribute(USERSESSIONPARAMETER,result.getResponse());
        } else {
            result = null;
            request.getSession().removeAttribute(USERSESSIONPARAMETER);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<StringDTO>(result, status);
    }

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Boolean> isAuthorized(HttpServletRequest request){
        String username = (String) request.getSession().getAttribute(USERSESSIONPARAMETER);
        HttpStatus status;
        if (null!=username){
            status = HttpStatus.OK;
            return new ResponseEntity<Boolean>(true, status);
        }
        status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<Boolean>(false, status);
    }


}
