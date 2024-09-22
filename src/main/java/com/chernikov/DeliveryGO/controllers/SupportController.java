package com.chernikov.DeliveryGO.controllers;

import com.chernikov.DeliveryGO.entities.SupportMessage;
import com.chernikov.DeliveryGO.service.SupportService;
import com.chernikov.DeliveryGO.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SupportController {
    private final UserService userService;
    private final SupportService supportService;


    @PostMapping(value = {"/api/send-support-message"})
    public ResponseEntity<SupportMessage> sendSupportMessage(@ModelAttribute String message) {
        return new ResponseEntity<>(
                supportService.saveMessage(userService.getUser(), message), HttpStatus.OK);
    }


    @GetMapping(value = {"/api/get-support-messages-by-user"})
    public ResponseEntity<List<SupportMessage>> getSupportMessagesByUser(@ModelAttribute Long userId, @ModelAttribute String page) {
        return new ResponseEntity<>(
                supportService.getSupportMessagesByUser(userId, page), HttpStatus.OK);
    }


    @PostMapping(value = {"/api/delete-support-message"})
    public ResponseEntity<HttpStatus> deleteSupportMessage(@ModelAttribute Long messageId) {
        supportService.deleteMessage(messageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
