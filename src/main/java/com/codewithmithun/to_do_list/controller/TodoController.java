package com.codewithmithun.to_do_list.controller;

import com.codewithmithun.to_do_list.entity.Todo;
import com.codewithmithun.to_do_list.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
//@CrossOrigin(origins = "*")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // get all todos
    @GetMapping
    public List<Todo> getAllTodos(){
        return todoService.getAllTodos();
    }

    // get by id todo
    @GetMapping("/{id}")
    public Todo getTodo(@PathVariable Long id){
        return todoService.getTodoById(id);
    }
    //save todo
    @PostMapping
    public Todo createTodo(@RequestBody Todo todo){
        return todoService.createTodo(todo);
    }
    // update todo
    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id,@RequestBody Todo todo){
        return todoService.updateTodo(id,todo);
    }
    // delete todo
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
    }


}
