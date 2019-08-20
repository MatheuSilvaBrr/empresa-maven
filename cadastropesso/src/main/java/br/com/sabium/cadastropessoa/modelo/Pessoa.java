package br.com.sabium.cadastropessoa.modelo;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonFilter;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import br.com.caelum.stella.tinytype.CPF;

@Table(name = "pessoa")
@NamedQueries({@NamedQuery(name = Pessoa.FIND_BY_CPF, query = "select p from Pessoa p where p.cpf =:cpf")})

@Entity
public class Pessoa {
	
	public static final String FIND_BY_CPF = "Pessoa.findByCpf";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String nome;
	
	@NotNull
	@Min(value = 1)
	private Integer idade;
	
	@NotBlank
	private String cpf;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoSexo tipoSexo;

	
	@ManyToMany
	private Set<Empresa> empresas;
	
	@Deprecated
	public Pessoa() {}

	public Pessoa(String nome, Integer idade, String cpf, TipoSexo tipoSexo) {
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
		this.tipoSexo = tipoSexo;
		empresas = new HashSet<Empresa>();
	}
	
	
	public void validaCpf() {
		if(!new CPF(this.cpf).isValido()) {
			throw new RuntimeException("CPF inválido");
		}
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresas.add(empresa);
	}
	public void atualiza(Pessoa pessoa) {
		this.nome = pessoa.getNome();
		this.tipoSexo = pessoa.getTipoSexo();
		this.idade = pessoa.getIdade();
		this.empresas = (Set<Empresa>) pessoa.getEmpresas();
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public String getCpf() {
		return cpf;
	}

	public TipoSexo getTipoSexo() {
		return tipoSexo;
	}

	public List<Empresa> getEmpresas() {
		return empresas.stream().sorted(Comparator.comparing(empresa -> empresa.getNome())).collect(Collectors.toList());
		
	}
	
}
