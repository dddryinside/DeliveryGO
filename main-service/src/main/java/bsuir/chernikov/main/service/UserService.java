package bsuir.chernikov.main.service;

import bsuir.chernikov.main.dto.CourierDto;
import bsuir.chernikov.main.entities.Courier;
import bsuir.chernikov.main.enums.ROLE;
import bsuir.chernikov.main.repository.CourierRepository;
import bsuir.chernikov.main.repository.UserRepository;
import bsuir.chernikov.main.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CourierRepository courierRepository;

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

    public User findByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username " + username + " is not found");
        }
    }

    public void topUpBalance(User user, Integer amount) {
        Integer currentBalance = user.getBalance();
        user.setBalance(currentBalance + amount);
        userRepository.save(user);
    }

    public void makeUserAdmin(String username) {
        User user = findByUsername(username);
        if (user.getRole() != ROLE.DIRECTOR) {
            user.setRole(ROLE.ADMIN);
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    public List<User> getAllAdminUsers() {
        return userRepository.findAllByRole(ROLE.ADMIN);
    }

    public void deleteAdmin(String username) {
        User user = findByUsername(username);
        if (user.getRole() == ROLE.ADMIN) {
            user.setRole(ROLE.UNDEFINED);
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
