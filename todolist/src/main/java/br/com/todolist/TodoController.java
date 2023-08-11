package br.com.todolist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
    @GetMapping("/api/todos")
    public Todo getTodos() {
        Todo todo = new Todo("Sample Task", "description");

        System.out.println(todo);
        return todo;
    }
}
