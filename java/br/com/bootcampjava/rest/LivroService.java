package br.com.bootcampjava.rest;

import java.util.ArrayList;
import java.util.List;

import br.com.bootcampjava.dto.Livro;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/livro")
public class LivroService {

	// output text
	  @GET
	  @Produces(MediaType.TEXT_PLAIN)
	  public String hello() {
	      return "Jersey hello world example.";
	  }
	
	  
	  @GET
	  @Path("listarLivros")
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Livro> listarLivros()  {
		  System.out.println("Chamou LivroService.listarLivros");
		  List<Livro> livros = new ArrayList();
		  livros.add(new Livro(1l, "OCA Java 8 - Guia de Estudos"));
		  return livros;
	  }
	  
	  @GET
	  @Path("buscarLivro")
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Livro> buscarLivro(@QueryParam("titulo") String titulo)  {
		  System.out.println("Chamou LivroService.buscarLivro, parametro: " + titulo);
		  List<Livro> livros = new ArrayList();
		  //TODO implementar
		  return livros;
	  }
	  
	  @POST
	  @Path("cadastrarLivro")
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Livro> cadasrarLivro(@QueryParam("titulo") String titulo)  {
		  System.out.println("Chamou LivroService.cadasrarLivro");
		  List<Livro> livros = new ArrayList();
		  livros.add(new Livro(1l, "OCA Java 8 - Guia de Estudos"));
		  System.out.println("chamou cadasrarLivro: " + titulo);
		  return livros;
	  }
	  
}
