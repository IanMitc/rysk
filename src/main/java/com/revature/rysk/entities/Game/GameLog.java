package com.revature.rysk.entities.Game;

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
    private long logId;

    @Setter(AccessLevel.NONE)
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "game_game_id")
    private Game game;
    private String message;
}
