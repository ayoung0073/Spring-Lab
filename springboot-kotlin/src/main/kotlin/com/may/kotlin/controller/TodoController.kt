package com.may.kotlin.controller

import com.may.kotlin.controller.dto.TodoRequest
import com.may.kotlin.domain.Todo
import com.may.kotlin.service.TodoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todo")
class TodoController(
        private val todoService: TodoService
) {

    @GetMapping
    fun getTodoList(): MutableIterable<Todo> = todoService.getTodoList()

    @PostMapping
    fun insertTodo(@RequestBody todoRequest: TodoRequest) = todoService.insertTodo(todoRequest.name)

    @PutMapping(path = ["/{todoId}"])
    fun updateTodo(@PathVariable("todoId") todoId: Long) = todoService.updateTodo(todoId)

    @DeleteMapping(path = ["/{todoId}"])
    fun deleteTodo(@PathVariable("todoId") todoId: Long) = todoService.deleteTodo(todoId)

}