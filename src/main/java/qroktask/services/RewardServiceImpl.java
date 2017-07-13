package qroktask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qroktask.dao.RewardRepository;
import qroktask.models.Reward;

@Service
public class RewardServiceImpl implements RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    @Override
    public Iterable<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    @Override
    public Reward getOneReward(Integer id) {
        return rewardRepository.findOne(id);
    }

    @Override
    public Reward createOrUpdateReward(Reward reward) {
        if (null!= reward) {
            return rewardRepository.save(reward);
        } else return null;
    }

    @Override
    public Boolean exists(Integer id) {
        if (null != id) {
            return rewardRepository.exists(id);
        } else return false;
    }

    @Override
    public Boolean delete(Integer id) {
        if (null != id) {
            if (rewardRepository.exists(id)) {
                rewardRepository.delete(id);
                return true;
            }
        }
        return false;
    }
}
