package com.revature.rysk.entities.Game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.rysk.entities.Player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hand {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hand_id_generator")
    @SequenceGenerator(name = "hand_id_generator", sequenceName = "hand_id_sequence")
    @JsonIgnore
    private long handId;

    @Embedded
    private List<Card> cards;

    @OneToOne
    @JoinColumn(name = "held_by_player_id")
    private Player heldBy;
}
