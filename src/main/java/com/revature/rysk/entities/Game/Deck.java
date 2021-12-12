package com.revature.rysk.entities.Game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Deck {
    @Id
    @GeneratedValue
    private long deckId;

    @Embedded
    private Stack<Card> cards;

    public void newDeck() {
        for (int i = 0; i < 14; i++) {
            this.cards.add(new Card(Card.TYPE.Cannon));
            this.cards.add(new Card(Card.TYPE.FootSoldier));
            this.cards.add(new Card(Card.TYPE.Horseman));
        }
        for (int i = 0; i < 2; i++) {
            this.cards.add(new Card(Card.TYPE.Joker));
        }
        Collections.shuffle(this.cards);
    }

    public Card draw() {
        return this.cards.pop();
    }

    public void discard(List<Card> discarded) {
        cards.addAll(discarded);
        Collections.shuffle(this.cards);
    }
}