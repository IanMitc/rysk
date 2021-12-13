package com.revature.rysk.controllers;

import com.revature.rysk.entities.Game.Game;
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

    @PostMapping("/game/decline/{gameId}")
    public String declineGame(@RequestBody Player player, @PathVariable("gameId") long gameId) {
        return gameService.declineGame(player, gameId);
    }

    @PostMapping("/game/join/{gameId}")
    public Game joinGame(@RequestBody Player player, @PathVariable("gameId") long gameId) {
        return gameService.joinGame(player, gameId);
    }

    @PostMapping("/game/quit/{gameId}")
    public String quitGame(@RequestBody Player player, @PathVariable("gameId") long gameId) {
        return gameService.declineGame(player, gameId);
    }
}
