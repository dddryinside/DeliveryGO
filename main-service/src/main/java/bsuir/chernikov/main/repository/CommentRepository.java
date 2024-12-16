package bsuir.chernikov.main.repository;

import bsuir.chernikov.main.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
