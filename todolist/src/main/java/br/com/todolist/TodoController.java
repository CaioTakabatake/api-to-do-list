package br.com.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public Optional<Todo> getTodo(@PathVariable Integer id) {
        return todoService.getTodoById(id);
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTodo(@PathVariable Integer id) {
        todoService.deleteTodoById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Tarefa excluída com sucesso.");

        return ResponseEntity.ok(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Integer id, @RequestBody Todo updatedTodo) {
        Todo todo = todoService.updateTodo(id, updatedTodo);
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{id}/start_task")
    public ResponseEntity<Todo> startTodo(@PathVariable Integer id) {
        Todo todo = todoService.startTodo(id);
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{id}/end_task")
    public ResponseEntity<Todo> endTodo(@PathVariable Integer id) {
        Todo todo = todoService.endTodo(id);
        return ResponseEntity.ok(todo);
    }
}
