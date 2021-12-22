package com.revature.rysk.dto;

import com.revature.rysk.entities.Game.GameLog;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GameLogDto {
    private String message;

    public static GameLogDto getDto(GameLog log) {
        return GameLogDto.builder().message(log.getMessage()).build();
    }
}
