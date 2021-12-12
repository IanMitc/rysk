package com.revature.rysk.entities.Game;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

@Entity
public class Deck {
    @Id
    private long deckId;

    @Embedded
    private Stack<Card> cards;

    public Deck() {
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

    public void discard(List<Card> discarded){
        cards.addAll(discarded);
        Collections.shuffle(this.cards);
    }
}