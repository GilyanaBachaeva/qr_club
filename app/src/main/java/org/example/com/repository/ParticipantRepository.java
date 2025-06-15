package org.example.com.repository;

import org.example.com.model.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
    Optional<ParticipantEntity> findByUuid(UUID uuid);
}
