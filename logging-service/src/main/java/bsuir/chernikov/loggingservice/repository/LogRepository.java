package bsuir.chernikov.loggingservice.repository;

import bsuir.chernikov.loggingservice.entities.LogMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<LogMessage, Long> {
}
