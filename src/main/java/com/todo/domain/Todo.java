package com.todo.domain;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Todo {

	@NotNull
	@Size(min = 10, max = 40)
	private String todo;

	@NotNull
	private Date createdOn = new Date();
	
	public Todo(String todo) {
		this.todo = todo;
	}

	public Todo() {
	}

	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "Todo [todo=" + todo + "]";
	}

}
