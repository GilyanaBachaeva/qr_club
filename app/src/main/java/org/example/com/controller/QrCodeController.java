package org.example.com.controller;

import org.example.com.dto.ParticipantDTO;
import org.example.com.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/qrcode")
public class QrCodeController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping("/{uuid}")
    public ResponseEntity<ParticipantDTO> checkQrCode(@PathVariable UUID uuid) {
        ParticipantDTO participant = participantService.checkQrCode(uuid);
        return ResponseEntity.ok(participant);
    }
}
