package qroktask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import qroktask.dto.AuthorDTO;
import qroktask.models.Author;
import qroktask.services.AuthorsService;

@RestController
public class CustomEndpoint {

    @Autowired
    AuthorsService authorsService;

    @RequestMapping(value = "/author/info/short/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<AuthorDTO> custom(@PathVariable("id") Integer id){
        HttpStatus status;
        AuthorDTO result;
        Author author = authorsService.getOneAuthorFetchAll(id);
        result = new AuthorDTO(author);
        if (result.validate()){
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<AuthorDTO>(result,status);
    }
}
