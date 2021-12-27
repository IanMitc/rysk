package com.revature.rysk.services;

import com.revature.rysk.dto.GameLogDto;
import com.revature.rysk.entities.Game.Card;
import com.revature.rysk.entities.Game.Country;
import com.revature.rysk.entities.Game.Game;
import com.revature.rysk.entities.Player.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameService {

    Game newGame(List<Player> players);

    Game joinGame(Player player, long gameId);

    String quitGame(Player player, long gameId);

    //Might not be needed as the game should just stop once it gets to a point that a player needs to play
    //and they're not there, but it might be good just to notify the other players
    String exitGame(Player player, long gameId);

    List<GameLogDto> getFullLog(Player player, long gameId);

    //gets all log messages after specific message
    List<GameLogDto> tailLog(Player player, long gameId, int logId);

    // For when the player does not want to discard any cards
    int discard(Player player, long gameId);

    //For when the player wants to discard 3 cards
    int discard(Player player, long gameId, Card.TYPE cardType1, Card.TYPE cardType2, Card.TYPE cardType3);

    Country placeArmies(Player player, long gameId, int countryId, int numberOfArmies);

    List<Integer> attack(long gameId, Player player, int attackingCountryId, int defendingCountryId, int numberOfArmies, int numberOfDice);

    List<Integer> defend(Player player, long gameId, int numberOfDice);

    List<Country> move(Player player, long gameId, int fromCountryId, int toCountryId, int numberOfArmies);

    Card draw(Player player, long gameId);

    List<Game> getGames(Player player);

    //ends attack stage
    String attack(Player player, Long gameId);

    void move(Player player, long gameId);
}