package qroktask.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import qroktask.dao.JacksonMappingMarker;
import qroktask.models.Author;
import qroktask.services.AuthorsService;

import java.util.List;

/**
 * Created by Gvozd on 12.07.2017.
 */
@RestController
@RequestMapping("/authors/")
public class AuthorsController {

    @Autowired
    AuthorsService authorsService;

    @JsonView(JacksonMappingMarker.List.class)
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<List<Author>> getAllAuthors(){
        List<Author> rewardsList = (List<Author>) authorsService.getAllAuthors();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<List<Author>>(rewardsList, status);
    }
}
