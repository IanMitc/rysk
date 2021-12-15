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

        checkAuthorized(players.get(0));

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
        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);
        game.removePlayer(playerFromDb);
        game.log(playerFromDb.getPlayerName() + " quit the game. Countries have been reallocated.");
        gameRepository.save(game);
        return "Success";
    }

    @Override
    public Game joinGame(Player player, long gameId) {
        Player playerFromDb = checkAuthorized(player);
        return getGame(playerFromDb, gameId);
    }

    @Override
    public String exitGame(Player player, long gameId) {
        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);
        game.log(playerFromDb.getPlayerName() + " exited the game.");
        gameRepository.save(game);
        return "Success";
    }

    @Override
    public List<GameLog> getFullLog(Player player, long gameId) {
        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);
        return game.getLogs();
    }

    @Override
    public List<GameLog> tailLog(Player player, long gameId, int logId) {
        if (logId < 0) {
            throw new BadRequestException("There are no negative logs");
        }

        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);
        return game.getLogs(logId);
    }

    @Override
    public int discard(Player player, long gameId) {
        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);

        int totalArmies = game.getArmiesToPlay(playerFromDb, null);
        gameRepository.save(game);
        return totalArmies;
    }

    @Override
    public int discard(Player player, long gameId, Card.TYPE cardType1, Card.TYPE cardType2, Card.TYPE cardType3) {
        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);

        List<Card> discardPile = new ArrayList<>();
        discardPile.add(Card.builder().type(cardType1).build());
        discardPile.add(Card.builder().type(cardType2).build());
        discardPile.add(Card.builder().type(cardType3).build());

        int totalArmies = game.getArmiesToPlay(playerFromDb, discardPile);
        gameRepository.save(game);
        return totalArmies;
    }

    @Override
    public Country placeArmies(Player player, long gameId, int countryId, int numberOfArmies) {
        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);

        Country country = game.placeArmies(playerFromDb, countryId, numberOfArmies);
        gameRepository.save(game);
        return country;
    }

    @Override
    public List<Integer> attack(long gameId, Player player, int attackingCountryId, int defendingCountryId, int numberOfArmies, int numberOfDice) {
        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);

        List<Integer> attackRoll = game.attack(playerFromDb, attackingCountryId, defendingCountryId, numberOfArmies, numberOfDice);
        gameRepository.save(game);
        return attackRoll;
    }

    @Override
    public List<Integer> defend(Player player, long gameId, int numberOfDice) {
        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);

        List<Integer> defenseRoll = game.defend(playerFromDb, numberOfDice);
        gameRepository.save(game);
        return defenseRoll;
    }

    @Override
    public List<Country> move(Player player, long gameId, int fromCountryId, int toCountryId, int numberOfArmies) {
        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);

        List<Country> changedCountries = game.move(playerFromDb, fromCountryId, toCountryId, numberOfArmies);
        gameRepository.save(game);
        return changedCountries;
    }

    @Override
    public Card draw(Player player, long gameId) {
        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);

        return game.draw(playerFromDb);
    }

    @Override
    public List<Long> getGames(Player player) {
        Player playerFromDb = checkAuthorized(player);
        return gameRepository.getGamesByPlayerId(playerFromDb.getPlayerId());
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

    private Game getGame(Player playerFromDb, long gameId) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);

        if (gameOptional.isEmpty()) {
            throw new NotFoundException("Game not found");
        }
        Game game = gameOptional.get();

        List<Player> players = game.getPlayers();
        if (!players.contains(playerFromDb)) {
            throw new PermissionsException("You are not a part of this game");
        }

        return gameOptional.get();
    }
}
