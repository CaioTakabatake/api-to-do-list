package br.com.todolist;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Integer id) {
        return todoRepository.findById(id);
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public void deleteTodoById(Integer id) {
        todoRepository.deleteById(id);
    }

    public Todo updateTodo(Integer id, Todo updatedTodo) {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada"));

        existingTodo.setTitle(updatedTodo.getTitle());
        existingTodo.setDescription(updatedTodo.getDescription());
        existingTodo.setStatus(updatedTodo.getStatus());

        return todoRepository.save(existingTodo);
    }

    public Todo startTodo(Integer id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada"));

        todo.setStatus(StatusEnum.IN_PROGRESS);

        return todoRepository.save(todo);
    }

    public Todo endTodo(Integer id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada"));

        todo.setStatus(StatusEnum.FINISHED);

        return todoRepository.save(todo);
    }


}
