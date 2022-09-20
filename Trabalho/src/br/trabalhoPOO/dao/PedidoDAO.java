package br.trabalhoPOO.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.trabalhoPOO.classes.*;
import br.trabalhoPOO.conexao.Conexao;

public class PedidoDAO {
	private Conexao conexao;
	PreparedStatement pInclusao;
	//PreparedStatement pInclusaoCliente; 
	//PreparedStatement pInclusaoEmpresa; 
	
	public PedidoDAO(Conexao conexao) { 
		this.conexao = conexao;
		prepararSqlInclusao();
		//prepararSqlInclusaoCliente();
		//prepararSqlInclusaoEmpresa();
	}
	
	private void prepararSqlInclusao() {
		String sql = "insert into vendas.pedido";
		sql = sql + " (idCliente, idEmpresa, data)";
		sql = sql + " values ";
		sql = sql + " (?, ?, ?)";
		
		try {
			pInclusao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public int incluirPedido(Pedido pedido) {
		try {
			pInclusao.setInt(1, pedido.getIdCliente());
			pInclusao.setInt(2, pedido.getIdEmpresa());
			pInclusao.setString(3, pedido.getData());
			//System.out.println("Pedido " + pedido.getIdPedido() + "Cadastrado");
			return pInclusao.executeUpdate();
			
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
			return 0;
		}
	}
	
	public Pedido apagarPedido(int idPedido) {
		Pedido pedido = new Pedido();
		
		String sql = "delete from vendas.pedido" +
						" where idPedido = " + idPedido;
		
		conexao.query(sql);	
		
		return pedido;
	}
	
	public Pedido updateTotal(int idPedido) {
		Pedido pedido = new Pedido();
		
		String sql;
		ResultSet tabela;
		
		sql = "update vendas.pedido set total = (select sum(quantidade * subtotal) from vendas.dadospedido where idPedido = " + idPedido + ") where idPedido =" + idPedido;
		
		tabela = conexao.query(sql);
		
		return pedido;
	}
	
	public Pedido localizarPedido(String data, int idPedido) {
		Pedido pedido = new Pedido();
		
		String sql;
		ResultSet tabela;
		
		if (data == null) {
			sql = "select * from vendas.pedido where idPedido = " + idPedido;
		} else
			sql = "select * from vendas.pedido where data = '" + data + "'";
		
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) {
				pedido.setIdPedido(tabela.getInt("idPedido"));
				pedido.setIdEmpresa(tabela.getInt("idEmpresa"));
				pedido.setIdCliente(tabela.getInt("idCliente"));
				pedido.setData(tabela.getString("data"));
			} else {
				if (data == null) {
					System.out.println("idPedido " + idPedido + " não localizado.");
				} else
					System.out.println("Data '" + data + "' não localizado.");
				
				pedido = null;
			}
			
			tabela.close();	
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		return pedido;
	}
	
	public Pedido ListarPedidoSimples() {
		Pedido pedido = new Pedido();
		
		String sql;
		ResultSet tabela;
		
		sql = "select * from vendas.pedido";
		sql = sql + " order by idPedido ";
		
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) {
				System.out.println("\nID Pedido\tID Cliente\tID Empresa\tData");
				System.out.println("--------------------------------------------------------------");
			} else {
				System.out.println("Não há dados para serem listados.");
			}
						
			tabela.beforeFirst();
			
			while (tabela.next()) {
				
				System.out.printf("%-10s\t%-10s\t%-10s\t%s\n",
						tabela.getString("idPedido"),
						tabela.getString("idCliente"),
						tabela.getString("idEmpresa"),
						tabela.getString("data")
						);
			}
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		return pedido;
	}
	
