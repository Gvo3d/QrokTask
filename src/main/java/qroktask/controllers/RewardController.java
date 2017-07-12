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
import qroktask.models.Reward;
import qroktask.services.RewardService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gvozd on 12.07.2017.
 */
@RestController(value = "/rewards/")
public class RewardController {

    @Autowired
    RewardService rewardService;

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<List<Resource<Reward>>> getAllRewards(){
        List<Reward> rewardsList = (List<Reward>) rewardService.getAllRewards();
        List<Resource<Reward>> resourceList = new ArrayList<>();
        for (Reward reward: rewardsList){
            Resource resource = new Resource(reward);
            resource.add(Link.valueOf("templink"));
            resourceList.add(resource);
        }
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<List<Resource<Reward>>>(resourceList, status);
    }
}