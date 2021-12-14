package com.revature.rysk.services;

import com.revature.rysk.entities.Player.AuthToken;
import com.revature.rysk.entities.Player.Player;
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

        player.setAuthToken(new AuthToken());
        Player playerOutput = playerRepository.save(player);
        //we make the password null in the returned object because it should never be needed by the UI
        playerOutput.getPlayerPassword().setPassword("");
        return playerOutput;
    }

    @Override
    public Player getPlayerByEmail(String email) {
        Optional<Player> player = playerRepository.getPlayerByPlayerEmail(email);
        if (player.isPresent()) {
            Player playerOutput = player.get();
            playerOutput.setAuthToken(null);
            playerOutput.setPlayerPassword(null);
            return playerOutput;
        }
        throw new NotFoundException("Player not found");
    }

    @Override
    public Player getPlayerById(long id) {
        Player playerOutput = playerRepository.getById(id);
        //we make the password empty in the returned object because it should never be needed by the UI
        //playerOutput.getPlayerPassword().setPassword("");
        return playerOutput;
    }

    @Override
    public Player updatePlayer(long id, Player player) {
        Player playerFromDb = playerRepository.getById(id);
        Optional<Player> playerByEmail = playerRepository.getPlayerByPlayerEmail(player.getPlayerEmail());

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
        //we make the password empty in the returned object because it should never be needed by the UI
        playerOutput.getPlayerPassword().setPassword("");
        return playerOutput;
    }

    @Override
    public Player login(Player player) {
        Optional<Player> playerOptional = playerRepository.getPlayerByPlayerEmail(player.getPlayerEmail());

        if (playerOptional.isEmpty()) {
            throw new NotFoundException("Player not found");
        }

        Player playerFromDb = playerOptional.get();
        System.out.println(playerFromDb);


        if (!playerFromDb.getPlayerPassword().getPassword().equals(player.getPlayerPassword().getPassword())) {
            throw new NotFoundException("Email or password is incorrect");
        }


        playerFromDb.setAuthToken(new AuthToken());
        playerRepository.save(playerFromDb);
        //we make the password empty in the returned object because it should never be needed by the UI
        playerFromDb.getPlayerPassword().setPassword("");
        return playerFromDb;
    }

    @Override
    public String logout(Player player) {
        Player playerFromDb = playerRepository.getById(player.getPlayerId());

        AuthToken authToken = playerFromDb.getAuthToken();
        AuthToken authTokenToCheck = player.getAuthToken();

        if (authToken != null) {
            if (!authToken.getAuthToken().equals(authTokenToCheck.getAuthToken())) {
                throw new PermissionsException("Not Authorized");
            }
            playerFromDb.setAuthToken(null);
            playerRepository.save(playerFromDb);
        }

        return "Success";
    }

    @Override
    public Player checkLoggedIn(Player player) {
        Optional<Player> playerFromDb = playerRepository.getPlayerByPlayerEmail(player.getPlayerEmail());
        if (playerFromDb.isEmpty()) {
            throw new NotFoundException("Player not found");
        }

        AuthToken authTokenFromDb = playerFromDb.get().getAuthToken();
        AuthToken authToken = player.getAuthToken();

        if (authTokenFromDb == null || !authTokenFromDb.getAuthToken().equals(authToken.getAuthToken())) {
            throw new PermissionsException("Not logged in");
        }

        Player playerOutput = playerFromDb.get();
        //we make the password empty in the returned object because it should never be needed by the UI
        playerOutput.getPlayerPassword().setPassword("");
        return playerOutput;
    }
}
