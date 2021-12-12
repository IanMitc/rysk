package com.revature.rysk.entities.Game;

import com.revature.rysk.entities.Player.Player;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class Game {
    @Id
    private long gameId;
    private Set<Player> players;
    private Set<Continent> continents;

}
