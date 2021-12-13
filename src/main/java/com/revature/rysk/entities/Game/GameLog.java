package com.revature.rysk.entities.Game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GameLog {
    @Id
    @GeneratedValue
    @JsonIgnore
    private long logId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Game game;

    private String message;
}
