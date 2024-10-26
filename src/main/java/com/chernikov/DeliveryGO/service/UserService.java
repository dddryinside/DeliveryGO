package com.chernikov.DeliveryGO.service;

import com.chernikov.DeliveryGO.enums.ROLE;
import com.chernikov.DeliveryGO.repository.UserRepository;
import com.chernikov.DeliveryGO.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void saveUser(User user) {
        if (userRepository.count() == 0) {
            user.setRole(ROLE.DIRECTOR);
        }
        userRepository.save(user);
    }

    public User getUserFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        Object principal = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        return findByUsername(userDetails.getUsername());
    }

    public User getUser(Long userId) {
        User contextUser = getUserFromContext();
        if (userId.equals(contextUser.getId())) {
            return contextUser;
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

    public void topUpBalance(User user, Integer amount) {
        Integer currentBalance = user.getBalance();
        user.setBalance(currentBalance + amount);
        userRepository.save(user);
    }
}
