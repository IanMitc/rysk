package com.revature.rysk.controllers;

import com.revature.rysk.entities.AuthToken;
import com.revature.rysk.entities.Player;
import com.revature.rysk.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping("/player")
    public Player addPlayer(@RequestBody Player newPlayer){
        newPlayer.setAuthToken(new AuthToken());
        System.out.println(newPlayer);
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

}
