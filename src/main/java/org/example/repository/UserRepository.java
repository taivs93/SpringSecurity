package org.example.repository;

import org.example.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = {"roles"})
    @Query("SELECT u FROM User u WHERE u.userName = :userName")
    Optional<User> findByUserNameWithEntityGraph(@Param("userName") String userName);

    boolean existsByUserName(String userName);
    boolean existsByTel(String tel);
    boolean existsByEmail(String email);
}
