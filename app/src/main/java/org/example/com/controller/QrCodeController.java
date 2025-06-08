package org.example.com.controller;

import org.example.com.model.Participant;
import org.example.com.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/qrcode")
public class QrCodeController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping("/{uuid}")
    public ResponseEntity<String> checkQrCode(@PathVariable UUID uuid) {
        Optional<Participant> participant = participantService.getParticipantByUuid(uuid);
        if (participant.isPresent()) {
            // Генерация нового UUID
            UUID newUuid = participantService.regenerateUuid(participant.get());
            return ResponseEntity.ok("Участник найден: " + participant.get().getFirstName() + " " + participant.get().getLastName() +
                    ". Новый UUID: " + newUuid);
        } else {
            return ResponseEntity.status(404).body("Участник не найден.");
        }
    }
}

