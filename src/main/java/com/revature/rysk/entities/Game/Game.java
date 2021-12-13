package com.revature.rysk.entities.Game;

import com.revature.rysk.entities.Player.Player;
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
    @GeneratedValue
    private long gameId;

    @ManyToMany
    @JoinTable(name = "game_players", joinColumns = @JoinColumn(name = "game_null", referencedColumnName = "gameId"), inverseJoinColumns = @JoinColumn(name = "players_player_id", referencedColumnName = "playerId"))
    private Deque<Player> players;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_player_player_id")
    private Player currentPlayer;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Continent> continents;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "deck_deck_id")
    private Deck deck;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameLog> logs = new ArrayList<>();

    private List<Integer> attackingDice;
    private List<Integer> defendingDice;
    private int bonusArmies;
    private STAGE stage;

    public void newGame(List<Player> playersForGame) {
        //gets a new deck of all the cards
        this.deck = new Deck();
        this.deck.newDeck();

        //Randomize turn order
        Collections.shuffle(playersForGame);
        this.players.addAll(playersForGame);

        this.players = new LinkedList<>();


        //Create all Countries and populate them with one Army for start of game
        Country Alaska = Country.builder().name(Country.NAME.Alaska).printableName("Alaska").armies(1).build();
        Country Alberta = Country.builder().name(Country.NAME.Alberta).printableName("Alberta").armies(1).build();
        Country CentralAmerica = Country.builder().name(Country.NAME.CentralAmerica).printableName("Central America").armies(1).build();
        Country EasternUnitedStates = Country.builder().name(Country.NAME.EasternUnitedStates).printableName("Eastern US").armies(1).build();
        Country Greenland = Country.builder().name(Country.NAME.Greenland).printableName("Greenland").armies(1).build();
        Country NorthwestTerritory = Country.builder().name(Country.NAME.NorthwestTerritory).printableName("NW Territory").armies(1).build();
        Country Ontario = Country.builder().name(Country.NAME.Ontario).printableName("Ontario").armies(1).build();
        Country Quebec = Country.builder().name(Country.NAME.Quebec).printableName("Quebec").armies(1).build();
        Country WesternUnitedStates = Country.builder().name(Country.NAME.WesternUnitedStates).printableName("Western US").armies(1).build();
        Country Argentina = Country.builder().name(Country.NAME.Argentina).printableName("Argentina").armies(1).build();
        Country Brazil = Country.builder().name(Country.NAME.Brazil).printableName("Brazil").armies(1).build();
        Country Peru = Country.builder().name(Country.NAME.Peru).printableName("Peru").armies(1).build();
        Country Venezuela = Country.builder().name(Country.NAME.Venezuela).printableName("Venezuela").armies(1).build();
        Country GreatBritain = Country.builder().name(Country.NAME.GreatBritain).printableName("Great Britain").armies(1).build();
        Country Iceland = Country.builder().name(Country.NAME.Iceland).printableName("Iceland").armies(1).build();
        Country NorthernEurope = Country.builder().name(Country.NAME.NorthernEurope).printableName("N Europe").armies(1).build();
        Country Scandinavia = Country.builder().name(Country.NAME.Scandinavia).printableName("Scandinavia").armies(1).build();
        Country SouthernEurope = Country.builder().name(Country.NAME.SouthernEurope).printableName("S Europe").armies(1).build();
        Country Ukraine = Country.builder().name(Country.NAME.Ukraine).printableName("Ukraine").armies(1).build();
        Country WesternEurope = Country.builder().name(Country.NAME.WesternEurope).printableName("W Europe").armies(1).build();
        Country Congo = Country.builder().name(Country.NAME.Congo).printableName("Congo").armies(1).build();
        Country EastAfrica = Country.builder().name(Country.NAME.EastAfrica).printableName("E Africa").armies(1).build();
        Country Egypt = Country.builder().name(Country.NAME.Egypt).printableName("Egypt").armies(1).build();
        Country Madagascar = Country.builder().name(Country.NAME.Madagascar).printableName("Madagascar").armies(1).build();
        Country NorthAfrica = Country.builder().name(Country.NAME.NorthAfrica).printableName("N Africa").armies(1).build();
        Country SouthAfrica = Country.builder().name(Country.NAME.SouthAfrica).printableName("S Africa").armies(1).build();
        Country Afghanistan = Country.builder().name(Country.NAME.Afghanistan).printableName("Afghanistan").armies(1).build();
        Country China = Country.builder().name(Country.NAME.China).printableName("China").armies(1).build();
        Country India = Country.builder().name(Country.NAME.India).printableName("India").armies(1).build();
        Country Irkutsk = Country.builder().name(Country.NAME.Irkutsk).printableName("Irkutsk").armies(1).build();
        Country Japan = Country.builder().name(Country.NAME.Japan).printableName("Japan").armies(1).build();
        Country Kamchatka = Country.builder().name(Country.NAME.Kamchatka).printableName("Kamchatka").armies(1).build();
        Country MiddleEast = Country.builder().name(Country.NAME.MiddleEast).printableName("Middle East").armies(1).build();
        Country Mongolia = Country.builder().name(Country.NAME.Mongolia).printableName("Mongolia").armies(1).build();
        Country Siam = Country.builder().name(Country.NAME.Siam).printableName("Siam").armies(1).build();
        Country Siberia = Country.builder().name(Country.NAME.Siberia).printableName("Siberia").armies(1).build();
        Country Ural = Country.builder().name(Country.NAME.Ural).printableName("Ural").armies(1).build();
        Country Yakutsk = Country.builder().name(Country.NAME.Yakutsk).printableName("Yakutsk").armies(1).build();
        Country EasternAustralia = Country.builder().name(Country.NAME.EasternAustralia).printableName("E Australia").armies(1).build();
        Country Indonesia = Country.builder().name(Country.NAME.Indonesia).printableName("Indonesia").armies(1).build();
        Country NewGuinea = Country.builder().name(Country.NAME.NewGuinea).printableName("New Guinea").armies(1).build();
        Country WesternAustralia = Country.builder().name(Country.NAME.WesternAustralia).printableName("W Australia").armies(1).build();

        //Add them to a list to be dealt to players
        List<Country> countries = new LinkedList<>();
        countries.add(Alaska);
        countries.add(Alberta);
        countries.add(CentralAmerica);
        countries.add(EasternUnitedStates);
        countries.add(Greenland);
        countries.add(NorthwestTerritory);
        countries.add(Ontario);
        countries.add(Quebec);
        countries.add(WesternUnitedStates);
        countries.add(Argentina);
        countries.add(Brazil);
        countries.add(Peru);
        countries.add(Venezuela);
        countries.add(GreatBritain);
        countries.add(Iceland);
        countries.add(NorthernEurope);
        countries.add(Scandinavia);
        countries.add(SouthernEurope);
        countries.add(Ukraine);
        countries.add(WesternEurope);
        countries.add(Congo);
        countries.add(EastAfrica);
        countries.add(Egypt);
        countries.add(Madagascar);
        countries.add(NorthAfrica);
        countries.add(SouthAfrica);
        countries.add(Afghanistan);
        countries.add(China);
        countries.add(India);
        countries.add(Irkutsk);
        countries.add(Japan);
        countries.add(Kamchatka);
        countries.add(MiddleEast);
        countries.add(Mongolia);
        countries.add(Siam);
        countries.add(Siberia);
        countries.add(Ural);
        countries.add(Yakutsk);
        countries.add(EasternAustralia);
        countries.add(Indonesia);
        countries.add(NewGuinea);
        countries.add(WesternAustralia);

        Collections.shuffle(countries);
        //for each country, assign the top player as the controller
        for (Country c : countries) {
            Player p = players.pop();
            c.setControlledBy(p);
            players.addLast(p);
        }
        //Take the top player and make him current
        this.currentPlayer = this.players.pop();
        this.players.addLast(this.currentPlayer);

        //Populate neighbors
        //Alaska
        Set<Country> neighbors = new LinkedHashSet<>();
        neighbors.add(Kamchatka);
        neighbors.add(Alberta);
        neighbors.add(NorthwestTerritory);
        Alaska.setNeighbors(neighbors);
        //Alberta
        neighbors = new LinkedHashSet<>();
        neighbors.add(Alaska);
        neighbors.add(NorthwestTerritory);
        neighbors.add(Ontario);
        neighbors.add(WesternUnitedStates);
        Alberta.setNeighbors(neighbors);
        //CentralAmerica
        neighbors = new LinkedHashSet<>();
        neighbors.add(EasternUnitedStates);
        neighbors.add(WesternUnitedStates);
        neighbors.add(Venezuela);
        CentralAmerica.setNeighbors(neighbors);
        //EasternUnitedStates
        neighbors = new LinkedHashSet<>();
        neighbors.add(CentralAmerica);
        neighbors.add(Ontario);
        neighbors.add(Quebec);
        neighbors.add(WesternUnitedStates);
        EasternUnitedStates.setNeighbors(neighbors);
        //Greenland
        neighbors = new LinkedHashSet<>();
        neighbors.add(NorthwestTerritory);
        neighbors.add(Ontario);
        neighbors.add(Quebec);
        neighbors.add(Iceland);
        Greenland.setNeighbors(neighbors);
        //NorthwestTerritory
        neighbors = new LinkedHashSet<>();
        neighbors.add(Alaska);
        neighbors.add(Alberta);
        neighbors.add(Greenland);
        neighbors.add(Ontario);
        NorthwestTerritory.setNeighbors(neighbors);
        //Ontario
        neighbors = new LinkedHashSet<>();
        neighbors.add(Alberta);
        neighbors.add(EasternUnitedStates);
        neighbors.add(Greenland);
        neighbors.add(NorthwestTerritory);
        neighbors.add(Quebec);
        neighbors.add(WesternUnitedStates);
        Ontario.setNeighbors(neighbors);
        //Quebec
        neighbors = new LinkedHashSet<>();
        neighbors.add(EasternUnitedStates);
        neighbors.add(Greenland);
        neighbors.add(Ontario);
        Quebec.setNeighbors(neighbors);
        //WesternUnitedStates
        neighbors = new LinkedHashSet<>();
        neighbors.add(Alberta);
        neighbors.add(CentralAmerica);
        neighbors.add(EasternUnitedStates);
        neighbors.add(Ontario);
        WesternUnitedStates.setNeighbors(neighbors);
        //Argentina
        neighbors = new LinkedHashSet<>();
        neighbors.add(Brazil);
        neighbors.add(Peru);
        Argentina.setNeighbors(neighbors);
        //Brazil
        neighbors = new LinkedHashSet<>();
        neighbors.add(Argentina);
        neighbors.add(Peru);
        neighbors.add(Venezuela);
        neighbors.add(NorthAfrica);
        Brazil.setNeighbors(neighbors);
        //Peru
        neighbors = new LinkedHashSet<>();
        neighbors.add(Argentina);
        neighbors.add(Brazil);
        neighbors.add(Venezuela);
        Peru.setNeighbors(neighbors);
        //Venezuela
        neighbors = new LinkedHashSet<>();
        neighbors.add(CentralAmerica);
        neighbors.add(Brazil);
        neighbors.add(Peru);
        Venezuela.setNeighbors(neighbors);
        //GreatBritain
        neighbors = new LinkedHashSet<>();
        neighbors.add(Iceland);
        neighbors.add(NorthernEurope);
        neighbors.add(Scandinavia);
        neighbors.add(WesternEurope);
        GreatBritain.setNeighbors(neighbors);
        //Iceland
        neighbors = new LinkedHashSet<>();
        neighbors.add(Greenland);
        neighbors.add(GreatBritain);
        neighbors.add(Scandinavia);
        Iceland.setNeighbors(neighbors);
        //NorthernEurope
        neighbors = new LinkedHashSet<>();
        neighbors.add(GreatBritain);
        neighbors.add(Scandinavia);
        neighbors.add(SouthernEurope);
        neighbors.add(Ukraine);
        neighbors.add(WesternEurope);
        NorthernEurope.setNeighbors(neighbors);
        //Scandinavia
        neighbors = new LinkedHashSet<>();
        neighbors.add(GreatBritain);
        neighbors.add(Iceland);
        neighbors.add(NorthernEurope);
        neighbors.add(Ukraine);
        Scandinavia.setNeighbors(neighbors);
        //SouthernEurope
        neighbors = new LinkedHashSet<>();
        neighbors.add(NorthernEurope);
        neighbors.add(Ukraine);
        neighbors.add(WesternEurope);
        neighbors.add(Egypt);
        neighbors.add(NorthAfrica);
        neighbors.add(MiddleEast);
        SouthernEurope.setNeighbors(neighbors);
        //Ukraine
        neighbors = new LinkedHashSet<>();
        neighbors.add(NorthernEurope);
        neighbors.add(Scandinavia);
        neighbors.add(SouthernEurope);
        neighbors.add(Afghanistan);
        neighbors.add(MiddleEast);
        neighbors.add(Ural);
        Ukraine.setNeighbors(neighbors);
        //WesternEurope
        neighbors = new LinkedHashSet<>();
        neighbors.add(GreatBritain);
        neighbors.add(NorthernEurope);
        neighbors.add(SouthernEurope);
        neighbors.add(NorthAfrica);
        WesternEurope.setNeighbors(neighbors);
        //Congo
        neighbors = new LinkedHashSet<>();
        neighbors.add(EastAfrica);
        neighbors.add(NorthAfrica);
        neighbors.add(SouthAfrica);
        Congo.setNeighbors(neighbors);
        //EastAfrica
        neighbors = new LinkedHashSet<>();
        neighbors.add(Congo);
        neighbors.add(Egypt);
        neighbors.add(Madagascar);
        neighbors.add(NorthAfrica);
        neighbors.add(SouthAfrica);
        neighbors.add(MiddleEast);
        EastAfrica.setNeighbors(neighbors);
        //Egypt
        neighbors = new LinkedHashSet<>();
        neighbors.add(SouthernEurope);
        neighbors.add(EastAfrica);
        neighbors.add(NorthAfrica);
        neighbors.add(MiddleEast);
        Egypt.setNeighbors(neighbors);
        //Madagascar
        neighbors = new LinkedHashSet<>();
        neighbors.add(EastAfrica);
        neighbors.add(SouthAfrica);
        Madagascar.setNeighbors(neighbors);
        //NorthAfrica
        neighbors = new LinkedHashSet<>();
        neighbors.add(Brazil);
        neighbors.add(SouthernEurope);
        neighbors.add(WesternEurope);
        neighbors.add(Congo);
        neighbors.add(EastAfrica);
        neighbors.add(Egypt);
        NorthAfrica.setNeighbors(neighbors);
        //SouthAfrica
        neighbors = new LinkedHashSet<>();
        neighbors.add(Congo);
        neighbors.add(EastAfrica);
        neighbors.add(Madagascar);
        SouthAfrica.setNeighbors(neighbors);
        //Afghanistan
        neighbors = new LinkedHashSet<>();
        neighbors.add(Ukraine);
        neighbors.add(China);
        neighbors.add(India);
        neighbors.add(MiddleEast);
        neighbors.add(Ural);
        Afghanistan.setNeighbors(neighbors);
        //China
        neighbors = new LinkedHashSet<>();
        neighbors.add(Afghanistan);
        neighbors.add(India);
        neighbors.add(Mongolia);
        neighbors.add(Siam);
        neighbors.add(Siberia);
        neighbors.add(Ural);
        China.setNeighbors(neighbors);
        //India
        neighbors = new LinkedHashSet<>();
        neighbors.add(Afghanistan);
        neighbors.add(China);
        neighbors.add(MiddleEast);
        neighbors.add(Siam);
        India.setNeighbors(neighbors);
        //Irkutsk
        neighbors = new LinkedHashSet<>();
        neighbors.add(Kamchatka);
        neighbors.add(Mongolia);
        neighbors.add(Siberia);
        neighbors.add(Yakutsk);
        Irkutsk.setNeighbors(neighbors);
        //Japan
        neighbors = new LinkedHashSet<>();
        neighbors.add(Kamchatka);
        neighbors.add(Mongolia);
        Japan.setNeighbors(neighbors);
        //Kamchatka
        neighbors = new LinkedHashSet<>();
        neighbors.add(Alaska);
        neighbors.add(Irkutsk);
        neighbors.add(Japan);
        neighbors.add(Mongolia);
        neighbors.add(Yakutsk);
        Kamchatka.setNeighbors(neighbors);
        //MiddleEast
        neighbors = new LinkedHashSet<>();
        neighbors.add(SouthernEurope);
        neighbors.add(Ukraine);
        neighbors.add(EastAfrica);
        neighbors.add(Egypt);
        neighbors.add(Afghanistan);
        neighbors.add(India);
        MiddleEast.setNeighbors(neighbors);
        //Mongolia
        neighbors = new LinkedHashSet<>();
        neighbors.add(China);
        neighbors.add(Irkutsk);
        neighbors.add(Japan);
        neighbors.add(Kamchatka);
        neighbors.add(Siberia);
        Mongolia.setNeighbors(neighbors);
        //Siam
        neighbors = new LinkedHashSet<>();
        neighbors.add(China);
        neighbors.add(India);
        neighbors.add(Indonesia);
        Siam.setNeighbors(neighbors);
        //Siberia
        neighbors = new LinkedHashSet<>();
        neighbors.add(China);
        neighbors.add(Irkutsk);
        neighbors.add(Mongolia);
        neighbors.add(Ural);
        neighbors.add(Yakutsk);
        Siberia.setNeighbors(neighbors);
        //Ural
        neighbors = new LinkedHashSet<>();
        neighbors.add(Ukraine);
        neighbors.add(Afghanistan);
        neighbors.add(China);
        neighbors.add(Siberia);
        Ural.setNeighbors(neighbors);
        //Yakutsk
        neighbors = new LinkedHashSet<>();
        neighbors.add(Irkutsk);
        neighbors.add(Kamchatka);
        neighbors.add(Siberia);
        Yakutsk.setNeighbors(neighbors);
        //EasternAustralia
        neighbors = new LinkedHashSet<>();
        neighbors.add(NewGuinea);
        neighbors.add(WesternAustralia);
        EasternAustralia.setNeighbors(neighbors);
        //Indonesia
        neighbors = new LinkedHashSet<>();
        neighbors.add(Siam);
        neighbors.add(NewGuinea);
        neighbors.add(WesternAustralia);
        Indonesia.setNeighbors(neighbors);
        //NewGuinea
        neighbors = new LinkedHashSet<>();
        neighbors.add(EasternAustralia);
        neighbors.add(Indonesia);
        neighbors.add(WesternAustralia);
        NewGuinea.setNeighbors(neighbors);
        //WesternAustralia
        neighbors = new LinkedHashSet<>();
        neighbors.add(EasternAustralia);
        neighbors.add(Indonesia);
        neighbors.add(NewGuinea);
        WesternAustralia.setNeighbors(neighbors);

        // Create Continents for game
        Continent NorthAmerica = Continent.builder().name(Continent.NAME.NorthAmerica).printableName("N America").build();
        Continent SouthAmerica = Continent.builder().name(Continent.NAME.SouthAmerica).printableName("S America").build();
        Continent Europe = Continent.builder().name(Continent.NAME.Europe).printableName("Europe").build();
        Continent Africa = Continent.builder().name(Continent.NAME.Africa).printableName("Africa").build();
        Continent Asia = Continent.builder().name(Continent.NAME.Asia).printableName("Asia").build();
        Continent Australia = Continent.builder().name(Continent.NAME.Australia).printableName("Australia").build();
        //Populate Continents
        // NorthAmerica
        Set<Country> countrySet = new LinkedHashSet<>();
        countrySet.add(Alaska);
        countrySet.add(Alberta);
        countrySet.add(CentralAmerica);
        countrySet.add(EasternUnitedStates);
        countrySet.add(Greenland);
        countrySet.add(NorthwestTerritory);
        countrySet.add(Ontario);
        countrySet.add(Quebec);
        countrySet.add(WesternUnitedStates);
        NorthAmerica.setCountries(countrySet);
        // SouthAmerica
        countrySet = new LinkedHashSet<>();
        countrySet.add(Argentina);
        countrySet.add(Brazil);
        countrySet.add(Peru);
        countrySet.add(Venezuela);
        SouthAmerica.setCountries(countrySet);
        // Europe
        countrySet = new LinkedHashSet<>();
        countrySet.add(GreatBritain);
        countrySet.add(Iceland);
        countrySet.add(NorthernEurope);
        countrySet.add(Scandinavia);
        countrySet.add(SouthernEurope);
        countrySet.add(Ukraine);
        countrySet.add(WesternEurope);
        Europe.setCountries(countrySet);
        // Africa
        countrySet = new LinkedHashSet<>();
        countrySet.add(Congo);
        countrySet.add(EastAfrica);
        countrySet.add(Egypt);
        countrySet.add(Madagascar);
        countrySet.add(NorthAfrica);
        countrySet.add(SouthAfrica);
        Africa.setCountries(countrySet);
        // Asia
        countrySet = new LinkedHashSet<>();
        countrySet.add(Afghanistan);
        countrySet.add(China);
        countrySet.add(India);
        countrySet.add(Irkutsk);
        countrySet.add(Japan);
        countrySet.add(Kamchatka);
        countrySet.add(MiddleEast);
        countrySet.add(Mongolia);
        countrySet.add(Siam);
        countrySet.add(Siberia);
        countrySet.add(Ural);
        countrySet.add(Yakutsk);
        Asia.setCountries(countrySet);
        // Australia
        countrySet = new LinkedHashSet<>();
        countrySet.add(EasternAustralia);
        countrySet.add(Indonesia);
        countrySet.add(NewGuinea);
        countrySet.add(WesternAustralia);
        Australia.setCountries(countrySet);

        //Save Continents
        this.continents = new LinkedHashSet<>();
        this.continents.add(NorthAmerica);
        this.continents.add(SouthAmerica);
        this.continents.add(Europe);
        this.continents.add(Africa);
        this.continents.add(Asia);
        this.continents.add(Australia);

        //Setup dice
        this.attackingDice = new ArrayList<>(3);
        this.defendingDice = new ArrayList<>(2);

        this.bonusArmies = 4;

        this.stage = STAGE.DISCARD;

        this.logs.add(GameLog.builder().game(this).message("New Game Started").build());
        this.logs.add(GameLog.builder().game(this).message(currentPlayer.getPlayerName() + " goes first").build());
    }

    public enum STAGE {
        DISCARD, ARMIES, ATTACK, DEFEND, MOVE, DRAW
    }
}
