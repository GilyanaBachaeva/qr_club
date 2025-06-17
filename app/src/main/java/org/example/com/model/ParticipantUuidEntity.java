package org.example.com.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "participant_uuids")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantUuidEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "expiration_time")
    private LocalDateTime expirationTime;

    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)

    private ParticipantEntity participant;
}
