package br.livraria.model;

public class Pessoa {
	
	private String cpf; //lenght() == 11 NOT NULL
	private String nome; //NOT NULL
	private String endereco;
	private String cidade;
	private String estado; //lenght() == 2
	private String telefone; //lenght() <= 11
	private String email;
	
	public Pessoa(Pessoa pessoa) {
		setCpf(pessoa.getCpf());
		setNome(pessoa.getNome());
		setEndereco(pessoa.getEndereco());
		setCidade(pessoa.getCidade());
		setEstado(pessoa.getEstado());
		setTelefone(pessoa.getTelefone());
		setEmail(pessoa.getEmail());
	
	}
	
	public Pessoa(String cpf, String nome, String endereco, String cidade, String estado, String telefone, String email) {
		setCpf(cpf);
		setNome(nome);
		setEndereco(endereco);
		setCidade(cidade);
		setEstado(estado);
		setTelefone(telefone);
		setEmail(email);
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		if(cpf != null && cpf.length() == 11)
			this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		if(estado != null && estado.length() == 2)
			this.estado = estado;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		if(telefone != null && telefone.length() <= 11)
			this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
