package com.revature.rysk.dto;

import com.revature.rysk.entities.Player.AuthToken;
import com.revature.rysk.entities.Player.Password;
import com.revature.rysk.entities.Player.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerWithPasswordDto {
    private long playerId;
    private String playerEmail;
    private String playerName;
    private String playerAuthToken;
    private String playerPassword;

    public static Player getPlayer(PlayerWithPasswordDto dto) {
        Player player = Player.builder()
                .playerId(dto.getPlayerId())
                .playerEmail(dto.getPlayerEmail())
                .playerName(dto.getPlayerName())
                .build();
        if(dto.playerAuthToken != null) {
            player.setPlayerAuthToken(AuthToken.builder().authToken(dto.getPlayerAuthToken()).build());
        }

        if(dto.playerPassword != null){
            player.setPlayerPassword(Password.builder().password(dto.getPlayerPassword()).build());
        }

        return player;
    }
}
