package org.example.com.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDTO {
    private Long id;
    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    private UUID uuid;

    public ParticipantDTO(Long id, String lastName, String firstName, LocalDate birthDate) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.uuid = UUID.randomUUID();
    }

    public ParticipantDTO(String lastName, String firstName, LocalDate birthDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.uuid = UUID.randomUUID();
    }
}
