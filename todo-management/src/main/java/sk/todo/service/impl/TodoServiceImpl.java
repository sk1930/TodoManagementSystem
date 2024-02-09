package sk.todo.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sk.todo.dto.TodoDto;
import sk.todo.entity.Todo;
import sk.todo.exception.ResourceNotFoundException;
import sk.todo.mapper.TodoMapper;
import sk.todo.repository.TodoRepository;
import sk.todo.service.TodoService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;
    // above both objects are using constructor injection
    // create by @AllArgsConstructor


    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        //convert TodoDto into JPA entity and save in to DB.
        // convert saved Todo object into TodoDto object and return

        /* with the help of ModelMapper library we can get rid of TodoMapper*/
        //Todo todo = TodoMapper.mapToTodo(todoDto);
        Todo todo = modelMapper.map(todoDto, Todo.class);
        Todo savedTodo = todoRepository.save(todo);

        //TodoDto savedTodoDto = TodoMapper.mapToTodoDto(savedTodo);
        TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);
        return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(UUID id) {
        /*Todo todo = todoRepository.findById(id).get();

        To throw exception if id is not present use below


         orElseThrow method takes supplier as a functional interface and we
         have to provide the lambda expression implementation for this
         supplier functional interface.


         */

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("todo with given id not present" + id));


        TodoDto todoDto = modelMapper.map(todo, TodoDto.class);
        return todoDto;


    }

    @Override
    public List<TodoDto> getAllTodos() {

        List<Todo> todos = todoRepository.findAll();

        return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public TodoDto updateTodo(TodoDto todoDto, UUID id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(UUID id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

        todoRepository.deleteById(id);

    }



    @Override
    public TodoDto completeTodo(UUID id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

        todo.setCompleted(Boolean.TRUE);

        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(UUID id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

        todo.setCompleted(Boolean.FALSE);

        Todo updatedTodo = todoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }
}
