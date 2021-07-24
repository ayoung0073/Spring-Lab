package com.may.kotlin.service

import com.may.kotlin.domain.Todo
import com.may.kotlin.domain.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoService(
        private val todoRepository: TodoRepository
) {
    fun getTodoList() = todoRepository.findAll()

    fun insertTodo(name: String): Todo = todoRepository.save(Todo(name = name)) // 반환 타입 생략 가능

    fun updateTodo(todoId: Long): Todo {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw Exception()
        todo.completed = !todo.completed
        return todoRepository.save(todo)
    }

    fun deleteTodo(todoId: Long) = todoRepository.deleteById(todoId)
}
