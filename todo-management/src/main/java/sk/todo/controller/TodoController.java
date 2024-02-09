package sk.todo.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.todo.dto.TodoDto;
import sk.todo.service.TodoService;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;


    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {

        /*
        request: POST
        http://localhost:8090/api/todos
        {
    "title": "Learn spring boot",
    "description": "learn with example",
    "completed":false
        }


        response:
        {"id":"fdffc000-944b-4912-ab1c-33739fdb2223",
        "title":"Learn spring boot",
        "description":"learn with example",
        "completed":false}

         */

        TodoDto savedTodo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }


    /* we use @GetMapping annotation to map incoming http get request
    to this particular method.
    this ID is basically called URI template variable.*/
    // Build Get Todo REST API

    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") UUID todoId) {

        TodoDto todoDto = todoService.getTodo(todoId);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
        /* Request Type: Get
        Request URL : http://localhost:8090/api/todos/ba17dbc8-79b2-4f61-8102-d2e432404b65

        response:
        {
    "id": "ba17dbc8-79b2-4f61-8102-d2e432404b65",
    "title": "Learn Hibernate boot",
    "description": "learn Hibernate example",
    "completed": false
}

         reqeust 2 and response: {
    "timestamp": "2024-02-09T02:00:47.851+00:00",
    "status": 404,
    "error": "Not Found",
    "path": "/api/todos/ba17dbc8-79b2-4f61-8102-d2e432404b64"
}

request and response:
2024-02-08T21:00:29.200-05:00  WARN 5256 --- [nio-8090-exec-2] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.method.annotation.MethodArgumentTypeMismatchException:
Failed to convert value of type 'java.lang.String' to required type 'java.util.UUID'; UUID string too large]

{
    "timestamp": "2024-02-09T02:01:35.959+00:00",
    "status": 400,
    "error": "Bad Request",
    "path": "/api/todos/ba17dbc8-79b2-4f61-8102-d2e432404b643"
}


         */


    }


    // Build Get All Todos REST API
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todos = todoService.getAllTodos();


        /*return new ResponseEntity<>(todos, HttpStatus.OK);
        here is one more way to write the same statement.

         */
        return ResponseEntity.ok(todos);
    }
/*
    // Build Update Todo REST API
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long todoId){
        TodoDto updatedTodo = todoService.updateTodo(todoDto, todoId);
        return ResponseEntity.ok(updatedTodo);
    }

    // Build Delete Todo REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }

    // Build Complete Todo REST API
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId){
        TodoDto updatedTodo = todoService.completeTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }

    // Build In Complete Todo REST API
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long todoId){
        TodoDto updatedTodo = todoService.inCompleteTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }*/


    // Build Update Todo REST API
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") UUID todoId) {
        TodoDto updatedTodo = todoService.updateTodo(todoDto, todoId);
        return ResponseEntity.ok(updatedTodo);

    }



    // Build Delete Todo REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") UUID todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }


    // Build Complete Todo REST API
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") UUID todoId){
        TodoDto updatedTodo = todoService.completeTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
      /*
    Request :Patch
    http://localhost:8090/api/todos/fdffc000-944b-4912-ab1c-33739fdb2223/complete
        Response:
        {
    "id": "fdffc000-944b-4912-ab1c-33739fdb2223",
    "title": "Learn spring boot",
    "description": "learn with example",
    "completed": true
}

       */
    }

    // Build In Complete Todo REST API
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") UUID todoId){
        TodoDto updatedTodo = todoService.inCompleteTodo(todoId);
        return ResponseEntity.ok(updatedTodo);

        /*

    Request :Patch
    http://localhost:8090/api/todos/fdffc000-944b-4912-ab1c-33739fdb2223/in-complete
        Response:

        {
    "id": "fdffc000-944b-4912-ab1c-33739fdb2223",
    "title": "Learn spring boot",
    "description": "learn with example",
    "completed": false
}
         */
    }
}