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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_log_id_generator")
    @SequenceGenerator(name = "game_log_id_generator", sequenceName = "game_log_id_sequence")
    @JsonIgnore
    private long logId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Game game;

    private String message;
}
