package com.revature.rysk.entities.Game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.rysk.entities.Player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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

    @JsonIgnoreProperties({"authToken", "playerPassword"})
    @ManyToMany
    @JoinTable(name = "game_players", joinColumns = @JoinColumn(name = "game_null", referencedColumnName = "gameId"), inverseJoinColumns = @JoinColumn(name = "players_player_id", referencedColumnName = "playerId"))
    private List<Player> players = new ArrayList<>(6);

    @JsonIgnoreProperties({"authToken", "playerPassword"})
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_player_player_id")
    private Player currentPlayer;

    @JsonIgnoreProperties({"authToken", "playerPassword"})
    @ManyToOne
    @JoinColumn(name = "attacking_player_player_id")
    private Player attackingPlayer;

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
            inverseJoinColumns = @JoinColumn(name = "countries_game_db_id", referencedColumnName = "gameDbId"))
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

        //Take the top player and make him current
        this.currentPlayer = this.players.get(0);
        this.players.remove(this.currentPlayer);
        this.players.add(this.currentPlayer);

        this.bonusArmies = 4;

        this.stage = STAGE.DISCARD;

        this.logs.add(GameLog.builder().game(this).message("New Game Started").build());
        this.logs.add(GameLog.builder().game(this).message(currentPlayer.getPlayerName() + " goes first").build());
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

    //The Army bonus increases each time a set is turned in
    public void nextBonus() {
        if (this.bonusArmies < 12) {
            this.bonusArmies += 2;
        } else if (this.bonusArmies < 15) {
            this.bonusArmies += 3;
        } else {
            this.bonusArmies += 5;
        }
    }

    public enum STAGE {
        DISCARD, ARMIES, ATTACK, DEFEND, MOVE, DRAW
    }
}
