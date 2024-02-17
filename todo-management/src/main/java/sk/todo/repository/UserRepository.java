package sk.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.todo.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {




    /*143. UserRepository and RoleRepository in Notes
     */

    Optional<User> findByUsername(String username);
    // Okay, so spring Data JPA internally create, SQL query based on this method name.\

    Boolean existsByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username,String email);



}
