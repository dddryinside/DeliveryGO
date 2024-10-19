package com.chernikov.DeliveryGO.service;

import com.chernikov.DeliveryGO.entities.SupportMessage;
import com.chernikov.DeliveryGO.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupportService {
    public final MessageRepository messageRepository;
    public final UserService userService;


    public SupportMessage saveMessage(String message) {
        SupportMessage supportMessage = new SupportMessage();
        supportMessage.setUser(userService.getUser());
        supportMessage.setContent(message);
        supportMessage.setDateTime(LocalDateTime.now());
        return messageRepository.save(supportMessage);
    }


    public List<SupportMessage> getSupportMessages(String page) {
        return messageRepository.findAllByUser(
                userService.getUser(), PageRequest.of(Integer.parseInt(page), 10));
    }


    public void deleteMessage(Long messageId) {
        Optional<SupportMessage> messageOptional = messageRepository.findById(messageId);
        if (messageOptional.isPresent()) {
            SupportMessage message = messageOptional.get();
            if (Objects.equals(message.getUser().getId(), userService.getUser().getId())) {
                messageRepository.delete(message);
            }  else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
