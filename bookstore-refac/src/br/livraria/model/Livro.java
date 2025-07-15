package br.livraria.model;

import java.util.Date;

public class Livro {
	
	private int idLivro; 
	private String titulo; //NOT NULL 
	private String genero; //NOT NULL 
	private Date dataPublicada;
	private String autor;
	private int qtdEstoque; //NOT NULL  >= 0
	private double precoUnit; //NOT NULL > 0
	private Editora editora; //NOT NULL 
	
	public Livro(String titulo, String genero, Date dataPublicada, String autor, 
			int qtdEstoque, double precoUnit, Editora editora) {
		setTitulo(titulo);
		setGenero(genero);
		setDataPublicada(dataPublicada);
		setAutor(autor);
		setQtdEstoque(qtdEstoque);
		setPrecoUnit(precoUnit);
		setEditora(editora);
	}
	
	public double getPrecoUnit() {
		return precoUnit;
	}
	public void setPrecoUnit(double precoUnit) {
		if(precoUnit > 0)
			this.precoUnit = precoUnit;
	}
	public int getIdLivro() {
		return idLivro;
	}
	public void setIdLivro(int idLivro) {
		this.idLivro = idLivro;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Date getDataPublicada() {
		return dataPublicada;
	}
	public void setDataPublicada(Date dataPublicada) {
		this.dataPublicada = dataPublicada;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(int qtdEstoque) {
		if(qtdEstoque >= 0)
			this.qtdEstoque = qtdEstoque;
	}
	public Editora getEditora() {
		return editora;
	}
	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	public void removeEstoque(int quantidade) {
		this.qtdEstoque -= quantidade;
		if(this.qtdEstoque < 0)
			this.qtdEstoque = 0;
	}
	public void addEstoque(int quantidade) {
		this.qtdEstoque += quantidade;
	}
}
