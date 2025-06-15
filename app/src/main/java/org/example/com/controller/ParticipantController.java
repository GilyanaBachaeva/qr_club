package org.example.com.controller;

import lombok.RequiredArgsConstructor;
import org.example.com.dto.ParticipantDTO;
import org.example.com.exception.ParticipantNotFoundException;
import org.example.com.service.ParticipantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/participants")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    @GetMapping("/{uuid}")
    public ResponseEntity<ParticipantDTO> getParticipant(@PathVariable UUID uuid) {
        return participantService.getParticipantByUuid(uuid)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ParticipantNotFoundException("Participant not found with UUID: " + uuid));
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
