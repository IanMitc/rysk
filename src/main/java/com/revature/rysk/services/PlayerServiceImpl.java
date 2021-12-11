package com.revature.rysk.services;

import com.revature.rysk.entities.Player;
import com.revature.rysk.exceptions.DuplicateResourceException;
import com.revature.rysk.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public Player addPlayer(Player player) {
        Player checkedPlayer = playerRepository.getPlayerByPlayerEmail(player.getPlayerEmail());
        if (checkedPlayer != null)
            throw new DuplicateResourceException("Email is already in use.");
        return playerRepository.save(player);
    }

    @Override
    public Player getPlayerByEmail(String email) {
        return playerRepository.getPlayerByPlayerEmail(email);
    }

    @Override
    public Player getPlayerById(long id) {
        return playerRepository.getById(id);
    }

    @Override
    public Player updatePlayer(long id, Player player) {
        Player playerFromDb = playerRepository.getById(id);
        System.out.println(player);
        if (playerFromDb.getPlayerId() != player.getPlayerId() && player.getPlayerId() != 0) {
            return null;
        }

        playerFromDb.setPlayerName(player.getPlayerName());
        playerFromDb.setPlayerEmail(player.getPlayerEmail());
        return playerRepository.save(playerFromDb);
    }
}
