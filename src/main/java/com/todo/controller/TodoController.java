package com.todo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.todo.domain.Todo;
import com.todo.domain.TodoList;
import com.todo.service.TodoListService;

@Model
public class TodoController {

	@Inject
	private TodoListService todoService;

	@Inject
	private FacesContext facesContext;

	@Produces
	@Named
	private TodoList newTodoList;

	@PostConstruct
	public void initNewTodoList() {
		newTodoList = new TodoList();
	}

	public void createNewTodoList() {
		try {
			System.out.println("Persisting todo list");
			List<Todo> todos = newTodoItems();
			newTodoList.setTodos(todos);
			todoService.create(newTodoList);
			System.out.println("created todo list" + newTodoList);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Created!", "TodoList creation successful");
			facesContext.addMessage(null, m);
			initNewTodoList();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					errorMessage, "TodoList creation unsuccessful");
			facesContext.addMessage(null, m);
		}
	}

	private List<Todo> newTodoItems() {
		List<Todo> todos = new ArrayList<Todo>();
		for (int i = 1; i < 10; i++) {
			todos.add(new Todo("Learn about " + i));
		}
		return todos;
	}

	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Todolist creation failed. See server log for more information";
		if (e == null) {
			// This shouldn't happen, but return the default messages
			return errorMessage;
		}

		// Start with the exception and recurse to find the root cause
		Throwable t = e;
		while (t != null) {
			// Get the message from the Throwable class instance
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		// This is the root cause message
		return errorMessage;
	}
}
