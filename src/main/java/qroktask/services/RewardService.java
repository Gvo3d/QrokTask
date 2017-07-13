package qroktask.services;

import qroktask.models.Reward;

/**
 * Created by Gvozd on 12.07.2017.
 */
public interface RewardService {
    public Iterable<Reward> getAllRewards();
    public Reward getOneReward(Integer id);
}
