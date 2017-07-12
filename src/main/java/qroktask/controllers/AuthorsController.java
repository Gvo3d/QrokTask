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
import qroktask.models.Author;
import qroktask.services.AuthorsService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gvozd on 12.07.2017.
 */
@RestController(value = "/authors/")
public class AuthorsController {

    @Autowired
    AuthorsService authorsService;

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<List<Resource<Author>>> getAllAuthors(){
        List<Author> rewardsList = (List<Author>) authorsService.getAllAuthors();
        List<Resource<Author>> resourceList = new ArrayList<>();
        for (Author reward: rewardsList){
            Resource resource = new Resource(reward);
            resource.add(Link.valueOf("templink"));
            resourceList.add(resource);
        }
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<List<Resource<Author>>>(resourceList, status);
    }
}
