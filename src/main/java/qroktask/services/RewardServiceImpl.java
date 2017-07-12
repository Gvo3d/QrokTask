package qroktask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qroktask.dao.RewardRepository;
import qroktask.models.Reward;

/**
 * Created by Gvozd on 12.07.2017.
 */
@Service
public class RewardServiceImpl implements RewardService {

    @Autowired
    RewardRepository rewardRepository;

    @Override
    public Iterable<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }
}
