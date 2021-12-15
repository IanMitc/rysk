package com.revature.rysk.repositories;

import com.revature.rysk.entities.Game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findGamesByPlayers_PlayerId(long playerId);
}
