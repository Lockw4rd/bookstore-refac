package br.livraria.model;

public class Editora {
	
	private int idEditora;
	private String nome; //NOT NULL 
	private String cnpj; //NOT NULL, length() == 14, UNIQUE
	private String endereco;
	
	public Editora(String nome, String cnpj, String endereco) {
		setNome(nome);
		setCnpj(cnpj);
		setEndereco(endereco);
	}
	
	public int getIdEditora() {
		return idEditora;
	}
	public void setIdEditora(int idEditora) {
		this.idEditora = idEditora;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		if(cnpj != null && cnpj.length() == 14)
			this.cnpj = cnpj;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
}
