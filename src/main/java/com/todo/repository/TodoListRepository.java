package com.todo.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.DatabaseRetrievalMethod;
import org.hibernate.search.query.ObjectLookupMethod;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.todo.domain.TodoList;
import com.todo.utils.JpaEntityManager;

public class TodoListRepository {
	
	@Inject
	@JpaEntityManager
	private EntityManager em;
	
	@Inject
	private QueryBuilder queryBuilder;
	
	public List<TodoList> findAllTodoListsOrderedByName() {
		Query luceneQuery = queryBuilder
				.all()
				.createQuery();
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
		List resultList = fullTextEntityManager.createFullTextQuery( luceneQuery )
				.initializeObjectsWith( ObjectLookupMethod.SKIP, DatabaseRetrievalMethod.FIND_BY_ID )
				.setSort( new Sort( new SortField( "name", SortField.STRING_VAL ) ) )
				.getResultList();
		return resultList;
	}

	public TodoList findById(String id) {
		return em.find(TodoList.class, id);
	}


}

