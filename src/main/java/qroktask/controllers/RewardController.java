package qroktask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qroktask.models.Reward;
import qroktask.services.RewardService;

/**
 * Created by Gvozd on 12.07.2017.
 */
@RestController
public class RewardController {

    @Autowired
    RewardService rewardService;

    @RequestMapping(value = "/")
    public String getAllRewards(){
        System.out.println("In rewardController");
        StringBuilder builder = new StringBuilder();
        Iterable<Reward> list = rewardService.getAllRewards();
        System.out.println("list="+list);
        for (Reward reward:list){
            System.out.println(reward);
            builder.append(reward);
            builder.append("\n");
        }
        System.out.println(builder.toString());
        return builder.toString();
    }
}
