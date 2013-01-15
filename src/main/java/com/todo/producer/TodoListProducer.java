package com.todo.producer;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.todo.domain.TodoList;
import com.todo.repository.TodoListRepository;

@RequestScoped
public class TodoListProducer {

	@Inject
	private TodoListRepository todoListRepository;

	private List<TodoList> todolists;

	@Produces
	@Named
	public List<TodoList> getTodolists() {
		if(todolists == null){
			retrieveAllTodoListsOrderedByName();
		}
		return todolists;
	}

	public void onTodoListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final TodoList TodoList) {
		todolists = null;
	}

	@PostConstruct
	public void retrieveAllTodoListsOrderedByName() {
		todolists = todoListRepository.findAllTodoListsOrderedByName();
		System.out.println("Todo Lists : " + todolists);
	}
}
