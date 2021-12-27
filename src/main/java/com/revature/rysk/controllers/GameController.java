package com.revature.rysk.controllers;

import com.revature.rysk.dto.GameDto;
import com.revature.rysk.dto.GameLogDto;
import com.revature.rysk.dto.PlayerWithAuthTokenDto;
import com.revature.rysk.entities.Game.Card;
import com.revature.rysk.entities.Game.Country;
import com.revature.rysk.entities.Game.Game;
import com.revature.rysk.entities.Player.Player;
import com.revature.rysk.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class GameController {
    @Autowired
    GameService gameService;

    @PostMapping("/game/new")
    public GameDto newGame(@RequestBody() List<PlayerWithAuthTokenDto> players) {
        Game game = gameService.newGame(PlayerWithAuthTokenDto.getPlayers(players));
        Player player = PlayerWithAuthTokenDto.getPlayer(players.get(0));
        return GameDto.getDto(game, player);
    }

    @PostMapping("/game/{gameId}/join")
    public GameDto joinGame(@RequestBody PlayerWithAuthTokenDto playerDto, @PathVariable("gameId") long gameId) {
        Player player = PlayerWithAuthTokenDto.getPlayer(playerDto);
        Game game = gameService.joinGame(player, gameId);
        return GameDto.getDto(game, player);
    }

    @PostMapping("/game/{gameId}/quit")
    public String quitGame(@RequestBody PlayerWithAuthTokenDto playerDto, @PathVariable("gameId") long gameId) {
        Player player = PlayerWithAuthTokenDto.getPlayer(playerDto);
        return gameService.quitGame(player, gameId);
    }

    @PostMapping("/game/{gameId}/exit")
    public String exitGame(@RequestBody PlayerWithAuthTokenDto playerDto, @PathVariable("gameId") long gameId) {
        Player player = PlayerWithAuthTokenDto.getPlayer(playerDto);
        return gameService.exitGame(player, gameId);
    }

    @PostMapping("/game/{gameId}/log")
    public List<GameLogDto> getFullLog(@RequestBody PlayerWithAuthTokenDto playerDto, @PathVariable("gameId") long gameId) {
        Player player = PlayerWithAuthTokenDto.getPlayer(playerDto);
        return gameService.getFullLog(player, gameId);
    }

    @PostMapping("/game/{gameId}/log/{logId}")
    public List<GameLogDto> tailLog(@RequestBody PlayerWithAuthTokenDto playerDto,
                                    @PathVariable("gameId") long gameId,
                                    @PathVariable("logId") int logId) {
        Player player = PlayerWithAuthTokenDto.getPlayer(playerDto);
        return gameService.tailLog(player, gameId, logId);
    }

    // For when the player does not want to discard any cards
    @PostMapping("/game/{gameId}/play/discard")
    public int discard(@RequestBody PlayerWithAuthTokenDto playerDto, @PathVariable("gameId") long gameId) {
        Player player = PlayerWithAuthTokenDto.getPlayer(playerDto);
        return gameService.discard(player, gameId);
    }

    //For when the player wants to discard 3 cards
    @PostMapping("/game/{gameId}/play/discard/{cardType1}/{cardType2}/{cardType3}")
    public int discard(@RequestBody PlayerWithAuthTokenDto playerDto,
                       @PathVariable("gameId") long gameId,
                       @PathVariable("cardType1") Card.TYPE cardType1,
                       @PathVariable("cardType2") Card.TYPE cardType2,
                       @PathVariable("cardType3") Card.TYPE cardType3) {
        Player player = PlayerWithAuthTokenDto.getPlayer(playerDto);
        return gameService.discard(player, gameId, cardType1, cardType2, cardType3);
    }

    @PostMapping("/game/{gameId}/play/armies/{countryId}/{numberOfArmies}")
    public Country placeArmies(@RequestBody PlayerWithAuthTokenDto playerDto,
                               @PathVariable("gameId") long gameId,
                               @PathVariable("countryId") int countryId,
                               @PathVariable("numberOfArmies") int numberOfArmies) {
        Player player = PlayerWithAuthTokenDto.getPlayer(playerDto);
        return gameService.placeArmies(player, gameId, countryId, numberOfArmies);
    }

    @PostMapping("/game/{gameId}/play/attack/{attackingCountryId}/{defendingCountryId}/{numberOfArmies}/{numberOfDice}")
    public List<Integer> attack(@RequestBody PlayerWithAuthTokenDto playerDto,
                                @PathVariable("gameId") long gameId,
                                @PathVariable("attackingCountryId") int attackingCountryId,
                                @PathVariable("defendingCountryId") int defendingCountryId,
                                @PathVariable("numberOfArmies") int numberOfArmies,
                                @PathVariable("numberOfDice") int numberOfDice) {
        Player player = PlayerWithAuthTokenDto.getPlayer(playerDto);
        return gameService.attack(gameId, player, attackingCountryId, defendingCountryId, numberOfArmies, numberOfDice);
    }

    //ends attack stage
    @PostMapping("/game/{gameId}/play/attack/")
    public String attack(@RequestBody PlayerWithAuthTokenDto playerDto, @PathVariable("gameId") long gameId) {
        Player player = PlayerWithAuthTokenDto.getPlayer(playerDto);
        return gameService.attack(player, gameId);
    }

    @PostMapping("/game/{gameId}/play/defend/{numberOfDice}")
    public List<Integer> defend(@RequestBody PlayerWithAuthTokenDto playerDto, @PathVariable("gameId") long gameId, @PathVariable("numberOfDice") int numberOfDice) {
        Player player = PlayerWithAuthTokenDto.getPlayer(playerDto);
        return gameService.defend(player, gameId, numberOfDice);
    }

    @PostMapping("/game/{gameId}/play/move/{fromCountryId}/{toCountryId}/{numberOfArmies}")
    public List<Country> move(@RequestBody PlayerWithAuthTokenDto playerDto,
                              @PathVariable("gameId") long gameId,
                              @PathVariable("fromCountryId") int fromCountryId,
                              @PathVariable("toCountryId") int toCountryId,
                              @PathVariable("numberOfArmies") int numberOfArmies) {
        Player player = PlayerWithAuthTokenDto.getPlayer(playerDto);
        return gameService.move(player, gameId, fromCountryId, toCountryId, numberOfArmies);
    }

    @PostMapping("/game/{gameId}/play/draw")
    public Card draw(@RequestBody PlayerWithAuthTokenDto playerDto, @PathVariable("gameId") long gameId) {
        Player player = PlayerWithAuthTokenDto.getPlayer(playerDto);
        return gameService.draw(player, gameId);
    }
}
