package com.revature.rysk.dto;

import com.revature.rysk.entities.Player.Player;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlayerForDisplayDto {
    private long playerId;
    private String playerEmail;
    private String playerName;

    public static PlayerForDisplayDto getDto(Player player) {
        return PlayerForDisplayDto.builder()
                .playerId(player.getPlayerId())
                .playerEmail(player.getPlayerEmail())
                .playerName(player.getPlayerName())
                .build();
    }

    public static Player getPlayer(PlayerForDisplayDto dto) {
        return Player.builder()
                .playerId(dto.getPlayerId())
                .playerEmail(dto.getPlayerEmail())
                .playerName(dto.getPlayerName())
                .build();
    }
}
