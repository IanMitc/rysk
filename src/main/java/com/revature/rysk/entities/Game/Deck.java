package com.revature.rysk.entities.Game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deck_id_generator")
    @SequenceGenerator(name = "deck_id_generator", sequenceName = "deck_id_sequence")
    private long deckId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards;

    public void newDeck() {
        this.cards = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            this.cards.add(Card.builder().type(Card.TYPE.Cannon).build());
            this.cards.add(Card.builder().type(Card.TYPE.FootSoldier).build());
            this.cards.add(Card.builder().type(Card.TYPE.Horseman).build());
        }
        for (int i = 0; i < 2; i++) {
            this.cards.add(Card.builder().type(Card.TYPE.Joker).build());
        }
        Collections.shuffle(this.cards);
    }

    public Card draw() {
        Card card = this.cards.get(0);
        this.cards.remove(card);
        return card;
    }

    public void discard(List<Card> discarded) {
        cards.addAll(discarded);
        Collections.shuffle(this.cards);
    }
}