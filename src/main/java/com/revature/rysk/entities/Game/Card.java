package com.revature.rysk.entities.Game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {
    private TYPE type;

    public enum TYPE {
        Horseman,
        Cannon,
        FootSoldier,
        Joker
    }
}