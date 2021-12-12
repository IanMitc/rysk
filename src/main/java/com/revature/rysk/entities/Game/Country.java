package com.revature.rysk.entities.Game;

import com.revature.rysk.entities.Player.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Country {
    @Id
    @GeneratedValue
    private long countryId;
    private NAME name;
    private String printableName;
    private int armies;

    @ManyToMany
    @JoinTable(name = "country_neighbors",
            joinColumns = @JoinColumn(name = "country_1_null", referencedColumnName = "countryId"),
            inverseJoinColumns = @JoinColumn(name = "neighbors_2_country_id", referencedColumnName = "countryId"))
    private Set<Country> neighbors = new java.util.LinkedHashSet<>();

    @OneToOne
    @JoinColumn(name = "controlled_by_player_id")
    private Player controlledBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "continent_continent_id")
    private Continent continent;

    public Country() {
        this.armies = 1;
    }

    public enum NAME {
        Alaska,
        Alberta,
        CentralAmerica,
        EasternUnitedStates,
        Greenland,
        NorthwestTerritory,
        Ontario,
        Quebec,
        WesternUnitedStates,
        Argentina,
        Brazil,
        Peru,
        Venezuela,
        GreatBritain,
        Iceland,
        NorthernEurope,
        Scandinavia,
        SouthernEurope,
        Ukraine,
        WesternEurope,
        Congo,
        EastAfrica,
        Egypt,
        Madagascar,
        NorthAfrica,
        SouthAfrica,
        Afghanistan,
        China,
        India,
        Irkutsk,
        Japan,
        Kamchatka,
        MiddleEast,
        Mongolia,
        Siam,
        Siberia,
        Ural,
        Yakutsk,
        EasternAustralia,
        Indonesia,
        NewGuinea,
        WesternAustralia,
    }
}
