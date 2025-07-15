package br.livraria.model;

import java.util.Date;

public class Funcionario extends Pessoa {
	
	private int idFuncionario;
	private String senha; //NOT NULL
	private Date dataContrato; 
	private boolean administrador; 

	public Funcionario(String cpf, String nome, String endereco, String cidade, String estado, String telefone,
			String email, String senha, Date dataContrato, boolean administrador) {
		super(cpf, nome, endereco, cidade, estado, telefone, email);
		setSenha(senha);
		setDataContrato(dataContrato);
		setAdministrador(administrador);
	}
	
	public Funcionario(Pessoa pessoa, String senha, Date dataContrato, boolean administrador) {
		super(pessoa);
		setSenha(senha);
		setDataContrato(dataContrato);
		setAdministrador(administrador);
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataContrato() {
		return dataContrato;
	}

	public void setDataContrato(Date dataContrato) {
		this.dataContrato = dataContrato;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
	
	public String printPost() {
		if(isAdministrador())
			return "Administrador";
		return "Funcionario";
	}

}
