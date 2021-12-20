package com.revature.rysk.repositories;

import com.revature.rysk.entities.Player.Password;
import com.revature.rysk.entities.Player.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class PlayerRepositoryTest {
    @Autowired
    PlayerRepository playerRepository;

    @Test
    void getPlayerByEmail() {
        for (int i = 0; i < 10; i++) {
            playerRepository.save(Player.builder()
                    .playerName("Player" + i)
                    .playerEmail(i + "@test.com")
                    .playerPassword(Password.builder()
                            .password("password")
                            .build())
                    .build());
        }

        for (int i = 0; i < 10; i++) {
            Optional<Player> playerOptional = playerRepository.getPlayerByPlayerEmail(i + "@test.com");
            Assertions.assertTrue(playerOptional.isPresent());

            Player player;
            player = playerOptional.get();
            Assertions.assertEquals(player.getPlayerName(), "Player" + i);
        }
        Optional<Player> playerOptional = playerRepository.getPlayerByPlayerEmail("notARealEmail@test.com");
        Assertions.assertTrue(playerOptional.isEmpty());
    }
}