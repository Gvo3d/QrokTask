package qroktask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import qroktask.models.Reward;
import qroktask.services.RewardService;

import java.util.List;

/**
 * Created by Gvozd on 12.07.2017.
 */
@RestController
@RequestMapping("/rewards/")
public class RewardController {

    @Autowired
    RewardService rewardService;

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<List<Reward>> getAllRewards(){
        List<Reward> rewardsList = (List<Reward>) rewardService.getAllRewards();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<List<Reward>>(rewardsList, status);
    }
}
