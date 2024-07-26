package org.example.api_rest.controller;

import org.example.api_rest.entity.Todo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.example.api_rest.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/")
public class TodoController {
    public final TodoService todoService;
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @GetMapping("/todo/status")
    public ResponseEntity<List<Todo>> getValidatedTodos(@RequestBody boolean status) {
        return ResponseEntity.ok(todoService.getValidatedTodos(status));
    }

    @GetMapping("/todo")
    public ResponseEntity<Todo> getTodoById(@RequestBody int id) {
        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    @DeleteMapping("/todo")
    public ResponseEntity<Boolean> deleteTodoById(@RequestBody int id) {
        return ResponseEntity.ok(todoService.deleteTodoById(id));
    }

    @PostMapping("/todo")
    public ResponseEntity<Todo> saveTodo(@RequestBody Todo todo) {
        todoService.saveTodo(todo);
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/todo")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todoRequest) {
        todoService.saveTodo(todoRequest);
        return ResponseEntity.ok(todoRequest);
    }

}
