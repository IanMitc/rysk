package com.revature.rysk.repositories;

import com.revature.rysk.entities.Game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    @Query(
            value = "SELECT game_null FROM game_players WHERE players_player_id = ?1",
            nativeQuery = true)
    List<Long> getGamesByPlayerId(long playerId);
}
