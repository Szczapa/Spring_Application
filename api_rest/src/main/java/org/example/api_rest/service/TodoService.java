package org.example.api_rest.service;

import org.example.api_rest.entity.Todo;
import org.example.api_rest.repository.ITodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private  final ITodoRepository iTodoRepository;

    public TodoService(ITodoRepository iTodoRepository) {
        this.iTodoRepository = iTodoRepository;
    }

    public List<Todo> getAllTodos() {
        return iTodoRepository.findAll();
    }

    public Todo getTodoById(int id) {
        return iTodoRepository.findById(id).orElse(null);
    }

    public void saveTodo(Todo todo) {
        iTodoRepository.save(todo);
    }

    public boolean deleteTodoById(int id) {
        iTodoRepository.deleteById(id);
        return iTodoRepository.findById(id).isEmpty();
    }

    public List<Todo> getValidatedTodos(boolean status) {
        return iTodoRepository.findAllByValidated(status);
    }
}
