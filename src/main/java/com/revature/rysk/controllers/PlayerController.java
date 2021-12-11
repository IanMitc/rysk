package com.revature.rysk.controllers;

import com.revature.rysk.entities.AuthToken;
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
        newPlayer.setAuthToken(new AuthToken());
        Player player = playerService.addPlayer(newPlayer);
        player.setPassword(null);
        return player;
    }

    @PostMapping("/player/email")
    public Player getPlayer(@RequestBody String email) {
        System.out.println(email);
        Player player = playerService.getPlayerByEmail(email);
        player.setAuthToken(null);
        player.setPassword(null);
        System.out.println(player);
        return player;
    }

    @PutMapping("/player")
    public Player updatePlayer(@RequestBody Player updatedPlayer) {
        return playerService.updatePlayer(updatedPlayer.getPlayerId(), updatedPlayer);
    }

}
