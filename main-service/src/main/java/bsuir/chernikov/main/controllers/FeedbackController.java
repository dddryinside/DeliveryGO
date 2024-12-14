package bsuir.chernikov.main.controllers;

import bsuir.chernikov.main.dto.FeedbackDto;
import bsuir.chernikov.main.dto.ResponseWrapper;
import bsuir.chernikov.main.service.FeedbackService;
import bsuir.chernikov.main.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping(value = {"/api/send-feedback"})
    public void sendFeedback(@RequestBody String message) {
        feedbackService.saveFeedback(message);
    }

    @GetMapping("/api/get-all-feedbacks")
    public ResponseWrapper<FeedbackDto> getAllFeedbacks(@RequestParam Integer page) {
        return Converter.convertFeedbacksList(feedbackService.getFeedbacks(PageRequest.of(page, 10)));
    }

    @DeleteMapping("/api/delete-feedback")
    public void deleteFeedback(@RequestParam Long feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
    }
}