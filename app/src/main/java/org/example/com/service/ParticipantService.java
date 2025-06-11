package org.example.com.service;

import org.example.com.dto.ParticipantDTO;
import org.example.com.exception.ParticipantNotFoundException;
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

    public Optional<ParticipantDTO> getParticipantByUuid(UUID uuid) {
        Optional<Participant> participant = participantRepository.findByUuid(uuid);
        return participant.map(this::convertToDTO)
                .or(() -> {
                    throw new ParticipantNotFoundException("Participant not found with UUID: " + uuid);
                });
    }

    public ParticipantDTO save(ParticipantDTO participantDTO) {
        Participant participant = convertToEntity(participantDTO);
        Participant savedParticipant = participantRepository.save(participant);
        return convertToDTO(savedParticipant);
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

    private Participant convertToEntity(ParticipantDTO participantDTO) {
        return new Participant(
                participantDTO.getId(),
                participantDTO.getLastName(),
                participantDTO.getFirstName(),
                participantDTO.getBirthDate(),
                participantDTO.getUuid()
        );
    }

    private ParticipantDTO convertToDTO(Participant participant) {
        return new ParticipantDTO(
                participant.getId(),
                participant.getLastName(),
                participant.getFirstName(),
                participant.getBirthDate(),
                participant.getUuid()
        );
    }
}
