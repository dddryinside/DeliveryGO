package com.chernikov.DeliveryGO.service;

import com.chernikov.DeliveryGO.repository.UserRepository;
import com.chernikov.DeliveryGO.security.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
