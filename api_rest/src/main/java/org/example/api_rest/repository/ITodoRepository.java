package org.example.api_rest.repository;
import org.example.api_rest.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findAllByValidated(boolean validated);
}
