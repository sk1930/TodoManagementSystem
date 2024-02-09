package sk.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.todo.entity.Todo;

import java.util.UUID;

public interface TodoRepository  extends JpaRepository<Todo, UUID> {
}
