package org.example.com.mapper;

import org.example.com.dto.ParticipantDTO;
import org.example.com.model.ParticipantEntity;
import org.example.com.model.ParticipantUuidEntity;
import org.springframework.stereotype.Component;

@Component
public class ParticipantMapper {

    public ParticipantEntity toEntity(ParticipantDTO participantDTO) {
        return new ParticipantEntity(
                participantDTO.getId(),
                participantDTO.getLastName(),
                participantDTO.getFirstName(),
                participantDTO.getBirthDate()
        );
    }

    public ParticipantDTO toDTO(ParticipantEntity participantEntity) {
        return new ParticipantDTO(
                participantEntity.getId(),
                participantEntity.getLastName(),
                participantEntity.getFirstName(),
                participantEntity.getBirthDate()
        );
    }

    public ParticipantDTO toDTO(ParticipantUuidEntity participantUuidEntity) {
        ParticipantEntity participantEntity = participantUuidEntity.getParticipant();
        return new ParticipantDTO(
                participantEntity.getId(),
                participantEntity.getLastName(),
                participantEntity.getFirstName(),
                participantEntity.getBirthDate()
        );
    }
}
