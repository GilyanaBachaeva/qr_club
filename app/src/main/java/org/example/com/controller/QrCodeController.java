package org.example.com.controller;

import org.example.com.dto.ParticipantDTO;
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

        Optional<ParticipantDTO> participant = participantService.getParticipantByUuid(uuid);

        String responseMessage = String.format("Участник найден: %s %s.",
                participant.get().getFirstName(), participant.get().getLastName());

        return ResponseEntity.ok(responseMessage);
    }
}
