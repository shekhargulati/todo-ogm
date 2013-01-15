package com.todo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.hibernate.search.jpa.FullTextEntityManager;

import com.todo.domain.TodoList;

@Stateless
public class TodoListService {

	@Inject
	private FullTextEntityManager em;
	
	@Inject
	Event<TodoList> todolistEvent;

	public TodoList create(TodoList todoList) {
		em.persist(todoList);
		todolistEvent.fire(todoList);
		return todoList;
	}

	public TodoList find(String id) {
		TodoList todoList = em.find(TodoList.class, id);
		List<String> tags = todoList.getTags();
		System.out.println("Tags : " + tags);
		return todoList;
	}

}
