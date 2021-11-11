package br.com.bootcampjava.dto;

public class Livro {
	public Livro() {
		
	}
	public Livro(Long id, String titulo) {
		this.id = id;
		this.titulo = titulo;
	}
	
	public Livro(String titulo) {
		this.titulo = titulo;
	}
	
	private Long id;
	private String titulo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
