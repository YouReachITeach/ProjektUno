package org.example.projektuno.repositories;

import org.example.projektuno.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    // 🔍 Alle Spieler, die in keinem UserTeam sind
    @Query("SELECT p FROM Player p WHERE p.id NOT IN (SELECT pl.id FROM UserTeam t JOIN t.players pl)")
    List<Player> findPlayersNotInAnyTeam();
}
