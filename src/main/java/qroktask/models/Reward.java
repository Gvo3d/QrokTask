package qroktask.models;

import com.fasterxml.jackson.annotation.JsonView;
import qroktask.models.support.Validatable;
import qroktask.support.JacksonMappingMarker;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="Rewards")
public class Reward implements Validatable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Reward_id")
    @JsonView(JacksonMappingMarker.Lower.class)
    private int id;

    @Column(name = "Reward_year")
    @JsonView(JacksonMappingMarker.Lower.class)
    private int rewardYear;

    @Column(name = "Reward_title")
    @JsonView(JacksonMappingMarker.Lower.class)
    private String rewardTitle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRewardYear() {
        return rewardYear;
    }

    public void setRewardYear(int rewardYear) {
        this.rewardYear = rewardYear;
    }

    public String getRewardTitle() {
        return rewardTitle;
    }

    public void setRewardTitle(String rewardTitle) {
        this.rewardTitle = rewardTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reward reward = (Reward) o;

        if (getId() != reward.getId()) return false;
        if (getRewardYear() != reward.getRewardYear()) return false;
        return getRewardTitle() != null ? getRewardTitle().equals(reward.getRewardTitle()) : reward.getRewardTitle() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getRewardYear();
        result = 31 * result + (getRewardTitle() != null ? getRewardTitle().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Reward{" +
                "id=" + id +
                ", rewardYear=" + rewardYear +
                ", rewardTitle='" + rewardTitle + '\'' +
                '}';
    }

    @Override
    public boolean validate() {
        return (null!=rewardTitle && rewardYear!=0);
    }
}
