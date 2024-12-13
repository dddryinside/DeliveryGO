package bsuir.chernikov.main.service;

import bsuir.chernikov.main.entities.Feedback;
import bsuir.chernikov.main.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final UserService userService;
    private final FeedbackRepository feedbackRepository;

    public Feedback findById(Long id) {
        Optional<Feedback> feedback = feedbackRepository.findById(id);
        if (feedback.isPresent()) {
            return feedback.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void saveFeedback(String message) {
        Feedback feedback = new Feedback();
        feedback.setUser(userService.getUserFromContext());
        feedback.setDateTime(LocalDateTime.now());
        feedback.setContent(message);
        feedbackRepository.save(feedback);
    }

    public Page<Feedback> getFeedbacks(Pageable pageable) {
        return feedbackRepository.findAll(pageable);
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}