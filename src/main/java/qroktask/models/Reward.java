package qroktask.models;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name="Rewards")
public class Reward {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Reward_id")
    private int id;

    @Column(name = "Reward_year")
    private int rewardYear;

    @Column(name = "Reward_title")
    private String rewardTitle;
}
