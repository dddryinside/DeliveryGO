package com.chernikov.DeliveryGO.service;

import com.chernikov.DeliveryGO.enums.ROLE;
import com.chernikov.DeliveryGO.repository.UserRepository;
import com.chernikov.DeliveryGO.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User saveUser(User user) {
        if (userRepository.count() == 0) {
            user.setRole(ROLE.DIRECTOR);
        }
        return userRepository.save(user);
    }

    public User getUser() {
        return null;
    }

    public User getUser(Long userId) {
        if (userId.equals(getUser().getId())) {
            return userRepository.findById(userId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    public User findByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UsernameNotFoundException("Username " + username + " is not found");
        }
    }
}
