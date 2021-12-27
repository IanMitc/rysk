package com.revature.rysk.dto;


import com.revature.rysk.entities.Game.Country;
import com.revature.rysk.entities.Game.Game;
import com.revature.rysk.entities.Game.GameLog;
import com.revature.rysk.entities.Game.Hand;
import com.revature.rysk.entities.Player.Player;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class GameDto {
    private long gameId;
    private List<PlayerForDisplayDto> players;
    private PlayerForDisplayDto currentPlayer;
    private PlayerForDisplayDto attackingPlayer;
    private Country attackingCountry;
    private Country defendingCountry;
    private Hand playersCards;
    private List<GameLogDto> logs;
    private List<CountryDto> countries;
    private int attackingDice1;
    private int attackingDice2;
    private int attackingDice3;
    private int defendingDice1;
    private int defendingDice2;
    private int armiesToPlay;
    private boolean playerWon;
    private int bonusArmies;
    private Game.STAGE stage;

    public static GameDto getDto(Game game, Player player) {
        GameDto dto = GameDto.builder()
                .gameId(game.getGameId())
                .armiesToPlay(game.getArmiesToPlay())
                .playerWon(game.isPlayerWon())
                .bonusArmies(game.getBonusArmies())
                .stage(game.getStage())
                .build();

        List<PlayerForDisplayDto> playersList = new ArrayList<>();

        for (Player p : game.getPlayers()) {
            playersList.add(PlayerForDisplayDto.getDto(p));
            if (p.getPlayerEmail().equals(player.getPlayerEmail())) {
                dto.setPlayersCards(game.getPlayersHand(p));
            }
        }
        dto.setPlayers(playersList);

        dto.setCurrentPlayer(PlayerForDisplayDto.getDto(game.getCurrentPlayer()));

        if (game.getAttackingPlayer() != null) {
            dto.setAttackingPlayer(PlayerForDisplayDto.getDto(game.getAttackingPlayer()));
            dto.setAttackingCountry(game.getAttackingCountry());
            dto.setDefendingCountry(game.getDefendingCountry());
            dto.setAttackingDice1(game.getAttackingDice1() + 1);
            dto.setAttackingDice2(game.getAttackingDice2() + 1);
            dto.setAttackingDice3(game.getAttackingDice3() + 1);
            dto.setDefendingDice1(game.getDefendingDice1() + 1);
            dto.setDefendingDice2(game.getDefendingDice2() + 1);
        }
        List<CountryDto> countriesDto = new ArrayList<>();

        for (Country c : game.getCountries()) {
            countriesDto.add(CountryDto.getDTO(c));
        }

        dto.setCountries(countriesDto);

        List<GameLog> gameLogs = game.getLogs();
        List<GameLogDto> gameLogDtos = new ArrayList<>();
        for (GameLog g : gameLogs) {
            gameLogDtos.add(GameLogDto.getDto(g));
        }
        dto.setLogs(gameLogDtos);

        return dto;
    }
}
