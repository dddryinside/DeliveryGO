package com.chernikov.DeliveryGO.service;

import com.chernikov.DeliveryGO.entities.SupportMessage;
import com.chernikov.DeliveryGO.entities.User;
import com.chernikov.DeliveryGO.repository.MessageRepository;
import com.chernikov.DeliveryGO.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupportService {
    public final MessageRepository messageRepository;
    public final UserRepository userRepository;


    public SupportMessage saveMessage(User user, String message) {
        SupportMessage supportMessage = new SupportMessage();

        supportMessage.setUser(user);
        supportMessage.setContent(message);
        supportMessage.setDateTime(LocalDateTime.now());

        return messageRepository.save(supportMessage);
    }


    public List<SupportMessage> getSupportMessagesByUser(Long userId, String page) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                return messageRepository.findAllByUser(
                        userOptional.get(), PageRequest.of(Integer.parseInt(page), 10));
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


    public void deleteMessage(Long messageId) {
        Optional<SupportMessage> messageOptional = messageRepository.findById(messageId);
        if (messageOptional.isPresent()) {
            messageRepository.delete(messageOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
