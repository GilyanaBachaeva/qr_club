package org.example.com.service;

import lombok.RequiredArgsConstructor;
import org.example.com.dto.ParticipantDTO;
import org.example.com.exception.ParticipantNotFoundException;
import org.example.com.mapper.ParticipantMapper;
import org.example.com.model.ParticipantEntity;
import org.example.com.model.ParticipantUuidEntity;
import org.example.com.repository.ParticipantRepository;
import org.example.com.repository.ParticipantUuidRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final ParticipantUuidRepository participantUuidRepository;
    private final ParticipantMapper participantMapper;

    public ParticipantDTO getParticipantByUuid(UUID uuid) {
        ParticipantUuidEntity participantUuidEntity = participantUuidRepository.findByUuid(uuid);
        if (participantUuidEntity == null) {
            throw new ParticipantNotFoundException("Participant not found with UUID: " + uuid);
        }
        ParticipantEntity participantEntity = participantUuidEntity.getParticipant();
        regenerateUuid(participantUuidEntity);
        return participantMapper.toDTO(participantEntity);
    }

    public ParticipantUuidEntity checkQrCode(UUID uuid) {
        ParticipantUuidEntity participantUuidEntity = participantUuidRepository.findByUuid(uuid);
        if (participantUuidEntity == null) {
            throw new ParticipantNotFoundException("Participant not found with UUID: " + uuid);
        }

        regenerateUuid(participantUuidEntity);
        return participantUuidEntity;
    }

    public ParticipantDTO save(ParticipantDTO participantDTO) {
        ParticipantEntity participantEntity = participantMapper.toEntity(participantDTO);
        ParticipantEntity savedParticipantEntity = participantRepository.save(participantEntity);
        ParticipantUuidEntity participantUuidEntity = new ParticipantUuidEntity();
        participantUuidEntity.setUuid(UUID.randomUUID());
        participantUuidEntity.setParticipant(savedParticipantEntity);
        participantUuidRepository.save(participantUuidEntity);
        final var dto = participantMapper.toDTO(savedParticipantEntity);
        return dto;
    }

    public void deleteParticipant(Long id) {
        if (!participantRepository.existsById(id)) {
            throw new ParticipantNotFoundException("Participant not found with ID: " + id);
        }
        participantRepository.deleteById(id);
    }

    public UUID regenerateUuid(ParticipantUuidEntity participantUuidEntity) {
        if (participantUuidEntity == null) {
            throw new IllegalArgumentException("ParticipantUuidEntity cannot be null");
        }

        UUID newUuid = UUID.randomUUID();
        participantUuidEntity.setUuid(newUuid);
        participantUuidRepository.save(participantUuidEntity);
        return newUuid;
    }
}