	public Pedido localizarPedidosID(int idPedido) {
		Pedido pedido = new Pedido();
		
		String sql;
		ResultSet tabela;
		
		sql = "select ";
		sql = sql + " 	pe.idPedido, ";
		sql = sql + " 	data,";
		sql = sql + " 	pe.idempresa as idempresa, ";
		sql = sql + " 	e.nome as nomeEmpresa, ";
		sql = sql + " 	e.cpfcnpj as cnpjEmpresa,";
		sql = sql + " 	pe.idcliente as idcliente,";
		sql = sql + " 	c.nome as nomeCliente,";
		sql = sql + " 	c.cpfcnpj as cpfCliente,";
		sql = sql + " 	c.cep,";
		sql = sql + " 	c.email,";
		sql = sql + " 	p.nome as nomeProduto,";
		sql = sql + " 	p.descricao,";
		sql = sql + " 	sum (dp.quantidade) as quantidade,";
		sql = sql + " 	sum (dp.subtotal) as subtotal,";
		sql = sql + "	pe.total";
		sql = sql + " from vendas.pedido pe";
		sql = sql + " left join vendas.dadospedido dp on dp.idPedido = pe.idpedido";
		sql = sql + " left join vendas.empresa e on e.idEmpresa = pe.idEmpresa";
		sql = sql + " left join vendas.cliente c on c.idCliente = pe.idCliente";
		sql = sql + " left join vendas.produto p on p.idProduto = dp.idProduto";
		sql = sql + " where pe.idPedido = " + idPedido;
		sql = sql + " group by pe.idPedido, e.nome, e.cpfcnpj, c.nome, c.cpfcnpj, c.cep, c.email, p.nome, p.descricao";
		
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) {
				System.out.println("Código\tData\t\t\tEmpresa\t\t\tCnpj Empresa\tCliente\t\t\tCpf Cliente\tCEP Cliente\tEmail Cliente\t\tProduto\t\tDescrição\t\t\tQuantidade\tSubtotal\tTotal");
				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			} else {
				System.out.println("Não há dados para serem listados.");
			}
						
			tabela.beforeFirst();
			
			String sData;
			String sEmpresa;
			String sCliente;
			String sEmail;
			
