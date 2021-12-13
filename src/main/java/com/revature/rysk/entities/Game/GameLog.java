package com.revature.rysk.entities.Game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GameLog {
    @Id
    @GeneratedValue
    private long logId;

    @JsonIgnoreProperties({"players","currentPlayer","attackingPlayer","deck","logs","countries","attackingDice","defendingDice","bonusArmies","stage"})
    @ManyToOne(cascade = CascadeType.ALL)
    private Game game;

    private String message;
}
