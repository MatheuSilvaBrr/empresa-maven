package br.com.sabium.cadastropessoa.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import br.com.sabium.cadastropessoa.modelo.Pessoa;

@Stateless
public class PessoaRepository extends AppRepository {
	public PessoaRepository() {}

	public PessoaRepository(EntityManager em) {
		entityManager = em;
	}

	public Pessoa salva(Pessoa pessoa) {
		entityManager.persist(pessoa);
		return pessoa;
	}

	public Pessoa buscaPeloId(Long id) {
		Pessoa pessoaRetornada = entityManager.find(Pessoa.class, id);
		return pessoaRetornada;
	}

	public Pessoa remove(Long id) {
		Pessoa pessoaBuscada = entityManager.find(Pessoa.class, id);
		entityManager.remove(pessoaBuscada);
		return pessoaBuscada;
	}

	public Pessoa atualiza(Pessoa pessoa) {
		Pessoa pessoaBuscada = entityManager.find(Pessoa.class, pessoa.getId());
		pessoaBuscada.atualiza(pessoa);
		entityManager.merge(pessoaBuscada);
		return pessoa;
	}
	
	public List<Pessoa> buscaTodasPessoa() {
		return entityManager.createQuery("from Pessoa", Pessoa.class).getResultList();
	}

	public Pessoa busporCpf(String cpf) {
		Pessoa pessoaRetornada = (Pessoa) entityManager.createNamedQuery(Pessoa.FIND_BY_CPF, Pessoa.class).setParameter("cpf",
				cpf);
		return pessoaRetornada;
	}

}
