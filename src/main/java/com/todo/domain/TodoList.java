package com.todo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Norms;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "todolists")
@Indexed
public class TodoList {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;

	@NotNull
	@Size(min = 10, max = 20)
	@Fields({
			@Field(analyze = Analyze.NO, norms = Norms.NO, store = Store.YES, name = "name"),
			@Field(analyze = Analyze.YES, norms = Norms.YES) })
	private String name;

	@NotNull
	@Email
	private String email;

	@NotNull
	private Date createdOn = new Date();

	@ElementCollection
	private List<Todo> todos;

	@ElementCollection
	private List<String> tags;

	public TodoList(String name, List<Todo> todos, List<String> tags) {
		super();
		this.name = name;
		this.todos = todos;
		this.tags = tags;
	}

	public TodoList() {
		super();
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public List<Todo> getTodos() {
		return todos;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "TodoList [id=" + id + ", name=" + name + ", tags=" + tags + "]";
	}

}
