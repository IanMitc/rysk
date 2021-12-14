package com.revature.rysk.services;

import com.revature.rysk.entities.Player.AuthToken;
import com.revature.rysk.entities.Player.Password;
import com.revature.rysk.entities.Player.Player;
import com.revature.rysk.exceptions.DuplicateResourceException;
import com.revature.rysk.exceptions.NotFoundException;
import com.revature.rysk.exceptions.PermissionsException;
import com.revature.rysk.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player addPlayer(Player player) {
        Optional<Player> checkedPlayer = playerRepository.getPlayerByPlayerEmail(player.getPlayerEmail());
        if (checkedPlayer.isPresent()) {
            throw new DuplicateResourceException("Email is already in use");
        }
        player.setPlayerAuthToken(new AuthToken());

        return cleanPlayerForPlayer(playerRepository.save(player));
    }

    @Override
    public Player getPlayerByEmail(String email) {
        Optional<Player> playerOptional = playerRepository.getPlayerByPlayerEmail(email);
        if (playerOptional.isEmpty()) {
            throw new NotFoundException("Player not found");
        }
        Player playerOutput = playerOptional.get();
        return cleanPlayerForOutput(playerOutput);
    }

    @Override
    public Player updatePlayer(long playerId, Player player) {
        //Check to make sure email isn't in use by another registered user
        Optional<Player> playerOptional = playerRepository.getPlayerByPlayerEmail(player.getPlayerEmail());
        Player playerCurrent;
        if (playerOptional.isPresent()) {
            playerCurrent = playerOptional.get();
            if (playerCurrent.getPlayerId() != playerId) {
                throw new DuplicateResourceException("Email is already in use");
            }
        }

        playerCurrent = checkAuthorized(player);

        playerCurrent.setPlayerName(player.getPlayerName());
        playerCurrent.setPlayerEmail(player.getPlayerEmail());
        //player may or may not be passed with a password
        if (player.getPlayerPassword() != null && !Objects.equals(player.getPlayerPassword().getPassword(), "")) {
            playerCurrent.setPlayerPassword(Password.builder()
                    .password(player.getPlayerPassword().getPassword())
                    .build()
            );
        }

        return cleanPlayerForPlayer(playerRepository.save(playerCurrent));
    }

    @Override
    public Player login(Player player) {
        //Don't use check authorized because we only receive email and not player id
        Optional<Player> playerOptional = playerRepository.getPlayerByPlayerEmail(player.getPlayerEmail());

        if (playerOptional.isEmpty()) {
            throw new NotFoundException("Player not found");
        }

        Player playerOutput = playerOptional.get();

        if (!playerOutput.checkPassword(player.getPlayerPassword().getPassword())) {
            throw new NotFoundException("Email or password is incorrect");
        }

        playerOutput.setPlayerAuthToken(new AuthToken());

        return cleanPlayerForPlayer(playerRepository.save(playerOutput));
    }

    @Override
    public String logout(Player player) {
        Player playerFromDb = checkAuthorized(player);

        playerFromDb.setPlayerAuthToken(null);
        playerRepository.save(playerFromDb);
        return "Success";
    }

    @Override
    public Player checkLoggedIn(Player player) {
        Player playerOutput = checkAuthorized(player);
        return cleanPlayerForPlayer(playerOutput);
    }

    //Only used until I can figure out why Jackson annotations won't ignore these properties.
    private Player cleanPlayerForPlayer(Player player) {
        //we make the password empty in the returned object because it should never be needed by the UI
        player.getPlayerPassword().setPassword("");
        return player;
    }

    private Player cleanPlayerForOutput(Player player) {
        //we make the password and auth token empty in the returned object because it should never be needed by the UI
        player.getPlayerAuthToken().setAuthToken("");
        player.getPlayerPassword().setPassword("");
        return player;
    }

    private Player checkAuthorized(Player player) {
        Player playerOutput = getPlayerById(player.getPlayerId());

        if (playerOutput.getPlayerAuthToken() == null || !playerOutput.checkAuthToken(player.getPlayerAuthToken().getAuthToken())) {
            throw new PermissionsException("Not Authorized");
        }

        return playerOutput;
    }

    private Player getPlayerById(long playerId) {
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        if (playerOptional.isEmpty()) {
            throw new NotFoundException("Player not found");
        }

        return playerOptional.get();
    }
}
