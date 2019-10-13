package com.phamtrong.app.repository.custom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractRepositoryCustomImpl<T> {

	@PersistenceContext
	protected EntityManager entityManager;
	
}
