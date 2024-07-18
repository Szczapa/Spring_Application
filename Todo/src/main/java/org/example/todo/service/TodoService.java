package org.example.todo.service;

import org.example.todo.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private final List<Todo> todos = new ArrayList<>();

    public List<Todo> getTodos() {
        return todos;
    }

    public Todo getTodoById(int id) {
        return todos.stream()
                .filter(todo -> todo.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void add(Todo todo) {
        todo.setId(getNextId());
        todos.add(todo);
    }

    public int getNextId() {
        return todos.size() + 1;
    }
}
