package org.example.projektuno.repositories;

import org.example.projektuno.entity.UserTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTeamRepository extends JpaRepository<UserTeam, Long> {
    Optional<UserTeam> findByUserId(Long userId);
    
    @Query("SELECT ut FROM UserTeam ut JOIN FETCH ut.league WHERE ut.id = :teamId")
    Optional<UserTeam> findByIdWithLeague(Long teamId);
}
