package org.example.com.service;

import org.example.com.model.Participant;
import org.example.com.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    public Optional<Participant> getParticipantByUuid(UUID uuid) {
        return participantRepository.findByUuid(uuid);
    }

    public Participant addOrUpdateParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }

    public UUID regenerateUuid(Participant participant) {
        UUID newUuid = UUID.randomUUID();
        participant.setUuid(newUuid);
        participantRepository.save(participant);
        return newUuid;
    }
}
