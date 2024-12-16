package bsuir.chernikov.main.service;

import bsuir.chernikov.main.dto.CommentDto;
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
public class CommentService {
    private final UserService userService;
    private final CourierRepository courierRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void saveCourierComment(CommentDto commentDto) {
        if (userService.getUserFromContext() instanceof Client client) {
            Optional<Courier> courierOptional = courierRepository.findById(Long.valueOf(commentDto.getHolderId()));
            if (courierOptional.isPresent()) {
                Courier courier = courierOptional.get();

                Comment comment = new Comment();
                comment.setText(commentDto.getText());
                comment.setHolder(courier);
                comment.setClient(client);
                comment.setDateTime(LocalDateTime.now());

                commentRepository.save(comment);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    public List<Comment> getCouriersComments(Long courierId) {
        Optional<Courier> courierOptional = courierRepository.findById(courierId);
        if (courierOptional.isPresent()) {
            return courierOptional.get().getComments();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteCourierComment(CommentDto commentDto) {
        if (userService.getUserFromContext() instanceof Client client) {
            if (Objects.equals(client.getId(), Long.valueOf(commentDto.getAuthorId()))) {
                commentRepository.deleteById(Long.valueOf(commentDto.getId()));
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
