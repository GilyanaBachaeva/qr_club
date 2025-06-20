package org.example.com.controller;

import lombok.RequiredArgsConstructor;
import org.example.com.dto.ParticipantDTO;
import org.example.com.service.ParticipantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/qrcode")
public class QrCodeController {

    final ParticipantService participantService;

    @GetMapping("/{uuid}")
    public ResponseEntity<ParticipantDTO> checkQrCode(@PathVariable UUID uuid) {
        ParticipantDTO participant = participantService.checkQrCode(uuid);
        return ResponseEntity.ok(participant);
    }
}