			while (tabela.next()) {
				sData = "";
				sEmpresa = "";
				sCliente = "";
				sEmail = "";
				
				if (tabela.getString("data").length() < 20) {
					for (int i=20; i>tabela.getString("data").length(); i--) {
						sData += " ";
					}					
				}
				sData = tabela.getString("data") + sData;
				
				if (tabela.getString("nomeEmpresa").length() < 20) {
					for (int i=20; i>tabela.getString("nomeEmpresa").length(); i--) {
						sEmpresa += " ";
					}					
				}
				sEmpresa = tabela.getString("nomeEmpresa") + sEmpresa;
				
				if (tabela.getString("nomeCliente").length() < 20) {
					for (int i=20; i>tabela.getString("nomeCliente").length(); i--) {
						sCliente += " ";
					}					
				}
				sCliente = tabela.getString("nomeCliente") + sCliente;
				
				if (tabela.getString("email").length() < 20) {
					for (int i=20; i>tabela.getString("email").length(); i--) {
						sEmail += " ";
					}					
				}
				sEmail = tabela.getString("email") + sEmail;
				
				System.out.printf("%s\t%s\t%s\t%-10s\t%s\t%-10s\t%-10s\t%s\t%-10s\t%-30s\t%-10s\t%-10s\t%s\n",
						tabela.getString("idPedido"),
						sData,
						sEmpresa,
						tabela.getString("cnpjEmpresa"),
						sCliente,
						tabela.getString("cpfCliente"),
						tabela.getString("cep"),
						sEmail,
						tabela.getString("nomeProduto"),
						tabela.getString("descricao"),
						tabela.getInt("quantidade"),
						tabela.getDouble("subtotal"),
						tabela.getDouble("total")
						);
			}
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		return pedido;
	}
	
	public Pedido localizarPedidosData(String data) {
		Pedido pedido = new Pedido();
		
		String sql;
		ResultSet tabela;
		
		sql = "select ";
		sql = sql + " 	pe.idPedido, ";
		sql = sql + " 	data,";
		sql = sql + " 	pe.idempresa as idempresa, ";
		sql = sql + " 	e.nome as nomeEmpresa, ";
		sql = sql + " 	e.cpfcnpj as cnpjEmpresa,";
		sql = sql + " 	pe.idcliente as idcliente,";
		sql = sql + " 	c.nome as nomeCliente,";
		sql = sql + " 	c.cpfcnpj as cpfCliente,";
		sql = sql + " 	c.cep,";
		sql = sql + " 	c.email,";
		sql = sql + " 	p.nome as nomeProduto,";
		sql = sql + " 	p.descricao,";
		sql = sql + " 	sum (dp.quantidade) as quantidade,";
		sql = sql + " 	sum (dp.subtotal) as subtotal,";
		sql = sql + "	pe.total";
		sql = sql + " from vendas.pedido pe";
		sql = sql + " left join vendas.dadospedido dp on dp.idPedido = pe.idpedido";
		sql = sql + " left join vendas.empresa e on e.idEmpresa = pe.idEmpresa";
		sql = sql + " left join vendas.cliente c on c.idCliente = pe.idCliente";
		sql = sql + " left join vendas.produto p on p.idProduto = dp.idProduto";
		sql = sql + " where data = '" + data + "'";
		sql = sql + " group by pe.idPedido, e.nome, e.cpfcnpj, c.nome, c.cpfcnpj, c.cep, c.email, p.nome, p.descricao";
		
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) {
				System.out.println("Código\tData\t\t\tEmpresa\t\t\tCnpj Empresa\tCliente\t\t\tCpf Cliente\tCEP Cliente\tEmail Cliente\t\tProduto\t\tDescrição\t\t\tQuantidade\tSubtotal\tTotal");
				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			} else {
				System.out.println("Não há dados para serem listados.");
			}
						
			tabela.beforeFirst();
			
			String sData;
			String sEmpresa;
			String sCliente;
			String sEmail;
			
			while (tabela.next()) {
				sData = "";
				sEmpresa = "";
				sCliente = "";
				sEmail = "";
				
				if (tabela.getString("data").length() < 20) {
					for (int i=20; i>tabela.getString("data").length(); i--) {
						sData += " ";
					}					
				}
				sData = tabela.getString("data") + sData;
				
				if (tabela.getString("nomeEmpresa").length() < 20) {
					for (int i=20; i>tabela.getString("nomeEmpresa").length(); i--) {
						sEmpresa += " ";
					}					
				}
				sEmpresa = tabela.getString("nomeEmpresa") + sEmpresa;
				
				if (tabela.getString("nomeCliente").length() < 20) {
					for (int i=20; i>tabela.getString("nomeCliente").length(); i--) {
						sCliente += " ";
					}					
				}
				sCliente = tabela.getString("nomeCliente") + sCliente;
				
				if (tabela.getString("email").length() < 20) {
					for (int i=20; i>tabela.getString("email").length(); i--) {
						sEmail += " ";
					}					
				}
				sEmail = tabela.getString("email") + sEmail;
				
				System.out.printf("%s\t%s\t%s\t%-10s\t%s\t%-10s\t%-10s\t%s\t%-10s\t%-30s\t%-10s\t%-10s\t%s\n",
						tabela.getString("idPedido"),
						sData,
						sEmpresa,
						tabela.getString("cnpjEmpresa"),
						sCliente,
						tabela.getString("cpfCliente"),
						tabela.getString("cep"),
						sEmail,
						tabela.getString("nomeProduto"),
						tabela.getString("descricao"),
						tabela.getInt("quantidade"),
						tabela.getDouble("subtotal"),
						tabela.getDouble("total")
						);
			}
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		return pedido;
	}
	
	public void listarPedido() {
		ResultSet tabela;
		
		String sql = "select ";
		sql = sql + " 	pe.idPedido, ";
		sql = sql + " 	data,";
		sql = sql + " 	e.nome as nomeEmpresa, ";
		sql = sql + " 	e.cpfcnpj as cnpjEmpresa,";
		sql = sql + " 	c.nome as nomeCliente,";
		sql = sql + " 	c.cpfcnpj as cpfCliente,";
		sql = sql + " 	c.cep,";
		sql = sql + " 	c.email,";
		sql = sql + " 	p.nome as nomeProduto,";
		sql = sql + " 	p.descricao,";
		sql = sql + " 	sum (dp.quantidade) as quantidade,";
		sql = sql + " 	sum (dp.subtotal) as subtotal,";
		sql = sql + "	pe.total";
		sql = sql + " from vendas.pedido pe";
		sql = sql + " left join vendas.dadospedido dp on dp.idPedido = pe.idpedido";
		sql = sql + " left join vendas.empresa e on e.idEmpresa = pe.idEmpresa";
		sql = sql + " left join vendas.cliente c on c.idCliente = pe.idCliente";
		sql = sql + " left join vendas.produto p on p.idProduto = dp.idProduto";
		//sql = sql + " order by pe.idPedido";
		sql = sql + " group by pe.idPedido, e.nome, e.cpfcnpj, c.nome, c.cpfcnpj, c.cep, c.email, p.nome, p.descricao";
		
		tabela = conexao.query(sql);		
		
		try {
			if (tabela.next()) {
				System.out.println("Código\tData\t\t\tEmpresa\t\t\tCnpj Empresa\tCliente\t\t\tCpf Cliente\tCEP Cliente\tEmail Cliente\t\tProduto\t\tDescrição\t\t\tQuantidade\tSubtotal\tTotal");
				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			} else {
				System.out.println("Não há dados para serem listados.");
			}
						
			tabela.beforeFirst();
			
			String sData;
			String sEmpresa;
			String sCliente;
			String sEmail;
			
			while (tabela.next()) {
				sData = "";
				sEmpresa = "";
				sCliente = "";
				sEmail = "";
				
				if (tabela.getString("data").length() < 20) {
					for (int i=20; i>tabela.getString("data").length(); i--) {
						sData += " ";
					}					
				}
				sData = tabela.getString("data") + sData;
				
				if (tabela.getString("nomeEmpresa").length() < 20) {
					for (int i=20; i>tabela.getString("nomeEmpresa").length(); i--) {
						sEmpresa += " ";
					}					
				}
				sEmpresa = tabela.getString("nomeEmpresa") + sEmpresa;
				
				if (tabela.getString("nomeCliente").length() < 20) {
					for (int i=20; i>tabela.getString("nomeCliente").length(); i--) {
						sCliente += " ";
					}					
				}
				sCliente = tabela.getString("nomeCliente") + sCliente;
				
				if (tabela.getString("email").length() < 20) {
					for (int i=20; i>tabela.getString("email").length(); i--) {
						sEmail += " ";
					}					
				}
				sEmail = tabela.getString("email") + sEmail;
				
				System.out.printf("%s\t%s\t%s\t%-10s\t%s\t%-10s\t%-10s\t%s\t%-10s\t%-30s\t%-10s\t%-10s\t%s\n",
						tabela.getString("idPedido"),
						sData,
						sEmpresa,
						tabela.getString("cnpjEmpresa"),
						sCliente,
						tabela.getString("cpfCliente"),
						tabela.getString("cep"),
						sEmail,
						tabela.getString("nomeProduto"),
						tabela.getString("descricao"),
						tabela.getInt("quantidade"),
						tabela.getDouble("subtotal"),
						tabela.getDouble("total")
						);
			}
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}	
	
	public void alterarPedido(Pedido pedido) {
		String sql = "update vendas.pedido set " +
				"idCliente = '" + pedido.getIdCliente() + "'" +
				", idEmpresa = '" + pedido.getIdEmpresa() + "'" +
				", data = '" + pedido.getData() + "'" +
				", total = '" + pedido.getTotal() + "'" +
				"where idPedido = " + pedido.getIdPedido();
		conexao.query(sql);
	}

}