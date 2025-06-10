package org.example.repository;

import org.example.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = {"roles"})
    Optional<User> findByUserNameWithEntityGraph(String userName);

    boolean existsByUserName(String userName);
    boolean existsByTel(String tel);
    boolean existsByEmail(String email);
}
