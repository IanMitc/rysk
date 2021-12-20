package com.revature.rysk.controllers;

import com.revature.rysk.dto.GameForDisplayDto;
import com.revature.rysk.dto.PlayerForDisplayDto;
import com.revature.rysk.dto.PlayerWithAuthTokenDto;
import com.revature.rysk.dto.PlayerWithPasswordDto;
import com.revature.rysk.entities.Game.Game;
import com.revature.rysk.services.GameService;
import com.revature.rysk.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private GameService gameService;

    @PostMapping("/player")
    public PlayerWithAuthTokenDto addPlayer(@RequestBody PlayerWithPasswordDto newPlayer) {
        return PlayerWithAuthTokenDto.getDto(playerService.addPlayer(PlayerWithPasswordDto.getPlayer(newPlayer)));
    }

    @PostMapping("/player/email")
    public PlayerForDisplayDto getPlayer(@RequestBody String email) {
        return PlayerForDisplayDto.getDto(playerService.getPlayerByEmail(email));
    }

    @PutMapping("/player")
    public PlayerWithAuthTokenDto updatePlayer(@RequestBody PlayerWithPasswordDto updatedPlayer) {
        return PlayerWithAuthTokenDto.getDto(
                playerService.updatePlayer(
                        updatedPlayer.getPlayerId(),
                        PlayerWithPasswordDto.getPlayer(updatedPlayer)
                ));
    }

    @PostMapping("/player/login")
    public PlayerWithAuthTokenDto login(@RequestBody PlayerWithPasswordDto player) {
        return PlayerWithAuthTokenDto.getDto(playerService.login(PlayerWithPasswordDto.getPlayer(player)));
    }

    @PostMapping("/player/logout")
    public String logout(@RequestBody PlayerWithAuthTokenDto player) {
        return playerService.logout(PlayerWithAuthTokenDto.getPlayer(player));
    }

    @PostMapping("/player/check")
    public PlayerWithAuthTokenDto checkLoggedIn(@RequestBody PlayerWithAuthTokenDto player) {
        return PlayerWithAuthTokenDto.getDto(playerService.checkLoggedIn(PlayerWithAuthTokenDto.getPlayer(player)));
    }

    @PostMapping("/player/games")
    public List<GameForDisplayDto> getGames(@RequestBody PlayerWithAuthTokenDto player) {
        List<Game> games = gameService.getGames(PlayerWithAuthTokenDto.getPlayer(player));

        List<GameForDisplayDto> gameDtoList = new ArrayList<>();
        for (Game g : games) {
            gameDtoList.add(GameForDisplayDto.getDto(g));
        }

        return gameDtoList;
    }
}
