package org.example.com.controller;

import org.example.com.model.Participant;
import org.example.com.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping("/{uuid}")
    public ResponseEntity<Participant> getParticipant(@PathVariable UUID uuid) {
        Optional<Participant> participant = participantService.getParticipantByUuid(uuid);
        if (participant.isPresent()) {
            UUID newUuid = participantService.regenerateUuid(participant.get());
            return ResponseEntity.ok(participant.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Participant> addParticipant(@RequestBody Participant participant) {
        Participant savedParticipant = participantService.addOrUpdateParticipant(participant);
        return ResponseEntity.ok(savedParticipant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {

        participantService.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }
}
