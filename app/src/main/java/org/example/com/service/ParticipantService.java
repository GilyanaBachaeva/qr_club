package org.example.com.service;

import lombok.RequiredArgsConstructor;
import org.example.com.dto.ParticipantDTO;
import org.example.com.exception.ParticipantNotFoundException;
import org.example.com.mapper.ParticipantMapper;
import org.example.com.model.ParticipantEntity;
import org.example.com.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;

    public Optional<ParticipantDTO> getParticipantByUuid(UUID uuid) {
        Optional<ParticipantEntity> participant = participantRepository.findByUuid(uuid);
        return participant.map(participantMapper::toDTO)

                .or(() -> {
                    throw new ParticipantNotFoundException("Participant not found with UUID: " + uuid);
                });
    }

    public ParticipantDTO checkQrCode(UUID uuid) {
        ParticipantEntity participantEntity = participantRepository.findByUuid(uuid)
                .orElseThrow(() -> new ParticipantNotFoundException("Participant not found with UUID: " + uuid));

        UUID newUuid = UUID.randomUUID();
        participantEntity.setUuid(newUuid);
        participantRepository.save(participantEntity);

        return participantMapper.toDTO(participantEntity);
    }

    public ParticipantDTO save(ParticipantDTO participantDTO) {
        ParticipantEntity participantEntity = participantMapper.toEntity(participantDTO);
        ParticipantEntity savedParticipantEntity = participantRepository.save(participantEntity);
        return participantMapper.toDTO(savedParticipantEntity);
    }

    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }

    public UUID regenerateUuid(ParticipantEntity participantEntity) {
        UUID newUuid = UUID.randomUUID();
        participantEntity.setUuid(newUuid);
        participantRepository.save(participantEntity);
        return newUuid;
    }
}
