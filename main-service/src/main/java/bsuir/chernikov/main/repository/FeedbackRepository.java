package bsuir.chernikov.main.repository;

import bsuir.chernikov.main.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long>,
        PagingAndSortingRepository<Feedback, Long> {
}