package com.revature.rysk.entities.Game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.rysk.entities.Player.Player;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Hand {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hand_id_generator")
    @SequenceGenerator(name = "hand_id_generator", sequenceName = "hand_id_sequence")
    private long handId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards;

    @OneToOne
    @JoinColumn(name = "held_by_player_id")
    @JsonIgnore
    private Player heldBy;
}
