package br.com.sabium.cadastropessoa.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.sabium.cadastropessoa.modelo.Pessoa;
import br.com.sabium.cadastropessoa.repository.PessoaRepository;

@Stateless
public class PessoaService {

	@Inject
	private PessoaRepository pessoaRepository;
	
	public Pessoa salva(Pessoa pessoa) {
		pessoa.validaCpf();
		pessoaRepository.salva(pessoa);
		return pessoa;
	}
	public Pessoa atualiza(Pessoa pessoa) {
		pessoa.validaCpf();
		pessoaRepository.atualiza(pessoa);
		return pessoa;
	}

	
}
