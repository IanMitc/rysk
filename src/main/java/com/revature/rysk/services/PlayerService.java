package com.revature.rysk.services;

import com.revature.rysk.entities.Player;
import org.springframework.stereotype.Service;

@Service
public interface PlayerService {
    Player addPlayer(Player player);

    Player getPlayerByEmail(String Email);

    Player getPlayerById(long id);

    Player updatePlayer(long id, Player player);

    Player login(Player player);

    Player logout(Player player);

    Player checkLoggedIn(Player player);
}
