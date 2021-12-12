package com.revature.rysk.entities.Game;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Continent {
    @Id
    @GeneratedValue
    private long continentId;
    private NAME name;
    private String printableName;

    @OneToMany(mappedBy = "continent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Country> countries = new java.util.LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "game_game_id")
    private Game game;

    public enum NAME {
        NorthAmerica,
        SouthAmerica,
        Europe,
        Africa,
        Asia,
        Australia
    }
}

