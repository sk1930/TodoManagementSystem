package sk.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.todo.entity.Role;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    //Okay, so this is a custom query method that will retrieve a Role object by name.
    Role findByName(String name);

}
