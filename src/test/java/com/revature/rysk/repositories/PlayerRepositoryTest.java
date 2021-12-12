package com.revature.rysk.repositories;

import com.revature.rysk.entities.AuthToken;
import com.revature.rysk.entities.Password;
import com.revature.rysk.entities.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PlayerRepositoryTest {
    @Autowired
    PlayerRepository playerRepository;

    @Test
    void savePlayer() {
        Player player = Player.builder()
                .playerEmail("test@example.com")
                .playerName("Testy McTestface")
                .build();

        player.setAuthToken(new AuthToken());
        player.setPlayerPassword(new Password(1,"Password"));

        playerRepository.save(player);
    }

    @Test
    void getPlayerByEmail() {
    }
}