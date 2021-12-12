package com.revature.rysk.controllers;

import com.revature.rysk.entities.Player;
import com.revature.rysk.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {
    @Autowired
    private PlayerService playerService;

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
    public Player logout(@RequestBody Player player) {
        return playerService.logout(player);
    }

    @PostMapping("/player/check")
    public Player checkLoggedIn(@RequestBody Player player) {
        return playerService.checkLoggedIn(player);
    }
}
