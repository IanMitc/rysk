package com.revature.rysk.entities.Game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.rysk.entities.Player.Player;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id_generator")
    @SequenceGenerator(name = "country_id_generator", sequenceName = "country_id_sequence", allocationSize = 42)
    @JsonIgnore
    private long gameDbCountryId;

    @JsonIgnoreProperties({"playerAuthToken", "playerPassword"})
    @OneToOne
    @JoinColumn(name = "controlled_by_player_id")
    private Player controlledBy;

    @Enumerated
    private NAME name;

    private long countryId;
    private String printableName;
    private int armies;

    public void addArmies(int numberOfArmies) {
        this.armies += numberOfArmies;
    }

    public void subtractArmies(int numberOfArmies) {
        this.armies -= numberOfArmies;
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
