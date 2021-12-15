package com.revature.rysk.entities.Game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.rysk.entities.Player.Player;
import com.revature.rysk.exceptions.BadRequestException;
import com.revature.rysk.exceptions.NotFoundException;
import com.revature.rysk.exceptions.PermissionsException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_id_generator")
    @SequenceGenerator(name = "game_id_sequence", sequenceName = "game_id_sequence")
    private long gameId;

    @JsonIgnoreProperties({"playerAuthToken", "playerPassword"})
    @ManyToMany
    @JoinTable(name = "game_players", joinColumns = @JoinColumn(name = "game_null", referencedColumnName = "gameId"), inverseJoinColumns = @JoinColumn(name = "players_player_id", referencedColumnName = "playerId"))
    private List<Player> players = new ArrayList<>(6);

    @JsonIgnoreProperties({"playerAuthToken", "playerPassword"})
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_player_player_id")
    private Player currentPlayer;

    @JsonIgnoreProperties({"playerAuthToken", "playerPassword"})
    @ManyToOne
    @JoinColumn(name = "attacking_player_player_id")
    private Player attackingPlayer;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "attacking_country_game_db_country_id")
    private Country attackingCountry;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "defending_country_game_db_country_id")
    private Country defendingCountry;

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "deck_deck_id")
    private Deck deck;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "game_players_cards",
            joinColumns = @JoinColumn(name = "game_null", referencedColumnName = "gameId"),
            inverseJoinColumns = @JoinColumn(name = "players_cards_hand_id", referencedColumnName = "handId"))
    List<Hand> playersCards = new ArrayList<>();

    @JsonIgnoreProperties({"game"})
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameLog> logs = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "game_countries",
            joinColumns = @JoinColumn(name = "game_null", referencedColumnName = "gameId"),
            inverseJoinColumns = @JoinColumn(name = "countries_game_db_id", referencedColumnName = "gameDbCountryId"))
    List<Country> countries = new ArrayList<>();

    @JsonIgnore
    private int attackingDice1;
    @JsonIgnore
    private int attackingDice2;
    @JsonIgnore
    private int attackingDice3;

    @JsonIgnore
    private int defendingDice1;
    @JsonIgnore
    private int defendingDice2;

    private int armiesToPlay;
    private boolean playerWon;
    private int bonusArmies;
    private STAGE stage;

    public void newGame(List<Player> playersForGame) {
        //gets a new deck of all the cards
        this.deck = new Deck();
        this.deck.newDeck();

        //Randomize turn order
        Collections.shuffle(playersForGame);
        this.players = new LinkedList<>();
        this.players.addAll(playersForGame);

        //Create all Countries and populate them with one Army for start of game
        int i = 0;
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Alaska).printableName("Alaska").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Alberta).printableName("Alberta").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.CentralAmerica).printableName("Central America").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.EasternUnitedStates).printableName("Eastern US").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Greenland).printableName("Greenland").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.NorthwestTerritory).printableName("NW Territory").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Ontario).printableName("Ontario").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Quebec).printableName("Quebec").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.WesternUnitedStates).printableName("Western US").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Argentina).printableName("Argentina").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Brazil).printableName("Brazil").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Peru).printableName("Peru").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Venezuela).printableName("Venezuela").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.GreatBritain).printableName("Great Britain").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Iceland).printableName("Iceland").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.NorthernEurope).printableName("N Europe").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Scandinavia).printableName("Scandinavia").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.SouthernEurope).printableName("S Europe").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Ukraine).printableName("Ukraine").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.WesternEurope).printableName("W Europe").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Congo).printableName("Congo").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.EastAfrica).printableName("E Africa").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Egypt).printableName("Egypt").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Madagascar).printableName("Madagascar").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.NorthAfrica).printableName("N Africa").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.SouthAfrica).printableName("S Africa").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Afghanistan).printableName("Afghanistan").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.China).printableName("China").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.India).printableName("India").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Irkutsk).printableName("Irkutsk").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Japan).printableName("Japan").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Kamchatka).printableName("Kamchatka").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.MiddleEast).printableName("Middle East").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Mongolia).printableName("Mongolia").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Siam).printableName("Siam").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Siberia).printableName("Siberia").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Ural).printableName("Ural").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Yakutsk).printableName("Yakutsk").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.EasternAustralia).printableName("E Australia").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.Indonesia).printableName("Indonesia").armies(1).build());
        countries.add(Country.builder().countryId(i++).name(Country.NAME.NewGuinea).printableName("New Guinea").armies(1).build());
        countries.add(Country.builder().countryId(i).name(Country.NAME.WesternAustralia).printableName("W Australia").armies(1).build());

        List<Country> countriesToDeal = new ArrayList<>(countries);
        Collections.shuffle(countriesToDeal);
        //for each country, assign the top player as the controller
        for (Country c : countriesToDeal) {
            Player p = players.get(0);
            players.remove(p);
            players.add(p);
            c.setControlledBy(p);
        }

        //Create empty hands for player to receive cards
        for (Player p : players) {
            playersCards.add(Hand.builder().heldBy(p).build());
        }
        this.attackingDice1 = -1;
        this.attackingDice2 = -1;
        this.attackingDice3 = -1;
        this.defendingDice1 = -1;
        this.defendingDice2 = -1;
        this.playerWon = false;
        this.bonusArmies = 4;
        this.stage = STAGE.DISCARD;

        nextPlayer();
        log("New Game Started");
        log(currentPlayer.getPlayerName() + " goes first");
    }

    //If a player leaves the game, we take their countries and deal them to the remaining players.
    //We set the armies to one for an even playing field.
    public void removePlayer(Player player) {
        this.players.remove(player);
        List<Country> countriesToRedistribute = new ArrayList<>();
        System.out.println(countriesToRedistribute);
        for (Country c : this.countries) {
            if (c.getControlledBy().equals(player)) {
                countriesToRedistribute.add(c);
                System.out.println(c);
            }
        }

        List<Player> playersLeft = new ArrayList<>(this.players);

        for (Country c : countriesToRedistribute) {
            Player p = playersLeft.get(0);
            playersLeft.remove(p);
            playersLeft.add(p);
            c.setControlledBy(p);
            c.setArmies(1);
            System.out.println(c);
        }

        if (player.equals(this.currentPlayer)) {
            this.currentPlayer = this.players.get(0);
            this.players.remove(this.currentPlayer);
            this.players.add(this.currentPlayer);
        }
    }

    //Add a message to the Game log
    public void log(String message) {
        this.logs.add(GameLog.builder().message(message).build());
    }

    //Get logs after logId
    public List<GameLog> getLogs(int logId) {
        List<GameLog> partialLogs = new ArrayList<>();

        if (logId < this.logs.size()) {
            partialLogs = this.logs.subList(logId + 1, this.logs.size());
        }

        return partialLogs;
    }

    //The Army bonus increases each time a set is turned in
    private void nextBonus() {
        if (this.bonusArmies < 12) {
            this.bonusArmies += 2;
        } else if (this.bonusArmies < 15) {
            this.bonusArmies += 3;
        } else {
            this.bonusArmies += 5;
        }
    }

    //Calculate armies and bonuses for controlling a continent
    public int getArmiesToPlay(Player playerFromDb, List<Card> discardPile) {
        checkStage(playerFromDb, STAGE.DISCARD);

        //Calculate Standard number of armies
        List<Country> countryList = this.getCountries();
        List<Country.NAME> playerCountries = new ArrayList<>();
        for (Country c : countryList) {
            if (c.getControlledBy().equals(currentPlayer)) {
                playerCountries.add(c.getName());
            }
        }
        int armies = playerCountries.size() / 3;
        this.log(currentPlayer.getPlayerName()
                + " gets "
                + armies
                + " armies for having "
                + playerCountries.size()
                + " countries."
        );

        //Calculate bonus from controlling continents
        List<Country.NAME> NorthAmerica = ContinentCountries.getCountries(Continent.NAME.NorthAmerica);
        List<Country.NAME> SouthAmerica = ContinentCountries.getCountries(Continent.NAME.SouthAmerica);
        List<Country.NAME> Europe = ContinentCountries.getCountries(Continent.NAME.Europe);
        List<Country.NAME> Africa = ContinentCountries.getCountries(Continent.NAME.Africa);
        List<Country.NAME> Asia = ContinentCountries.getCountries(Continent.NAME.Asia);
        List<Country.NAME> Australia = ContinentCountries.getCountries(Continent.NAME.Australia);

        int bonus = 0;
        if (playerCountries.containsAll(NorthAmerica)) {
            bonus += 5;
            this.log(currentPlayer.getPlayerName()
                    + " gets 5 armies for controlling N. America"
            );
        }
        if (playerCountries.containsAll(SouthAmerica)) {
            bonus += 2;
            this.log(currentPlayer.getPlayerName()
                    + " gets 2 armies for controlling S. America"
            );
        }
        if (playerCountries.containsAll(Europe)) {
            bonus += 5;
            this.log(currentPlayer.getPlayerName()
                    + " gets 5 armies for controlling Europe"
            );
        }
        if (playerCountries.containsAll(Africa)) {
            bonus += 3;
            this.log(currentPlayer.getPlayerName()
                    + " gets 3 armies for controlling Africa"
            );
        }
        if (playerCountries.containsAll(Asia)) {
            bonus += 7;
            this.log(currentPlayer.getPlayerName()
                    + " gets 7 armies for controlling Asia"
            );
        }
        if (playerCountries.containsAll(Australia)) {
            bonus += 2;
            this.log(currentPlayer.getPlayerName()
                    + " gets 2 armies for controlling Australia"
            );
        }

        //Calculate bonus from trading in cards
        if (discardPile != null) {

            List<Hand> workingHands = new ArrayList<>(this.playersCards);
            List<Card> playerHandList = new ArrayList<>();
            Hand playerHand = null;
            for (Hand h : workingHands) {
                if (playerFromDb.equals(h.getHeldBy())) {
                    playerHand = h;
                    playerHandList = h.getCards();
                    workingHands.remove(h);
                }
            }

            if (playerHand == null) {
                throw new NotFoundException("Hand not found");
            } else if (playerHandList.size() < 3) {
                throw new BadRequestException("Player does not hold enough cards to turn in");
            }

            boolean hasAll = true;
            for (Card c : discardPile) {
                hasAll = playerHandList.remove(c);
            }

            if (!hasAll) {
                throw new BadRequestException("Player does not have these cards");
            }

            //Calculate bonus from cards
            if ((discardPile.contains(Card.builder().type(Card.TYPE.Joker).build()))
                    || (discardPile.get(0) == discardPile.get(1)
                    && discardPile.get(0) == discardPile.get(2)
                    && discardPile.get(1) == discardPile.get(2)
            )
                    || (discardPile.contains(Card.builder().type(Card.TYPE.Cannon).build())
                    && discardPile.contains(Card.builder().type(Card.TYPE.FootSoldier).build())
                    && discardPile.contains(Card.builder().type(Card.TYPE.Horseman).build())
            )) {
                bonus += bonusArmies;
                nextBonus();
                log(playerFromDb.getPlayerName()
                        + " gets "
                        + bonus
                        + " armies from trading in cards."
                );
            } else {
                throw new BadRequestException("Not a valid set of cards to turn in");
            }

            //Create new hand from remaining cards and save to playersCards
            workingHands.add(Hand.builder().heldBy(playerFromDb).cards(playerHandList).build());
            this.playersCards = workingHands;
            this.deck.discard(discardPile);
        }

        int totalArmies = armies + bonus;
        this.log(currentPlayer.getPlayerName() + " recieved " + totalArmies + " total armies.");

        this.stage = Game.STAGE.ARMIES;
        this.armiesToPlay = totalArmies;

        return this.armiesToPlay;
    }

    public Country placeArmies(Player playerFromDb, int countryId, int numberOfArmies) {
        checkStage(playerFromDb, STAGE.ARMIES);

        if (numberOfArmies > this.armiesToPlay) {
            throw new BadRequestException("Not enough armies left");
        }

        Country country = this.countries.get(countryId);

        if (!country.getControlledBy().equals(playerFromDb)) {
            throw new PermissionsException("This is not your country");
        }

        country.addArmies(numberOfArmies);
        this.armiesToPlay -= numberOfArmies;
        return country;
    }

    public List<Integer> attack(Player playerFromDb, int attackingCountryId, int defendingCountryId, int numberOfArmies, int numberOfDice) {
        checkStage(playerFromDb, STAGE.ATTACK);

        if (numberOfDice > 3 || numberOfDice < 1) {
            throw new BadRequestException("Please roll 1-3 dice");
        }

        this.attackingPlayer = playerFromDb;
        this.attackingCountry = this.countries.get(attackingCountryId);

        if (!this.attackingCountry.getControlledBy().equals(playerFromDb)) {
            this.attackingCountry = null;
            this.attackingPlayer = null;
            throw new PermissionsException("This is not your country");
        }

        if (numberOfArmies > this.attackingCountry.getArmies() - 1) {
            this.attackingCountry = null;
            this.attackingPlayer = null;
            throw new BadRequestException("You don't have enough armies for this attack");
        }

        //Can't roll more dice than the number of attacking armies
        if (numberOfArmies < numberOfDice) {
            numberOfDice = numberOfArmies;
        }

        //Get defending player and set as current, so they can defend
        this.defendingCountry = this.countries.get(defendingCountryId);
        this.currentPlayer = this.defendingCountry.getControlledBy();
        this.stage = STAGE.DEFEND;

        Random random = new Random();
        attackingDice1 = random.nextInt(6) + 1;
        if (numberOfDice > 1) {
            attackingDice2 = random.nextInt(6) + 1;
        }
        if (numberOfDice > 2) {
            attackingDice3 = random.nextInt(6) + 1;
        }

        List<Integer> roll = new ArrayList<>();
        roll.add(attackingDice1);
        roll.add(attackingDice2);
        roll.add(attackingDice3);
        return roll.subList(0, numberOfDice);
    }

    public List<Integer> defend(Player playerFromDb, int numberOfDice) {
        checkStage(playerFromDb, STAGE.DEFEND);

        if (numberOfDice > 2 || numberOfDice < 1) {
            throw new BadRequestException("Please roll 1 or 2 dice");
        }

        //Can't roll more dice than the number of defending armies
        if (this.defendingCountry.getArmies() < numberOfDice) {
            numberOfDice = this.defendingCountry.getArmies();
        }

        Random random = new Random();
        defendingDice1 = random.nextInt(6) + 1;
        if (numberOfDice > 1) {
            defendingDice2 = random.nextInt(6) + 1;
        }

        List<Integer> defendingRoll = new ArrayList<>();
        defendingRoll.add(defendingDice1);
        defendingRoll.add(defendingDice2);
        defendingRoll.sort(Comparator.reverseOrder());
        List<Integer> attackingRoll = new ArrayList<>();
        attackingRoll.add(attackingDice1);
        attackingRoll.add(attackingDice2);
        attackingRoll.add(attackingDice3);
        attackingRoll.sort(Comparator.reverseOrder());

        int attackerLoses = 0;
        int defenderLoses = 0;
        for (int i = 0; i < 2; i++) {
            if (defendingRoll.get(i) > -1 && attackingRoll.get(i) > -1) {
                if (defendingRoll.get(i) < attackingRoll.get(i)) {
                    defenderLoses++;
                } else {
                    attackerLoses++;
                }
            }
        }

        attackingCountry.subtractArmies(attackerLoses);
        defendingCountry.subtractArmies(defenderLoses);

        log(this.attackingPlayer.getPlayerName() + " rolled " +
                (this.attackingDice1 + 1) +
                (this.attackingDice2 > -1 ? ", " + (this.attackingDice2 + 1) : "") +
                (this.attackingDice3 > -1 ? " and " + (this.attackingDice3 + 1) : "")
        );
        log(this.currentPlayer.getPlayerName() + " rolled " +
                (this.defendingDice1 + 1) +
                (this.defendingDice2 > -1 ? " and " + (this.defendingDice2 + 1) : "")
        );

        log(this.attackingPlayer.getPlayerName() +
                " lost " + attackerLoses +
                " armies from " + this.attackingCountry.getPrintableName());

        log(this.currentPlayer.getPlayerName() +
                " lost " + defenderLoses +
                " armies from " + this.defendingCountry.getPrintableName());

        if (this.defendingCountry.getArmies() == 0) {
            this.defendingCountry.setControlledBy(this.attackingPlayer);
            this.playerWon = true;
            log(this.attackingPlayer + " captured " + this.defendingCountry.getPrintableName());
        }

        //Clear out info from current attack
        this.currentPlayer = this.attackingPlayer;
        this.attackingPlayer = null;
        this.attackingCountry = null;
        this.defendingCountry = null;
        this.attackingDice1 = -1;
        this.attackingDice2 = -1;
        this.attackingDice3 = -1;
        this.defendingDice1 = -1;
        this.defendingDice2 = -1;
        this.stage = STAGE.ATTACK;
        return defendingRoll.subList(0, numberOfDice);
    }

    private void checkStage(Player playerFromDb, STAGE stageToCheck) {
        //Make sure that it's the player's turn
        if (!currentPlayer.equals(playerFromDb)) {
            throw new PermissionsException("It's not your turn");
        }

        //Make sure that this is the right time
        if (this.stage.ordinal() > stageToCheck.ordinal()) {
            throw new BadRequestException("Wrong stage to discard cards");
        } else if (this.stage.ordinal() < stageToCheck.ordinal()) {
            this.stage = stageToCheck;
        }
    }

    private void nextPlayer() {
        //Take the top player and make him current
        this.currentPlayer = this.players.get(0);
        this.players.remove(this.currentPlayer);
        this.players.add(this.currentPlayer);
    }

    public List<Country> move(Player playerFromDb, int fromCountryId, int toCountryId, int numberOfArmies) {
        checkStage(playerFromDb, STAGE.MOVE);

        Country fromCountry = this.countries.get(fromCountryId);
        Country toCountry = this.countries.get(toCountryId);

        //Make sure that the countries border each other
        List<Country.NAME> neighbors = CountryNeighbors.getNeighbors(fromCountry.getName());
        if (!neighbors.contains(toCountry.getName())) {
            throw new BadRequestException("These countries aren't neighbors");
        }

        if (numberOfArmies > fromCountry.getArmies() - 1) {
            throw new BadRequestException("You can only move up to " + (fromCountry.getArmies() - 1) + " countries");
        }

        fromCountry.subtractArmies(numberOfArmies);
        toCountry.addArmies(numberOfArmies);
        log(playerFromDb.getPlayerName() +
                " moved " +
                numberOfArmies +
                " armies from " +
                fromCountry.getPrintableName() +
                " to " +
                toCountry.getPrintableName()
        );

        List<Country> changedCountries = new ArrayList<>();
        changedCountries.add(fromCountry);
        changedCountries.add(toCountry);
        this.stage = STAGE.DRAW;
        return changedCountries;
    }

    public Card draw(Player playerFromDb) {
        checkStage(playerFromDb, STAGE.DRAW);

        Card card = null;
        if (playerWon) {
            card = this.deck.draw();
            playerWon = false;
        }

        nextPlayer();
        this.stage = STAGE.DISCARD;
        return card;
    }

    public enum STAGE {
        DISCARD, ARMIES, ATTACK, DEFEND, MOVE, DRAW
    }
}
