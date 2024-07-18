package org.example.todo.controller;

import org.example.todo.entity.Todo;
import org.example.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public String list(Model model) {
        List<Todo> todoList = todoService.getTodos();
        if (todoList == null) {
            todoList = new ArrayList<>(); // Ensure an empty list is passed to the model if todoList is null
        }
        model.addAttribute("todos", todoList);
        return "todo/list";
    }

    @GetMapping("/todo/{id}")
    public String todoView(@PathVariable int id, Model model) {
        Todo todo = todoService.getTodoById(id);
        model.addAttribute("todo", todo);
        return "todo/view";
    }

    @PostMapping("/todo/add")
    public String todoAdd() {
        Todo newTodo = new Todo(todoService.getNextId(), "New Todo", "New Todo Description", false);
        todoService.add(newTodo);
        Todo newTodo2 = new Todo(todoService.getNextId(), "New Todo 2", "New Todo Description 2", true);
        todoService.add(newTodo2);
        return "redirect:/";
    }
}
