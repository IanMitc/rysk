package com.revature.rysk.dto;

import com.revature.rysk.entities.Player.AuthToken;
import com.revature.rysk.entities.Player.Player;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class PlayerWithAuthTokenDto {
    private long playerId;
    private String playerEmail;
    private String playerName;
    private String playerAuthToken;

    public static Player getPlayer(PlayerWithAuthTokenDto dto) {
        return Player.builder()
                .playerId(dto.getPlayerId())
                .playerEmail(dto.getPlayerEmail())
                .playerName(dto.getPlayerName())
                .playerAuthToken(AuthToken.builder()
                        .authToken(dto.getPlayerAuthToken())
                        .build())
                .build();
    }

    public static PlayerWithAuthTokenDto getDto(Player player) {
        return PlayerWithAuthTokenDto.builder()
                .playerId(player.getPlayerId())
                .playerEmail(player.getPlayerEmail())
                .playerName(player.getPlayerName())
                .playerAuthToken(player.getPlayerAuthToken().getAuthToken())
                .build();
    }

    public static List<Player> getPlayers(List<PlayerWithAuthTokenDto> dtoList) {
        List<Player> players = new ArrayList<>();
        for (PlayerWithAuthTokenDto dto : dtoList) {
            players.add(getPlayer(dto));
        }
        return players;
    }
}
