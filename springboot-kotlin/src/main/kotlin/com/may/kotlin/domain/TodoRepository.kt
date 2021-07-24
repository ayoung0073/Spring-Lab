package com.may.kotlin.domain

import org.springframework.data.repository.CrudRepository

interface TodoRepository : CrudRepository<Todo, Long>