package com.todo.utils;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.SearchFactory;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.todo.domain.TodoList;

public class Resources {

	@Produces
    @PersistenceContext
    @JpaEntityManager
    private EntityManager em;

    @Produces
    public Logger logger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    @Produces
    @RequestScoped
    public FacesContext produceFacesContext() {
        return FacesContext.getCurrentInstance();
    }
    
    @Produces
	@ApplicationScoped
	public QueryBuilder getTodoListQueryBuilder() {
		return getSearchFactory().buildQueryBuilder().forEntity(TodoList.class)
				.get();
	}

	@Produces
	@ApplicationScoped
	public SearchFactory getSearchFactory() {
		return getFullTextEntityManager().getSearchFactory();
	}

	@Produces
	public FullTextEntityManager getFullTextEntityManager() {
		return Search.getFullTextEntityManager(em);
	}

}
