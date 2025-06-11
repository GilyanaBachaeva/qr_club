package org.example.com.controller;

import org.example.com.dto.ParticipantDTO;
import org.example.com.service.ParticipantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Optional<ParticipantDTO>> getParticipant(@PathVariable UUID uuid) {
        Optional<ParticipantDTO> participantDTO = participantService.getParticipantByUuid(uuid);
        return ResponseEntity.ok(participantDTO);
    }

    @PostMapping
    public ResponseEntity<ParticipantDTO> addParticipant(@RequestBody ParticipantDTO participantDTO) {
        ParticipantDTO savedParticipantDTO = participantService.save(participantDTO);
        return ResponseEntity.ok(savedParticipantDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }
}
