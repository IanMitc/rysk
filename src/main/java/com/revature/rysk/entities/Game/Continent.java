package com.revature.rysk.entities.Game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Continent {
    @Id
    @GeneratedValue
    private long continentId;
    private NAME name;
    private String printableName;

    @OneToMany(mappedBy = "continent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Country> countries = new java.util.LinkedHashSet<>();

    public enum NAME {
        NorthAmerica,
        SouthAmerica,
        Europe,
        Africa,
        Asia,
        Australia
    }
}

