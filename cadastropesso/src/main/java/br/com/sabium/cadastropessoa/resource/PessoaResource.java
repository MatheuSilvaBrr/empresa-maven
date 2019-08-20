package br.com.sabium.cadastropessoa.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.sabium.cadastropessoa.modelo.Pessoa;
import br.com.sabium.cadastropessoa.repository.PessoaRepository;
import br.com.sabium.cadastropessoa.service.PessoaService;

@Path("pessoas")
public class PessoaResource {

	@Inject
	private PessoaRepository pessoaRepository;
	
	@Inject
	private PessoaService pessoaService;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retornaTodasPessoa() {
		List<Pessoa> pessoasBuscadas = pessoaRepository.buscaTodasPessoa();
		return Response.ok(pessoasBuscadas).build();
	}
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retornaPessoa(@PathParam("id") Long id){
		Pessoa pessoaBuscada = pessoaRepository.buscaPeloId(id);
		return Response.ok(pessoaBuscada).build();
	}
	
	@Path("{id}")
	@DELETE
	public Response removePessoa(@PathParam("id") Long id) {
		pessoaRepository.remove(id);
		return Response.ok().build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salva(Pessoa pessoa) {
		pessoaService.salva(pessoa);
		return Response.ok(pessoa).build();
	}
	
	@Path("{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualiza(Pessoa pessoa, @PathParam("id") Long id) {
		pessoaService.atualiza(pessoa);
		return Response.ok(pessoa).build();
	}
	
//	private String toJson(Object pessoa) {
//		return new Gson().toJson(pessoa);
//	}
	
	
}
