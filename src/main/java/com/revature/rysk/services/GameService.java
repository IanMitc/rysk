package com.revature.rysk.services;

import com.revature.rysk.entities.Game.Card;
import com.revature.rysk.entities.Game.Country;
import com.revature.rysk.entities.Game.Game;
import com.revature.rysk.entities.Player.Player;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface GameService {

    Game newGame(List<Player> players);

    String declineGame(Player player, long gameId);

    Game joinGame(Player player, long gameId);

    Game quitGame(Player player, long gameId);

    //Might not be needed as the game should just stop once it gets to a point that a player needs to play
    //and they're not there, but it might be good just to notify the other players
    String exitGame(Player player, long gameId);

    List<String> getFullLog(Player player, long gameId);

    //gets all log messages after specific message
    List<String> tailLog(Player player, long gameId, long logId);

    int discard(List<Card> cards);

    Country addArmies(Player player, long gameId, long countryId, int armies);

    List<Integer> attack(Player player, long attackingCountryId, int attackingArmies, int numberOfDice, long defendingCountryId);

    List<Integer> defend(Player player, long defendingCountryId, int numberOfDice);

    Country move(Player player, long fromCountryId, long toCountryId, int numberOfArmies);

    List<Card> draw(Player player);
}
