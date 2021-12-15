package com.revature.rysk.controllers;

import com.revature.rysk.entities.Player.Player;
import com.revature.rysk.services.GameService;
import com.revature.rysk.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private GameService gameService;

    @PostMapping("/player")
    public Player addPlayer(@RequestBody Player newPlayer) {
        return playerService.addPlayer(newPlayer);
    }

    @PostMapping("/player/email")
    public Player getPlayer(@RequestBody String email) {
        return playerService.getPlayerByEmail(email);
    }

    @PutMapping("/player")
    public Player updatePlayer(@RequestBody Player updatedPlayer) {
        return playerService.updatePlayer(updatedPlayer.getPlayerId(), updatedPlayer);
    }

    @PostMapping("/player/login")
    public Player login(@RequestBody Player player) {
        return playerService.login(player);
    }

    @PostMapping("/player/logout")
    public String logout(@RequestBody Player player) {
        return playerService.logout(player);
    }

    @PostMapping("/player/check")
    public Player checkLoggedIn(@RequestBody Player player) {
        return playerService.checkLoggedIn(player);
    }

    @PostMapping("/player/games")
    public List<Long> getGames(@RequestBody Player player) {
        return gameService.getGames(player);
    }
}
