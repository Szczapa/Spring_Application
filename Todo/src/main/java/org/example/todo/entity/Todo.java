package org.example.todo.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class Todo {
    private int id;
    private String title;
    private String description;
    private boolean done;

    private List<Todo> todos;

    public Todo(int id,String title, String description, boolean done) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = done;
    }
}
