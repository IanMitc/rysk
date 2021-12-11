package com.revature.rysk.services;

import com.revature.rysk.entities.Player;
import com.revature.rysk.exceptions.DuplicateResourceException;
import com.revature.rysk.exceptions.NotFoundException;
import com.revature.rysk.exceptions.PermissionsException;
import com.revature.rysk.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public Player addPlayer(Player player) {
        Optional<Player> checkedPlayer = playerRepository.getPlayerByPlayerEmail(player.getPlayerEmail());
        if (checkedPlayer.isPresent())
            throw new DuplicateResourceException("Email is already in use");

        Player playerOutput = playerRepository.save(player);
        //we make the password null in the returned object because it should never be needed by the UI
        playerOutput.setPassword(null);
        return playerOutput;
    }

    @Override
    public Player getPlayerByEmail(String email) {
        Optional<Player> player = playerRepository.getPlayerByPlayerEmail(email);
        if (player.isPresent()) {
            return player.get();
        }
        throw new NotFoundException("Player not found");
    }

    @Override
    public Player getPlayerById(long id) {
        Player playerOutput = playerRepository.getById(id);
        //we make the password null in the returned object because it should never be needed by the UI
        playerOutput.setPassword(null);
        return playerOutput;
    }

    @Override
    public Player updatePlayer(long id, Player player) {
        Player playerFromDb = playerRepository.getById(id);
        Optional<Player> playerByEmail = playerRepository.getPlayerByPlayerEmail(player.getPlayerEmail());

        System.out.println(player);
        if (playerFromDb.getPlayerId() != player.getPlayerId() && player.getPlayerId() != 0) {
            throw new NotFoundException("Player not found");
        } else if (!playerFromDb.getAuthToken().getAuthToken().equals(player.getAuthToken().getAuthToken())) {
            throw new PermissionsException("Not authorized to update this Player");
        }

        if (playerByEmail.isPresent() && playerByEmail.get().getPlayerId() != player.getPlayerId()) {
            throw new DuplicateResourceException("Email is already in use");
        }

        playerFromDb.setPlayerName(player.getPlayerName());
        playerFromDb.setPlayerEmail(player.getPlayerEmail());
        Player playerOutput = playerRepository.save(playerFromDb);
        //we make the password null in the returned object because it should never be needed by the UI
        playerOutput.setPassword(null);
        return playerOutput;
    }
}
