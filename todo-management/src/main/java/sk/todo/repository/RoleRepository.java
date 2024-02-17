package sk.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.todo.entity.Role;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
