package com.revature.rysk.services;

import com.revature.rysk.entities.Game.Game;
import com.revature.rysk.entities.Player.Player;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface GameService {

    Game newGame(Set<Player> players);

    String declineGame(Player player, long gameId);

    Game joinGame(Player player, long gameId);

    Game quitGame(Player player, long gameId);

    //Might not be needed as the game should just stop once it gets to a point that a player needs to play
    //and they're not there, but it might be good just to notify the other players
    String exitGame(Player player, long gameId);

    List<String> getFullLog(Player player, long gameId);

    //gets all log messages after specific message
    List<String> tailLog(Player player, long gameId, long logId);

}
