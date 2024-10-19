package com.chernikov.DeliveryGO.controllers;

import com.chernikov.DeliveryGO.entities.SupportMessage;
import com.chernikov.DeliveryGO.service.SupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SupportController {
    private final SupportService supportService;


    @PostMapping(value = {"/api/send-support-message"})
    public ResponseEntity<SupportMessage> sendSupportMessage(@RequestParam String message) {
        return new ResponseEntity<>(
                supportService.saveMessage(message), HttpStatus.OK);
    }


    @GetMapping(value = {"/api/get-support-messages"})
    public ResponseEntity<List<SupportMessage>> getSupportMessages(@RequestParam String page) {
        return new ResponseEntity<>(
                supportService.getSupportMessages(page), HttpStatus.OK);
    }


    @DeleteMapping(value = {"/api/delete-support-message"})
    public ResponseEntity<HttpStatus> deleteSupportMessage(@RequestParam Long messageId) {
        supportService.deleteMessage(messageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
