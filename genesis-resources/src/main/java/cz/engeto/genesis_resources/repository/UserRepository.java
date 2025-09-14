package cz.engeto.genesis_resources.repository;

import cz.engeto.genesis_resources.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPersonID(String personID);
}
