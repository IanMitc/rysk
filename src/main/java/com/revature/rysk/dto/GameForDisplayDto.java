package com.revature.rysk.dto;

import com.revature.rysk.entities.Game.Game;
import com.revature.rysk.entities.Player.Player;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class GameForDisplayDto {
    private long gameId;
    private List<PlayerForDisplayDto> players;
    private PlayerForDisplayDto currentPlayer;
    private boolean playerWon;
    private Game.STAGE stage;

    public static GameForDisplayDto getDto(Game game) {
        GameForDisplayDto dto = GameForDisplayDto.builder()
                .gameId(game.getGameId())
                .playerWon(game.isPlayerWon())
                .stage(game.getStage())
                .build();

        List<PlayerForDisplayDto> playersList = new ArrayList<>();

        for (Player p : game.getPlayers()) {
            playersList.add(PlayerForDisplayDto.getDto(p));
        }
        dto.setPlayers(playersList);

        dto.setCurrentPlayer(PlayerForDisplayDto.getDto(game.getCurrentPlayer()));

        return dto;
    }
}
