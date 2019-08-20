package br.com.sabium.cadastropessoa.modelo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.com.caelum.stella.tinytype.CNPJ;

@Entity
@Table(name = "empresa")
@JsonIgnoreProperties({ "id","cnpj", "funcionarios" })
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Min(value = 3)
	@Min(value = 100)
	private String nome;

	@NotBlank
	private String cnpj;
	
	@ManyToMany(mappedBy = "empresas")
	private Set<Pessoa> funcionarios;
	
	private Integer quantidadeFuncionarios;
	
	@Deprecated
	public Empresa() {}
	
	public Empresa(String nome, String cnpj) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.funcionarios = new HashSet<Pessoa>();
	}

	public void validaCNPJ() {
		if (!new CNPJ(this.cnpj).isValid()) {
		//	throw new RuntimeException("CNPJ inválido");
		}
		
	}

	public void contratar(Pessoa pessoa) {
		funcionarios.add(pessoa);
		pessoa.setEmpresa(this);
		quantidadeFuncionarios = funcionarios.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public Set<Pessoa> getFuncionarios() {
		return Collections.unmodifiableSet(funcionarios);
	}
	public Integer getQuantidadeFuncionarios() {
		return quantidadeFuncionarios;
	}
}
