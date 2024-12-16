package bsuir.chernikov.main.controllers;

import bsuir.chernikov.main.dto.CommentDto;
import bsuir.chernikov.main.entities.Client;
import bsuir.chernikov.main.service.CommentService;
import bsuir.chernikov.main.service.UserService;
import bsuir.chernikov.main.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final UserService userService;
    private final CommentService commentService;

    @PostMapping("/api/save-courier-comment")
    public void saveCourierComment(@RequestBody CommentDto commentDto) {
        commentService.saveCourierComment(commentDto);
    }

    @GetMapping("/api/get-courier-comments")
    public List<CommentDto> getCourierComments(@RequestParam Long courierId) {
        return commentService.getCouriersComments(courierId)
                .stream().map(comment -> Converter.convertCommentToDto(comment, userService.getUserFromContext())).toList();
    }

    @GetMapping("/api/get-courier-page-comment-form-permission")
    public Boolean getCourierPageCommentFormPermission() {
        return userService.getUserFromContext() instanceof Client;
    }

    @DeleteMapping("/api/delete-courier-comment")
    public void deleteComment(@RequestBody CommentDto commentDto) {
        commentService.deleteCourierComment(commentDto);
    }
}
