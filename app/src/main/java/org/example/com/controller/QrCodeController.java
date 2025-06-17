package org.example.com.controller;

import lombok.RequiredArgsConstructor;
import org.example.com.model.ParticipantUuidEntity;
import org.example.com.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/qrcode")
public class QrCodeController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping("/{uuid}")
    public ResponseEntity<ParticipantUuidEntity> checkQrCode(@PathVariable UUID uuid) {
        ParticipantUuidEntity participant = participantService.checkQrCode(uuid);
        return ResponseEntity.ok(participant);
    }
}
