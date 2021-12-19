package com.revature.rysk.repositories;

import com.revature.rysk.entities.Game.Game;
import com.revature.rysk.entities.Player.Password;
import com.revature.rysk.entities.Player.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
class GameRepositoryTest {
    @Autowired
    GameRepository gameRepository;
    @Autowired
    PlayerRepository playerRepository;

    @Test
    void findGamesByPlayers_PlayerId() {
        for (int i = 0; i < 10; i++) {
            Game game = new Game();
            List<Player> playerList = new ArrayList<>();

            for (int j = i; j < 10; j += 2) {
                playerList.add(playerRepository.getById((long) (j + 1)));
            }

            if (playerList.size() > 1) {
                game.newGame(playerList);
                gameRepository.save(game);
            }
        }

        List<Game> expected = new ArrayList<>(List.of(gameRepository.getById(1L)));
        List<Game> actual = gameRepository.findGamesByPlayers_PlayerId(1L);
        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());

        expected = new ArrayList<>(List.of(gameRepository.getById(2L)));
        actual = gameRepository.findGamesByPlayers_PlayerId(2L);
        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());

        expected = new ArrayList<>(List.of(gameRepository.getById(1L), gameRepository.getById(3L)));
        actual = gameRepository.findGamesByPlayers_PlayerId(3L);
        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());

        expected = new ArrayList<>(List.of(gameRepository.getById(2L), gameRepository.getById(4L)));
        actual = gameRepository.findGamesByPlayers_PlayerId(4L);
        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());

        expected = new ArrayList<>(List.of(gameRepository.getById(1L), gameRepository.getById(3L), gameRepository.getById(5L)));
        actual = gameRepository.findGamesByPlayers_PlayerId(5L);
        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
    }
}