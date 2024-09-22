package com.chernikov.DeliveryGO.service;

import com.chernikov.DeliveryGO.repository.UserRepository;
import com.chernikov.DeliveryGO.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUser() throws HttpClientErrorException.Unauthorized {
        return null;
    }
}
