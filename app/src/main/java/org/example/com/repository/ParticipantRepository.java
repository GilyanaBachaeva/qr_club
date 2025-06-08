package org.example.com.repository;

import org.example.com.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    Optional<Participant> findByUuid(UUID uuid);
}
