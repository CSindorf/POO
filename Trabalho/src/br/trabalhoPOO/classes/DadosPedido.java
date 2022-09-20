package br.trabalhoPOO.classes;

public class DadosPedido {
	
	private int idDadosPedido;
	private int idPedido;
	private int idProduto;
	private int quantidade;
	private double subtotal;
	
	public int getIdDadosPedido() {
		return idDadosPedido;
	}
	public void setIdDadosPedido(int idDadosPedido) {
		this.idDadosPedido = idDadosPedido;
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

}
