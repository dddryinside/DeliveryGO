package bsuir.chernikov.main.repository;

import bsuir.chernikov.main.entities.User;
import bsuir.chernikov.main.enums.ROLE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findAllByRole(ROLE role);
}
