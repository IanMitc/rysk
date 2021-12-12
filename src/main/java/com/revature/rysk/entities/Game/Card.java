package com.revature.rysk.entities.Game;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Entity
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private TYPE type;

    public enum TYPE {
        Horseman,
        Cannon,
        FootSoldier,
        Joker
    }
}