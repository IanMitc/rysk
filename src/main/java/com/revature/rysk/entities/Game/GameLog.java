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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_log_id_generator")
    @SequenceGenerator(name = "game_log_id_generator", sequenceName = "game_log_id_sequence")
    private long logId;

    private String message;
}
