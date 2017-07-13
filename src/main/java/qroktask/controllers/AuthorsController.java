package qroktask.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qroktask.models.Author;
import qroktask.services.AuthorsService;
import qroktask.support.JacksonMappingMarker;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Gvozd on 12.07.2017.
 */
@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    AuthorsService authorsService;

    @JsonView(JacksonMappingMarker.List.class)
    @RequestMapping(value="/model",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Author getModel(){
        return new Author();
    }

    @JsonView(JacksonMappingMarker.List.class)
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<List<Author>> getAllAuthors(){
        List<Author> rewardsList = (List<Author>) authorsService.getAllAuthors();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<List<Author>>(rewardsList, status);
    }

    @JsonView(JacksonMappingMarker.List.class)
    @RequestMapping(value="/{id}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Author> getOneAuthor(@PathVariable("id") Integer id){
        Author author = authorsService.getOneAuthor(id);
        HttpStatus status;
        if (null!=author){
             status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Author>(author, status);
    }

    @JsonView(JacksonMappingMarker.Data.class)
    @RequestMapping(value="/full/{id}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Author> getFullAuthor(@PathVariable("id") Integer id){
        Author book = authorsService.getOneAuthorFetchAll(id);
        HttpStatus status;
        if (null!=book){
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Author>(book, status);
    }

    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Integer> createAuthor(@RequestBody Author author, HttpServletRequest request){
        HttpStatus status;
        Integer result;
        if (author.validate()){
        status = HttpStatus.OK;
        result = authorsService.create(author).getId();
        } else {
            status = HttpStatus.BAD_REQUEST;
            result = 0;
        }
        return new ResponseEntity<Integer>(result, status);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Author> updateAuthor(@PathVariable("id") Integer id, @RequestBody Author author, HttpServletRequest request){
        HttpStatus status;
        Author result;
        if (author.validate()){
            author.setId(id);
            status = HttpStatus.OK;
            result = authorsService.update(author);
        } else {
            status = HttpStatus.BAD_REQUEST;
            result = null;
        }
        return new ResponseEntity<Author>(result, status);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Boolean> deleteAuthor(@PathVariable("id") Integer id){
        HttpStatus status;
        Boolean result = authorsService.delete(id);
        if (result){
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Boolean>(result, status);
    }
}
