package com.revature.rysk.entities.Game;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Card {
    @Enumerated
    private TYPE type;

    public enum TYPE {
        Horseman,
        Cannon,
        FootSoldier,
        Joker
    }
}