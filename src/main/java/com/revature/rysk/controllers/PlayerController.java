package com.revature.rysk.controllers;

import com.revature.rysk.entities.AuthToken;
import com.revature.rysk.entities.Player;
import com.revature.rysk.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping("/player")
    public Player addPlayer(@RequestBody Player newPlayer){
        newPlayer.setAuthToken(new AuthToken());
        System.out.println(newPlayer);
        return playerService.addPlayer(newPlayer);
    }

}
