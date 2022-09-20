package br.trabalhoPOO.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.trabalhoPOO.classes.DadosPedido;
import br.trabalhoPOO.conexao.Conexao;

public class DadosPedidoDAO {
	private Conexao conexao;
	PreparedStatement pInclusao;
	
	public DadosPedidoDAO(Conexao conexao) { 
		this.conexao = conexao;
		prepararSqlInclusao();
	}
	
	private void prepararSqlInclusao() {
		String sql = "insert into vendas.dadospedido";
		sql = sql + " (idPedido, idProduto, quantidade)";
		sql = sql + " values ";
		sql = sql + " (?, ?, ?)";
		
		try {
			pInclusao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public int incluirDadosPedido(DadosPedido dadosPedido) {
		try {
			pInclusao.setInt(1, dadosPedido.getIdPedido());
			pInclusao.setInt(2, dadosPedido.getIdProduto());
			pInclusao.setInt(3, dadosPedido.getQuantidade());
			pInclusao.setInt(3, dadosPedido.getQuantidade());
			
			return pInclusao.executeUpdate();
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
			return 0;
		}
	}
	
	public DadosPedido updatePreco(int idProduto) {
		DadosPedido dadosPedido = new DadosPedido();
		
		String sql;
		sql = "update vendas.dadospedido set subtotal = (select (precoUnitario) from vendas.produto where idProduto = " + idProduto + ") where idProduto =" + idProduto;
		
		conexao.query(sql);
		
		return dadosPedido;
	}
	
}
