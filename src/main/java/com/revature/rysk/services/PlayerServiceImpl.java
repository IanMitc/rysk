package com.revature.rysk.services;

import com.revature.rysk.entities.Player.AuthToken;
import com.revature.rysk.entities.Player.Password;
import com.revature.rysk.entities.Player.Player;
import com.revature.rysk.exceptions.BadRequestException;
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

        return playerRepository.save(player);
    }

    @Override
    public Player getPlayerByEmail(String email) {
        Optional<Player> playerOptional = playerRepository.getPlayerByPlayerEmail(email);
        if (playerOptional.isEmpty()) {
            throw new NotFoundException("Player not found");
        }
        return playerOptional.get();
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

        return playerRepository.save(playerCurrent);
    }

    @Override
    public Player login(Player player) {
        Player playerOutput = checkAuthorized(player);

        playerOutput.setPlayerAuthToken(new AuthToken());

        return playerRepository.save(playerOutput);
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
        return checkAuthorized(player);
    }

    private Player checkAuthorized(Player player) {

        if (player.getPlayerPassword() == null && player.getPlayerAuthToken() == null){
            throw new BadRequestException("No authentication provided");
        }

        if (player.getPlayerId() == 0 && (player.getPlayerEmail() == null || player.getPlayerEmail().equals(""))) {
            throw new NotFoundException("Player not found");
        }

        Optional<Player> playerOptional = playerRepository.getPlayerByPlayerEmail(player.getPlayerEmail());

        if (playerOptional.isEmpty()) {
            playerOptional = playerRepository.findById(player.getPlayerId());
            if (playerOptional.isEmpty()) {
                throw new NotFoundException("Player not found");
            }
        }

        Player playerOutput = playerOptional.get();

        if (player.getPlayerAuthToken() != null) {
            if (!playerOutput.checkAuthToken(player.getPlayerAuthToken().getAuthToken())) {
                throw new PermissionsException("Not Authorized");
            }
        } else if (!playerOutput.checkPassword(player.getPlayerPassword().getPassword())) {
            throw new NotFoundException("Email or password is incorrect");
        }

        return playerOutput;
    }
}
