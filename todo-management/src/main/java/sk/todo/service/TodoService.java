package sk.todo.service;


import sk.todo.dto.TodoDto;

import java.util.List;
import java.util.UUID;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodo(UUID id);

    List<TodoDto> getAllTodos();

    TodoDto updateTodo(TodoDto todoDto, UUID id);

    void deleteTodo(UUID id);

    TodoDto completeTodo(UUID id);

    TodoDto inCompleteTodo(UUID id);


}