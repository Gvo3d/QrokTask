package qroktask.services;

import qroktask.models.Reward;

/**
 * Created by Gvozd on 12.07.2017.
 */
public interface RewardService {
    Iterable<Reward> getAllRewards();
    Reward getOneReward(Integer id);
    Reward createOrUpdateReward(Reward reward);
    Boolean exists(Integer id);
    Boolean delete(Integer id);
}
