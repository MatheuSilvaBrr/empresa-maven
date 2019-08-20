package br.com.sabium.cadastropessoa.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.sabium.cadastropessoa.modelo.Empresa;
import br.com.sabium.cadastropessoa.repository.EmpresaRepository;

@Stateless
public class EmpresaService {
	
	@Inject
	private EmpresaRepository empresaRepository;
	
	public Empresa salva(Empresa empresa) {
		empresa.validaCNPJ();
		empresaRepository.salva(empresa);
		return empresa;
	}
	public Empresa atualiza(Empresa empresa) {
		empresa.validaCNPJ();
		empresaRepository.atualiza(empresa);
		return empresa;
	}

}
