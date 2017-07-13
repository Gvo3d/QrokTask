package qroktask.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import qroktask.models.Reward;

public interface RewardRepository extends JpaRepository<Reward, Integer> {
}
