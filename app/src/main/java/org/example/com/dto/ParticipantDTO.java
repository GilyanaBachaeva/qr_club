package org.example.com.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class ParticipantDTO {
    private Long id;
    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    private UUID uuid;

    public ParticipantDTO (Long id, String lastName, String firstName, LocalDate birthDate, UUID uuid) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.uuid = uuid;
    }
}
