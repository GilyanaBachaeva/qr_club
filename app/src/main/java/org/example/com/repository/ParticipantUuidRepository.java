package org.example.com.repository;

import org.example.com.model.ParticipantUuidEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParticipantUuidRepository extends JpaRepository<ParticipantUuidEntity, Long> {
    ParticipantUuidEntity findByUuid(UUID uuid);
}
