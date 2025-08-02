package com.codewithmithun.to_do_list.service;

import com.codewithmithun.to_do_list.entity.Todo;
import com.codewithmithun.to_do_list.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id, Todo updatedTodo) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if(todo!=null){
            todo.setTitle(updatedTodo.getTitle());
            todo.setCompleted(updatedTodo.isCompleted());
            return todoRepository.save(todo);
        }
        return null;
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
