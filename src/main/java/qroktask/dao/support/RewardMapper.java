package qroktask.dao.support;

import qroktask.models.Reward;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Gvozd on 12.07.2017.
 */
public class RewardMapper implements RowMapper<Reward> {
    @Override
    public Reward mapRow(ResultSet resultSet, int i) throws SQLException {
        Reward reward = new Reward();
        reward.setId(resultSet.getInt("reward_id"));
        reward.setRewardTitle(resultSet.getString("reward_title"));
        reward.setRewardYear(resultSet.getInt("reward_year"));
        return reward;
    }
}
