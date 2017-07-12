package qroktask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import qroktask.models.Book;
import qroktask.services.BooksService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gvozd on 12.07.2017.
 */
@RestController(value = "/books/")
public class BooksController {

    @Autowired
    BooksService booksService;

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<List<Resource<Book>>> getAllBooks(){
        List<Book> rewardsList = (List<Book>) booksService.getAllBooks();
        List<Resource<Book>> resourceList = new ArrayList<>();
        for (Book reward: rewardsList){
            Resource resource = new Resource(reward);
            resource.add(Link.valueOf("templink"));
            resourceList.add(resource);
        }
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<List<Resource<Book>>>(resourceList, status);
    }
}
