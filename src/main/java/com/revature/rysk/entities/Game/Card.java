package com.revature.rysk.entities.Game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_id_generator")
    @SequenceGenerator(name = "card_id_generator", sequenceName = "card_id_sequence")
    @JsonIgnore
    Long cardId;

    @Enumerated
    private TYPE type;

    public enum TYPE {
        Horseman,
        Cannon,
        FootSoldier,
        Joker
    }
}