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
import qroktask.models.Book;
import qroktask.services.BooksService;

import java.util.List;

/**
 * Created by Gvozd on 12.07.2017.
 */
@RestController
@RequestMapping("/books/")
public class BooksController {

    @Autowired
    BooksService booksService;

    @JsonView(JacksonMappingMarker.List.class)
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> booksList = (List<Book>) booksService.getAllBooks();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<List<Book>>(booksList, status);
    }
}
