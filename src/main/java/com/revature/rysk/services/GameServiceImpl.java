package com.revature.rysk.services;

import com.revature.rysk.dto.GameLogDto;
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
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
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

        gameRepository.saveAndFlush(game);
        return game;
    }

    @Override
    @Transactional
    public String quitGame(Player player, long gameId) {
        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);
        game.removePlayer(playerFromDb);
        game.log(playerFromDb.getPlayerName() + " quit the game. Countries have been reallocated.");
        gameRepository.saveAndFlush(game);
        return "Success";
    }

    @Override
    public Game joinGame(Player player, long gameId) {
        Player playerFromDb = checkAuthorized(player);
        return getGame(playerFromDb, gameId);
    }

    @Override
    @Transactional
    public String exitGame(Player player, long gameId) {
        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);
        game.log(playerFromDb.getPlayerName() + " exited the game.");
        gameRepository.saveAndFlush(game);
        return "Success";
    }

    @Override
    public List<GameLogDto> getFullLog(Player player, long gameId) {
        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);

        List<GameLog> gameLogs = game.getLogs();
        List<GameLogDto> gameLogDtos = new ArrayList<>();
        for (GameLog g : gameLogs) {
            gameLogDtos.add(GameLogDto.getDto(g));
        }

        return gameLogDtos;
    }

    @Override
    public List<GameLogDto> tailLog(Player player, long gameId, int logId) {
        if (logId < 0) {
            throw new BadRequestException("There are no negative logs");
        }

        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);

        List<GameLog> gameLogs = game.getLogs(logId);
        List<GameLogDto> gameLogDtos = new ArrayList<>();
        for (GameLog g : gameLogs) {
            gameLogDtos.add(GameLogDto.getDto(g));
        }

        return gameLogDtos;
    }

    @Override
    @Transactional
    public int discard(Player player, long gameId) {
        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);

        int totalArmies = game.getArmiesToPlay(playerFromDb, null);
        gameRepository.saveAndFlush(game);
        return totalArmies;
    }

    @Override
    @Transactional
    public int discard(Player player, long gameId, Card.TYPE cardType1, Card.TYPE cardType2, Card.TYPE cardType3) {
        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);

        List<Card> discardPile = new ArrayList<>();
        discardPile.add(Card.builder().type(cardType1).build());
        discardPile.add(Card.builder().type(cardType2).build());
        discardPile.add(Card.builder().type(cardType3).build());

        int totalArmies = game.getArmiesToPlay(playerFromDb, discardPile);
        gameRepository.saveAndFlush(game);
        return totalArmies;
    }

    @Override
    @Transactional
    public Country placeArmies(Player player, long gameId, int countryId, int numberOfArmies) {
        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);

        Country country = game.placeArmies(playerFromDb, countryId, numberOfArmies);
        gameRepository.saveAndFlush(game);
        return country;
    }

    @Override
    @Transactional
    public List<Integer> attack(long gameId, Player player, int attackingCountryId, int defendingCountryId, int numberOfArmies, int numberOfDice) {
        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);

        List<Integer> attackRoll = game.attack(playerFromDb, attackingCountryId, defendingCountryId, numberOfArmies, numberOfDice);
        gameRepository.saveAndFlush(game);
        return attackRoll;
    }

    @Override
    @Transactional
    public List<Integer> defend(Player player, long gameId, int numberOfDice) {
        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);

        List<Integer> defenseRoll = game.defend(playerFromDb, numberOfDice);
        gameRepository.saveAndFlush(game);
        return defenseRoll;
    }

    @Override
    @Transactional
    public List<Country> move(Player player, long gameId, int fromCountryId, int toCountryId, int numberOfArmies) {
        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);

        List<Country> changedCountries = game.move(playerFromDb, fromCountryId, toCountryId, numberOfArmies);
        gameRepository.saveAndFlush(game);
        return changedCountries;
    }

    @Override
    @Transactional
    public Card draw(Player player, long gameId) {
        Player playerFromDb = checkAuthorized(player);
        Game game = getGame(playerFromDb, gameId);

        Card card = game.draw(playerFromDb);
        gameRepository.saveAndFlush(game);
        return card;
    }

    @Override
    public List<Game> getGames(Player player) {
        Player playerFromDb = checkAuthorized(player);
        return gameRepository.findGamesByPlayers_PlayerId(playerFromDb.getPlayerId());
    }

    private Player checkAuthorized(Player player) {

        if (player.getPlayerPassword() == null && player.getPlayerAuthToken() == null) {
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
