package com.revature.rysk.services;

import com.revature.rysk.entities.Game.*;
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

    //Not secure, need to refactor to make sure only logged in players can create games
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

        if (!playerFromDb.getPlayerAuthToken().getAuthToken().equals(player.getPlayerAuthToken().getAuthToken())) {
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

        if (!playerFromDb.getPlayerAuthToken().getAuthToken().equals(player.getPlayerAuthToken().getAuthToken())) {
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

        if (!playerFromDb.getPlayerAuthToken().getAuthToken().equals(player.getPlayerAuthToken().getAuthToken())) {
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

        if (!playerFromDb.getPlayerAuthToken().getAuthToken().equals(player.getPlayerAuthToken().getAuthToken())) {
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

        return game.getLogs();
    }

    @Override
    public List<GameLog> tailLog(Player player, long gameId, int logId) {
        if (logId < 0) {
            throw new BadRequestException("There are no negative logs");
        }

        Optional<Player> playerOptional = playerRepository.getPlayerByPlayerEmail(player.getPlayerEmail());

        if (playerOptional.isEmpty()) {
            throw new NotFoundException("Player not found");
        }

        Player playerFromDb = playerOptional.get();

        if (!playerFromDb.getPlayerAuthToken().getAuthToken().equals(player.getPlayerAuthToken().getAuthToken())) {
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

        List<GameLog> partialLogs = new ArrayList<>();

        if (logId < gameLogs.size()) {
            partialLogs = gameLogs.subList(logId + 1, gameLogs.size());
        }

        return partialLogs;
    }

    @Override
    public int discard(Player player, long gameId) {
        Optional<Player> playerOptional = playerRepository.getPlayerByPlayerEmail(player.getPlayerEmail());

        if (playerOptional.isEmpty()) {
            throw new NotFoundException("Player not found");
        }

        Player playerFromDb = playerOptional.get();

        if (!playerFromDb.getPlayerAuthToken().getAuthToken().equals(player.getPlayerAuthToken().getAuthToken())) {
            throw new PermissionsException("You are not logged in");
        }

        Optional<Game> gameOptional = gameRepository.findById(gameId);

        if (gameOptional.isEmpty()) {
            throw new NotFoundException("Game not found");
        }

        Game game = gameOptional.get();

        if (game.getStage().ordinal() > Game.STAGE.DISCARD.ordinal()) {
            throw new BadRequestException("Wrong stage to discard cards");
        }

        List<GameLog> logs = game.getLogs();
        int bonus = 0;
        //Calculate Standard number of armies
        List<Country> countryList = game.getCountries();
        List<Country.NAME> playerCountries = new ArrayList<>();
        for (Country c : countryList) {
            if (c.getControlledBy().equals(playerFromDb)) {
                playerCountries.add(c.getName());
            }
        }
        int armies = playerCountries.size() / 3;
        logs.add(GameLog.builder().message(playerFromDb.getPlayerName()
                + " gets "
                + armies
                + " armies for having "
                + playerCountries.size()
                + " countries."
        ).build());

        //Calculate bonus from controlling continents
        List<Country.NAME> NorthAmerica = ContinentCountries.getCountries(Continent.NAME.NorthAmerica);
        List<Country.NAME> SouthAmerica = ContinentCountries.getCountries(Continent.NAME.SouthAmerica);
        List<Country.NAME> Europe = ContinentCountries.getCountries(Continent.NAME.Europe);
        List<Country.NAME> Africa = ContinentCountries.getCountries(Continent.NAME.Africa);
        List<Country.NAME> Asia = ContinentCountries.getCountries(Continent.NAME.Asia);
        List<Country.NAME> Australia = ContinentCountries.getCountries(Continent.NAME.Australia);

        if (playerCountries.containsAll(NorthAmerica)) {
            bonus += 5;
            logs.add(GameLog.builder().message(playerFromDb.getPlayerName()
                    + " gets 5 armies for controlling N. America"
            ).build());
        }
        if (playerCountries.containsAll(SouthAmerica)) {
            bonus += 2;
            logs.add(GameLog.builder().message(playerFromDb.getPlayerName()
                    + " gets 2 armies for controlling S. America"
            ).build());
        }
        if (playerCountries.containsAll(Europe)) {
            bonus += 5;
            logs.add(GameLog.builder().message(playerFromDb.getPlayerName()
                    + " gets 5 armies for controlling Europe"
            ).build());
        }
        if (playerCountries.containsAll(Africa)) {
            bonus += 3;
            logs.add(GameLog.builder().message(playerFromDb.getPlayerName()
                    + " gets 3 armies for controlling Africa"
            ).build());
        }
        if (playerCountries.containsAll(Asia)) {
            bonus += 7;
            logs.add(GameLog.builder().message(playerFromDb.getPlayerName()
                    + " gets 7 armies for controlling Asia"
            ).build());
        }
        if (playerCountries.containsAll(Australia)) {
            bonus += 2;
            logs.add(GameLog.builder().message(playerFromDb.getPlayerName()
                    + " gets 2 armies for controlling Australia"
            ).build());
        }

        int totalArmies = armies + bonus;
        logs.add(GameLog.builder().message(playerFromDb.getPlayerName() + " recieved " + totalArmies + " total armies.").build());

        game.setLogs(logs);
        game.setStage(Game.STAGE.ARMIES);
        game.setArmiesToPlay(totalArmies);
        gameRepository.save(game);
        return totalArmies;
    }

    @Override
    public int discard(Player player, long gameId, Card.TYPE cardType1, Card.TYPE cardType2, Card.TYPE cardType3) {
        Optional<Player> playerOptional = playerRepository.getPlayerByPlayerEmail(player.getPlayerEmail());

        if (playerOptional.isEmpty()) {
            throw new NotFoundException("Player not found");
        }

        Player playerFromDb = playerOptional.get();

        if (!playerFromDb.getPlayerAuthToken().getAuthToken().equals(player.getPlayerAuthToken().getAuthToken())) {
            throw new PermissionsException("You are not logged in");
        }

        Optional<Game> gameOptional = gameRepository.findById(gameId);

        if (gameOptional.isEmpty()) {
            throw new NotFoundException("Game not found");
        }

        Game game = gameOptional.get();

        if (game.getStage().ordinal() > Game.STAGE.DISCARD.ordinal()) {
            throw new BadRequestException("Wrong stage to discard cards");
        }

        List<Hand> playersCards = game.getPlayersCards();
        List<Card> playerHandList = new ArrayList<>();
        Hand playerHand = null;
        for (Hand h : playersCards) {
            if (playerFromDb.equals(h.getHeldBy())) {
                playerHand = h;
                playerHandList = h.getCards();
                playersCards.remove(h);
            }
        }

        if (playerHand == null) {
            throw new NotFoundException("Hand not found");
        } else if (playerHandList.size() < 3) {
            throw new BadRequestException("Player does not hold enough cards to turn in");
        }
        List<Card> discardPile = new ArrayList<>();
        discardPile.add(Card.builder().type(cardType1).build());
        discardPile.add(Card.builder().type(cardType2).build());
        discardPile.add(Card.builder().type(cardType3).build());

        boolean hasAll = true;
        for (Card c : discardPile) {
            hasAll = playerHandList.remove(c);
        }

        if (!hasAll) {
            throw new BadRequestException("Player does not have these cards");
        }
        List<GameLog> logs = game.getLogs();

        //Calculate bonus from cards
        int bonus = 0;
        if ((discardPile.contains(Card.builder().type(Card.TYPE.Joker).build()))
                || (discardPile.get(0) == discardPile.get(1)
                && discardPile.get(0) == discardPile.get(2)
                && discardPile.get(1) == discardPile.get(2)
        )
                || (discardPile.contains(Card.builder().type(Card.TYPE.Cannon).build())
                && discardPile.contains(Card.builder().type(Card.TYPE.FootSoldier).build())
                && discardPile.contains(Card.builder().type(Card.TYPE.Horseman).build())
        )) {
            bonus += game.getBonusArmies();
            game.nextBonus();
            logs.add(GameLog.builder().message(playerFromDb.getPlayerName()
                    + " gets "
                    + bonus
                    + " armies from trading in cards."
            ).build());
        } else {
            throw new BadRequestException("Not a valid set of cards to turn in");
        }

        //Calculate Standard number of armies
        List<Country> countryList = game.getCountries();
        List<Country.NAME> playerCountries = new ArrayList<>();
        for (Country c : countryList) {
            if (c.getControlledBy().equals(playerFromDb)) {
                playerCountries.add(c.getName());
            }
        }
        int armies = playerCountries.size() / 3;
        logs.add(GameLog.builder().message(playerFromDb.getPlayerName()
                + " gets "
                + armies
                + " armies for having "
                + playerCountries.size()
                + " countries."
        ).build());

        //Calculate bonus from controlling continents
        List<Country.NAME> NorthAmerica = ContinentCountries.getCountries(Continent.NAME.NorthAmerica);
        List<Country.NAME> SouthAmerica = ContinentCountries.getCountries(Continent.NAME.SouthAmerica);
        List<Country.NAME> Europe = ContinentCountries.getCountries(Continent.NAME.Europe);
        List<Country.NAME> Africa = ContinentCountries.getCountries(Continent.NAME.Africa);
        List<Country.NAME> Asia = ContinentCountries.getCountries(Continent.NAME.Asia);
        List<Country.NAME> Australia = ContinentCountries.getCountries(Continent.NAME.Australia);

        if (playerCountries.containsAll(NorthAmerica)) {
            bonus += 5;
            logs.add(GameLog.builder().message(playerFromDb.getPlayerName()
                    + " gets 5 armies for controlling N. America"
            ).build());
        }
        if (playerCountries.containsAll(SouthAmerica)) {
            bonus += 2;
            logs.add(GameLog.builder().message(playerFromDb.getPlayerName()
                    + " gets 2 armies for controlling S. America"
            ).build());
        }
        if (playerCountries.containsAll(Europe)) {
            bonus += 5;
            logs.add(GameLog.builder().message(playerFromDb.getPlayerName()
                    + " gets 5 armies for controlling Europe"
            ).build());
        }
        if (playerCountries.containsAll(Africa)) {
            bonus += 3;
            logs.add(GameLog.builder().message(playerFromDb.getPlayerName()
                    + " gets 3 armies for controlling Africa"
            ).build());
        }
        if (playerCountries.containsAll(Asia)) {
            bonus += 7;
            logs.add(GameLog.builder().message(playerFromDb.getPlayerName()
                    + " gets 7 armies for controlling Asia"
            ).build());
        }
        if (playerCountries.containsAll(Australia)) {
            bonus += 2;
            logs.add(GameLog.builder().message(playerFromDb.getPlayerName()
                    + " gets 2 armies for controlling Australia"
            ).build());
        }

        int totalArmies = armies + bonus;
        logs.add(GameLog.builder().message(playerFromDb.getPlayerName() + " recieved " + totalArmies + " total armies.").build());

        //Make sure game state is updated and saved
        playerHand.setCards(playerHandList);
        playersCards.add(playerHand);
        game.setPlayersCards(playersCards);
        Deck deck = game.getDeck();
        deck.discard(discardPile);
        game.setDeck(deck);
        game.setLogs(logs);
        game.setStage(Game.STAGE.ARMIES);
        game.setArmiesToPlay(totalArmies);
        gameRepository.save(game);
        return totalArmies;
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
