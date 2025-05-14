package org.example.projektuno.repositories;

import org.example.projektuno.entity.UserTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTeamRepository extends JpaRepository<UserTeam, Long> {
    Optional<UserTeam> findByUserId(Long userId);
}
