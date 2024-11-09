package bsuir.chernikov.main.repository;

import bsuir.chernikov.main.entities.SupportMessage;
import bsuir.chernikov.main.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<SupportMessage, Long> {
    List<SupportMessage> findAllByUser(User user, Pageable pageable);
}
