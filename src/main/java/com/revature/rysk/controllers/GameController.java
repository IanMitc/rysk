package com.revature.rysk.controllers;

import com.revature.rysk.entities.Game.Card;
import com.revature.rysk.entities.Game.Country;
import com.revature.rysk.entities.Game.Game;
import com.revature.rysk.entities.Game.GameLog;
import com.revature.rysk.entities.Player.Player;
import com.revature.rysk.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {
    @Autowired
    GameService gameService;

    @PostMapping("/game/new")
    public Game newGame(@RequestBody() List<Player> players) {
        return gameService.newGame(players);
    }

    @PostMapping("/game/{gameId}/join")
    public Game joinGame(@RequestBody Player player, @PathVariable("gameId") long gameId) {
        return gameService.joinGame(player, gameId);
    }

    @PostMapping("/game/{gameId}/quit")
    public String quitGame(@RequestBody Player player, @PathVariable("gameId") long gameId) {
        return gameService.quitGame(player, gameId);
    }

    @PostMapping("/game/{gameId}/exit")
    public String exitGame(@RequestBody Player player, @PathVariable("gameId") long gameId) {
        return gameService.exitGame(player, gameId);
    }

    @PostMapping("/game/{gameId}/log")
    public List<GameLog> getFullLog(@RequestBody Player player, @PathVariable("gameId") long gameId) {
        return gameService.getFullLog(player, gameId);
    }

    @PostMapping("/game/{gameId}/log/{logId}")
    public List<GameLog> tailLog(@RequestBody Player player,
                                 @PathVariable("gameId") long gameId,
                                 @PathVariable("logId") int logId) {
        return gameService.tailLog(player, gameId, logId);
    }

    // For when the player does not want to discard any cards
    @PostMapping("/game/{gameId}/play/discard")
    public int discard(@RequestBody Player player, @PathVariable("gameId") long gameId) {
        return gameService.discard(player, gameId);
    }

    //For when the player wants to discard 3 cards
    @PostMapping("/game/play/{gameId}/discard/{cardType1}/{cardType2}/{cardType3}")
    public int discard(@RequestBody Player player,
                       @PathVariable("gameId") long gameId,
                       @PathVariable("carType1") Card.TYPE cardType1,
                       @PathVariable("carType2") Card.TYPE cardType2,
                       @PathVariable("carType3") Card.TYPE cardType3) {
        return gameService.discard(player, gameId, cardType1, cardType2, cardType3);
    }

    @PostMapping("/game/{gameId}/play/armies/{countryId}/{numberOfArmies}")
    public Country placeArmies(@RequestBody Player player,
                               @PathVariable("gameId") long gameId,
                               @PathVariable("countryId") int countryId,
                               @PathVariable("numberOfArmies") int numberOfArmies) {
        return gameService.placeArmies(player, gameId, countryId, numberOfArmies);
    }

    @PostMapping("/game/{gameId}/play/attack/{attackingCountryId}/{defendingCountryId}/{numberOfArmies}/{numberOfDice}")
    public List<Integer> attack(@RequestBody Player player,
                                @PathVariable("gameId") long gameId,
                                @PathVariable("attackingCountryId") int attackingCountryId,
                                @PathVariable("defendingCountryId") int defendingCountryId,
                                @PathVariable("numberOfArmies") int numberOfArmies,
                                @PathVariable("numberOfDice") int numberOfDice) {
        return gameService.attack(gameId, player, attackingCountryId, defendingCountryId, numberOfArmies, numberOfDice);
    }
}
