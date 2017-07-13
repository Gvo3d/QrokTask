package qroktask.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qroktask.models.Book;
import qroktask.services.BooksService;
import qroktask.support.JacksonMappingMarker;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Gvozd on 12.07.2017.
 */
@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    BooksService booksService;

    @JsonView(JacksonMappingMarker.Middle.class)
    @RequestMapping(value="/model",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Book getModel(){
        return new Book();
    }

    @JsonView(JacksonMappingMarker.Middle.class)
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> rewardsList = (List<Book>) booksService.getAllBooks();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<List<Book>>(rewardsList, status);
    }

    @JsonView(JacksonMappingMarker.Middle.class)
    @RequestMapping(value="/{id}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Book> getOneBook(@PathVariable("id") Integer id){
        Book book = booksService.getOneBook(id);
        HttpStatus status;
        if (null!=book){
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Book>(book, status);
    }

    @JsonView(JacksonMappingMarker.Higher.class)
    @RequestMapping(value="/full/{id}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Book> getFullBook(@PathVariable("id") Integer id){
        Book book = booksService.getOneBookFetchAll(id);
        HttpStatus status;
        if (null!=book){
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Book>(book, status);
    }

    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Integer> createBook(@RequestBody Book book){
        HttpStatus status;
        Integer result;
        if (book.validate()){
            status = HttpStatus.OK;
            result = booksService.create(book).getId();
        } else {
            status = HttpStatus.BAD_REQUEST;
            result = 0;
        }
        return new ResponseEntity<Integer>(result, status);
    }

    @JsonView(JacksonMappingMarker.Middle.class)
    @RequestMapping(value="/{id}",method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Book> updateBook(@PathVariable("id") Integer id, @RequestBody Book book, HttpServletRequest request){
        HttpStatus status;
        Book result;
        if (book.validate()){
            book.setId(id);
            status = HttpStatus.OK;
            result = booksService.update(book);
        } else {
            status = HttpStatus.BAD_REQUEST;
            result = null;
        }
        return new ResponseEntity<Book>(result, status);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Boolean> deleteBook(@PathVariable("id") Integer id){
        HttpStatus status;
        Boolean result = booksService.delete(id);
        if (result){
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Boolean>(result, status);
    }
}
