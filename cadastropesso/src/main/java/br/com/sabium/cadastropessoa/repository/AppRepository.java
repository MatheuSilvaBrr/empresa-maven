package br.com.sabium.cadastropessoa.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public abstract class AppRepository {
	
	@PersistenceContext(name = "cadastroProdutoPU")
	protected EntityManager entityManager;
}
