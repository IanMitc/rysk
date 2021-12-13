package com.revature.rysk.services;

import com.revature.rysk.entities.Game.Card;
import com.revature.rysk.entities.Game.Country;
import com.revature.rysk.entities.Game.Game;
import com.revature.rysk.entities.Game.GameLog;
import com.revature.rysk.entities.Player.Player;
import com.revature.rysk.exceptions.BadRequestException;
import com.revature.rysk.exceptions.NotFoundException;
import com.revature.rysk.exceptions.PermissionsException;
import com.revature.rysk.repositories.GameRepository;
import com.revature.rysk.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Game newGame(List<Player> players) {
        if (players.size() > 6 || players.size() < 2) {
            throw new BadRequestException("Only 2-6 players allowed");
        }

        List<Player> newPlayers = new ArrayList<>();
        for (Player newPlayer : players) {
            Optional<Player> newPlayerFromDb = playerRepository.getPlayerByPlayerEmail(newPlayer.getPlayerEmail());

            if (newPlayerFromDb.isEmpty()) {
                throw new NotFoundException(newPlayer.getPlayerEmail() + " not found");
            }

            newPlayers.add(newPlayerFromDb.get());
        }
        Game game = new Game();
        game.newGame(newPlayers);

        gameRepository.save(game);
        return game;
    }

    @Override
    public String quitGame(Player player, long gameId) {
        Optional<Player> playerOptional = playerRepository.getPlayerByPlayerEmail(player.getPlayerEmail());

        if (playerOptional.isEmpty()) {
            throw new NotFoundException("Player not found");
        }

        Player playerFromDb = playerOptional.get();

        if (!playerFromDb.getAuthToken().getAuthToken().equals(player.getAuthToken().getAuthToken())) {
            throw new PermissionsException("You are not logged in");
        }

        Optional<Game> gameOptional = gameRepository.findById(gameId);

        if (gameOptional.isEmpty()) {
            throw new NotFoundException("Game not found");
        }
        Game game = gameOptional.get();
        List<Player> players = game.getPlayers();

        if (!players.contains(playerFromDb)) {
            throw new PermissionsException("You are not a part of this game");
        }

        game.removePlayer(playerFromDb);

        List<GameLog> gameLogs = game.getLogs();
        gameLogs.add(GameLog.builder().message(playerFromDb.getPlayerName() + " quit the game. Countries have been reallocated.").build());

        game.setLogs(gameLogs);

        gameRepository.save(game);
        return "Success";
    }

    @Override
    public Game joinGame(Player player, long gameId) {
        Optional<Player> playerOptional = playerRepository.getPlayerByPlayerEmail(player.getPlayerEmail());

        if (playerOptional.isEmpty()) {
            throw new NotFoundException("Player not found");
        }

        Player playerFromDb = playerOptional.get();

        if (!playerFromDb.getAuthToken().getAuthToken().equals(player.getAuthToken().getAuthToken())) {
            throw new PermissionsException("You are not logged in");
        }

        Optional<Game> gameOptional = gameRepository.findById(gameId);

        if (gameOptional.isEmpty()) {
            throw new NotFoundException("Game not found");
        }
        Game game = gameOptional.get();
        List<Player> players = game.getPlayers();

        if (!players.contains(playerFromDb)) {
            throw new PermissionsException("You are not a part of this game");
        }

        return game;
    }

    @Override
    public String exitGame(Player player, long gameId) {
        Optional<Player> playerOptional = playerRepository.getPlayerByPlayerEmail(player.getPlayerEmail());

        if (playerOptional.isEmpty()) {
            throw new NotFoundException("Player not found");
        }

        Player playerFromDb = playerOptional.get();

        if (!playerFromDb.getAuthToken().getAuthToken().equals(player.getAuthToken().getAuthToken())) {
            throw new PermissionsException("You are not logged in");
        }

        Optional<Game> gameOptional = gameRepository.findById(gameId);

        if (gameOptional.isEmpty()) {
            throw new NotFoundException("Game not found");
        }
        Game game = gameOptional.get();
        List<Player> players = game.getPlayers();

        if (!players.contains(playerFromDb)) {
            throw new PermissionsException("You are not a part of this game");
        }
        List<GameLog> gameLogs = game.getLogs();
        gameLogs.add(GameLog.builder().message(playerFromDb.getPlayerName() + " exited the game.").build());

        game.setLogs(gameLogs);
        gameRepository.save(game);
        return "Success";
    }

    @Override
    public List<GameLog> getFullLog(Player player, long gameId) {
        Optional<Player> playerOptional = playerRepository.getPlayerByPlayerEmail(player.getPlayerEmail());

        if (playerOptional.isEmpty()) {
            throw new NotFoundException("Player not found");
        }

        Player playerFromDb = playerOptional.get();

        if (!playerFromDb.getAuthToken().getAuthToken().equals(player.getAuthToken().getAuthToken())) {
            throw new PermissionsException("You are not logged in");
        }

        Optional<Game> gameOptional = gameRepository.findById(gameId);

        if (gameOptional.isEmpty()) {
            throw new NotFoundException("Game not found");
        }
        Game game = gameOptional.get();
        List<Player> players = game.getPlayers();

        if (!players.contains(playerFromDb)) {
            throw new PermissionsException("You are not a part of this game");
        }
        List<GameLog> gameLogs = game.getLogs();

        return gameLogs;
    }

    @Override
    public List<String> tailLog(Player player, long gameId, long logId) {
        return null;
    }

    @Override
    public int discard(List<Card> cards) {
        return 0;
    }

    @Override
    public Country addArmies(Player player, long gameId, long countryId, int armies) {
        return null;
    }

    @Override
    public List<Integer> attack(Player player, long attackingCountryId, int attackingArmies, int numberOfDice, long defendingCountryId) {
        return null;
    }

    @Override
    public List<Integer> defend(Player player, long defendingCountryId, int numberOfDice) {
        return null;
    }

    @Override
    public Country move(Player player, long fromCountryId, long toCountryId, int numberOfArmies) {
        return null;
    }

    @Override
    public List<Card> draw(Player player) {
        return null;
    }
}
