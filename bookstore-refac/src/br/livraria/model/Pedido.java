package br.livraria.model;

import java.util.Date;

public class Pedido {
	
	private final String[] formasPagamento = {"CREDITO", "DEBITO", "DINHEIRO"};
	private final String[] formasOperacoes = {"VENDA", "DEVOLUCAO"};

	private int idPedido;
	private Funcionario funcionario;
	private Pessoa cliente;
	private Date dataPedido;
	private double precoTotal;
	private String formaPagamento; //CREDITO - DEBITO - DINHEIRO
	private String operacao; //VENDA - DEVOLUCAO
	
	public Pedido(Funcionario funcionario, Pessoa cliente, Date dataVenda, double precoTotal, 
			String formaPagamento, String operacao) {
		setFuncionario(funcionario);
		setCliente(cliente);
		setDataPedido(dataVenda);
		setPrecoTotal(precoTotal);
		setFormaPagamento(formaPagamento);
		setOperacao(operacao);
	}
	
	public Pedido(Funcionario funcionario, Date dataVenda, String operacao) {
		setFuncionario(funcionario);
		setDataPedido(dataVenda);
		setOperacao(operacao);
	}
	
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		
		if(operacao == null) {
			setOperacao("VENDA");
		} else {
			boolean contem = false;
			for(int i = 0; i < formasOperacoes.length; i++) {
				if(formasOperacoes[i].equals(operacao)) {
					contem = true;
				}
			}
			
			if(contem)
				this.operacao = operacao;
		}
		
	}
	public void setPrecoTotal(double precoTotal) {
		this.precoTotal = precoTotal;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataVendida) {
		this.dataPedido = dataVendida;
	}
	public Pessoa getCliente() {
		return cliente;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		
		if(formaPagamento == null) {
			setFormaPagamento("DINHEIRO");
		} else {
			boolean contem = false;
			for(int i = 0; i < formasPagamento.length; i++) {
				if(formasPagamento[i].equals(formaPagamento)) {
					contem = true;
				}
			}
			
			if(contem)
				this.formaPagamento = formaPagamento;
			
		}
		
	}
	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public double getPrecoTotal() {
		return precoTotal;
	}
}