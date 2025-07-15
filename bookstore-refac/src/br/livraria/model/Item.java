package br.livraria.model;

public class Item {

	private int idItem;
	private Livro livro;
	private Pedido pedido;
	private int qtdVendida; //> 0
	private double precoTotal; 

	public Item(Livro livro, Pedido pedido, int qtdVendida) {
		setLivro(livro);
		setPedido(pedido);
		setQtdVendida(qtdVendida);
		setPrecoTotal();
	}
	
	public double getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal() {
		precoTotal = getLivro().getPrecoUnit() * getQtdVendida();
		
		if(pedido.getOperacao().equals("DEVOLUCAO"))
			precoTotal = precoTotal * -1;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public Integer getIdItem() {
		return idItem;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public int getQtdVendida() {
		return qtdVendida;
	}
	public void setQtdVendida(int qtdVendida) {
		if(qtdVendida > 0)
			this.qtdVendida = qtdVendida;
	}
	public void addQtdVendida(int quantidade) {
		this.qtdVendida += quantidade;
	}
	public void removeQtdVendida(int quantidade) {
		this.qtdVendida -= quantidade;
		if(qtdVendida < 0)
			this.qtdVendida = 0;
	}
}