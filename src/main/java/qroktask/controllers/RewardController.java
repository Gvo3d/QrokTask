package qroktask.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qroktask.models.Reward;
import qroktask.services.RewardService;
import qroktask.support.JacksonMappingMarker;

import java.util.List;

/**
 * Created by Gvozd on 12.07.2017.
 */
@RestController
@RequestMapping("/rewards")
public class RewardController {

    @Autowired
    RewardService rewardsService;

    @JsonView(JacksonMappingMarker.Lower.class)
    @RequestMapping(value="/model",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Reward getModel(){
        return new Reward();
    }

    @JsonView(JacksonMappingMarker.Lower.class)
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<List<Reward>> getAllRewards(){
        List<Reward> rewardsList = (List<Reward>) rewardsService.getAllRewards();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<List<Reward>>(rewardsList, status);
    }

    @JsonView(JacksonMappingMarker.Lower.class)
    @RequestMapping(value="/{id}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Reward> getOneReward(@PathVariable("id") Integer id){
        Reward reward = rewardsService.getOneReward(id);
        HttpStatus status;
        if (null!=reward){
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Reward>(reward, status);
    }

    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Integer> createReward(@RequestBody Reward reward){
        HttpStatus status;
        Integer result;
        if (reward.validate()){
            status = HttpStatus.OK;
            result = rewardsService.create(reward).getId();
        } else {
            status = HttpStatus.BAD_REQUEST;
            result = 0;
        }
        return new ResponseEntity<Integer>(result, status);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Reward> updateReward(@PathVariable("id") Integer id, @RequestBody Reward reward){
        HttpStatus status;
        Reward result;
        if (reward.validate()){
            reward.setId(id);
            status = HttpStatus.OK;
            result = rewardsService.update(reward);
        } else {
            status = HttpStatus.BAD_REQUEST;
            result = null;
        }
        return new ResponseEntity<Reward>(result, status);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Boolean> deleteReward(@PathVariable("id") Integer id){
        HttpStatus status;
        Boolean result = rewardsService.delete(id);
        if (result){
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Boolean>(result, status);
    }
}
