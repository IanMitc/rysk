package com.revature.rysk.repositories;

import com.revature.rysk.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player getPlayerByEmail(String email);
}
