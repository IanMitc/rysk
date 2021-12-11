package com.revature.rysk.repositories;

import com.revature.rysk.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> getPlayerByPlayerEmail(String email);
}
