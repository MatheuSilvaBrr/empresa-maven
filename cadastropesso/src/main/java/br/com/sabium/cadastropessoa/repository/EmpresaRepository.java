package br.com.sabium.cadastropessoa.repository;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import br.com.sabium.cadastropessoa.modelo.Empresa;
@Stateless
public class EmpresaRepository extends AppRepository {
	public EmpresaRepository() {}

	public EmpresaRepository (EntityManager em) {
		entityManager = em;
	}

	public Empresa salva(Empresa empresa) {
		entityManager.persist(empresa);
		return empresa;
	}

	public Empresa buscaPeloId(Long id) {
		Empresa EmpresaRetornada = entityManager.find(Empresa.class, id);
		return EmpresaRetornada;
	}

	public void remove(Empresa empresa) {
		Empresa EmpresaBuscada = entityManager.find(Empresa.class, empresa.getId());
		entityManager.remove(EmpresaBuscada);
	}

	public Empresa atualiza(Empresa empresa) {
		entityManager.merge(empresa);
		return empresa;
	}


}
