package bsuir.chernikov.main.service;

import bsuir.chernikov.main.dto.CommentDto;
import bsuir.chernikov.main.dto.CourierDto;
import bsuir.chernikov.main.entities.Client;
import bsuir.chernikov.main.entities.Comment;
import bsuir.chernikov.main.entities.Courier;
import bsuir.chernikov.main.repository.CommentRepository;
import bsuir.chernikov.main.repository.CourierRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourierService {
    private final UserService userService;
    private final CourierRepository courierRepository;
    private final CommentRepository commentRepository;

    public Courier getCourierFromContext() {
        if (userService.getUserFromContext() instanceof Courier courier) {
            return courier;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    public void updateCourierInfo(CourierDto courierDto) {
        if (userService.getUserFromContext() instanceof Courier courier) {
            courier.setPhone(courierDto.getPhone());
            courier.setEmail(courierDto.getEmail());
            courier.setLocation(courierDto.getLocation());
            courier.setAbout(courierDto.getAbout());
            courierRepository.save(courier);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    public Courier getCourierById(Long courierId) {
        Optional<Courier> courierOptional = courierRepository.findById(courierId);
        if (courierOptional.isPresent()) {
            return courierOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
