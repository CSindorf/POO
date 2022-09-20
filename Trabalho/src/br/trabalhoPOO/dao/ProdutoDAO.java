package br.trabalhoPOO.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.trabalhoPOO.classes.Produto;
import br.trabalhoPOO.conexao.Conexao;

public class ProdutoDAO {
	private Conexao conexao;
	
	PreparedStatement pInclusao;
	
	public ProdutoDAO(Conexao conexao) { 
		this.conexao = conexao;
		prepararSqlInclusao();
	}
	
	private void prepararSqlInclusao() {
		String sql = "insert into vendas.produto";
		sql = sql + "(nome, descricao, custo, precoUnitario, estoque)";
		sql = sql + " values ";
		sql = sql + " (?, ?, ?, ?, ?)";
		
		try {
			pInclusao =  conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
	}
	
	public int incluirProduto(Produto produto) {
		try {
			pInclusao.setString(1, produto.getNome());
			pInclusao.setString(2, produto.getDescricao());
			pInclusao.setDouble(3, produto.getCusto());
			pInclusao.setDouble(4, produto.getPrecoUnitario());
			pInclusao.setInt(5, produto.getEstoque());
			//pInclusao.setDate(6, produto.getDataFabricacao());
			
			return pInclusao.executeUpdate();
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public void alterarProduto(Produto produto) {
		String sql = "update vendas.produto set " +
				"nome = '" + produto.getNome() + "'" +
				", descricao = '" + produto.getDescricao() + "'" +
				", custo = '" + produto.getCusto() + "'" +
				", precoUnitario = '" + produto.getPrecoUnitario() + "'" +
				", estoque = '" + produto.getEstoque() + "'" +
				"where idProduto = " + produto.getIdProduto();
		conexao.query(sql);
	}
	
	public void apagarProduto(int idProduto) {
		String sql = "delete from vendas.produto" +
						" where idProduto = " + idProduto;
		
		conexao.query(sql);		
	}
	
	public Produto localizarProduto(String nome, int idProduto) {
		Produto produto = new Produto();
		
		String sql;
		ResultSet tabela;
		
		if (nome == null) {
			sql = "select * from vendas.produto where idProduto = " + idProduto;
		} else
			sql = "select * from vendas.produto where nome = '" + nome + "'";
		
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) {
				produto.setIdProduto(tabela.getInt("idProduto"));
				produto.setNome(tabela.getString("nome"));
				produto.setDescricao(tabela.getString("descricao"));
				produto.setCusto(tabela.getDouble("custo"));
				produto.setPrecoUnitario(tabela.getDouble("precoUnitario"));
				produto.setEstoque(tabela.getInt("estoque"));
			} else {
				if (nome == null) {
					System.out.println("idProduto " + idProduto + " não localizado.");
				} else
					System.out.println("Produto '" + nome + "' não localizado.");
				
				produto = null;
			}
			
			tabela.close();	
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		return produto;
	}
	
	public Produto localizarPedidosProdutoID(int idProduto) {
		Produto produto = new Produto();
		
		String sql;
		ResultSet tabela;
		
		sql = "select ";
		sql = sql + " 	p.idPedido, ";
		sql = sql + " 	pe.idProduto, ";
		sql = sql + " 	data,";
		sql = sql + " 	e.idempresa as idempresa, ";
		sql = sql + " 	e.nome as nomeEmpresa, ";
		sql = sql + " 	e.cpfcnpj as cnpjEmpresa,";
		sql = sql + " 	c.idcliente as idcliente,";
		sql = sql + " 	c.nome as nomeCliente,";
		sql = sql + " 	c.cpfcnpj as cpfCliente,";
		sql = sql + " 	c.cep,";
		sql = sql + " 	c.email,";
		sql = sql + " 	pe.nome as nomeProduto,";
		sql = sql + " 	pe.descricao,";
		sql = sql + " 	sum (dp.quantidade) as quantidade,";
		sql = sql + " 	sum (dp.subtotal) as subtotal,";
		sql = sql + "	total";
		sql = sql + " from vendas.produto pe";
		sql = sql + " left join vendas.dadospedido dp on dp.idPedido = dp.idpedido";
		sql = sql + " left join vendas.empresa e on e.idEmpresa = e.idEmpresa";
		sql = sql + " left join vendas.cliente c on c.idCliente = c.idCliente";
		sql = sql + " left join vendas.pedido p on p.idPedido = dp.idPedido";
		sql = sql + " where pe.idProduto = " + idProduto;
		sql = sql + " group by p.idPedido, e.idempresa, e.nome, e.cpfcnpj, c.idcliente, c.nome, c.cpfcnpj, c.cep, c.email, pe.nome, pe.descricao, pe.idproduto";
		
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) {
				System.out.println("Código\tData\t\t\tEmpresa\t\t\tCnpj Empresa\tCliente\t\t\tCpf Cliente\tCEP Cliente\tEmail Cliente\t\tProduto\tDescrição\t\tQuantidade\tSubtotal\tTotal");
				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			} else {
				System.out.println("Não há dados para serem listados.");
			}
						
			tabela.beforeFirst();
			
			String sData;
			String sEmpresa;
			String sCliente;
			String sEmail;
			String sDescricao;
			
			while (tabela.next()) {
				sData = "";
				sEmpresa = "";
				sCliente = "";
				sEmail = "";
				sDescricao = "";
				
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
				
				if (tabela.getString("descricao").length() < 20) {
					for (int i=20; i>tabela.getString("descricao").length(); i--) {
						sDescricao += " ";
					}					
				}
				sDescricao = tabela.getString("descricao") + sDescricao;
				
				System.out.printf("%s\t%s\t%s\t%-10s\t%s\t%-10s\t%-10s\t%s\t%s\t%s\t%-10s\t%-10s\t%s\n",
						tabela.getString("idPedido"),
						sData,
						sEmpresa,
						tabela.getString("cnpjEmpresa"),
						sCliente,
						tabela.getString("cpfCliente"),
						tabela.getString("cep"),
						sEmail,
						tabela.getString("nomeProduto"),
						sDescricao,
						tabela.getInt("quantidade"),
						tabela.getDouble("subtotal"),
						tabela.getDouble("total")
						);
			}
			
			tabela.close();	
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		return produto;
	}
	
	public Produto localizarPedidosProdutoNome(String nome) {
		Produto produto = new Produto();
		
		String sql;
		ResultSet tabela;
		
		sql = "select ";
		sql = sql + " 	p.idPedido, ";
		sql = sql + " 	data,";
		sql = sql + " 	e.idempresa as idempresa, ";
		sql = sql + " 	e.nome as nomeEmpresa, ";
		sql = sql + " 	e.cpfcnpj as cnpjEmpresa,";
		sql = sql + " 	c.idcliente as idcliente,";
		sql = sql + " 	c.nome as nomeCliente,";
		sql = sql + " 	c.cpfcnpj as cpfCliente,";
		sql = sql + " 	c.cep,";
		sql = sql + " 	c.email,";
		sql = sql + " 	pe.nome as nomeProduto,";
		sql = sql + " 	pe.descricao,";
		sql = sql + " 	sum (dp.quantidade) as quantidade,";
		sql = sql + " 	sum (dp.subtotal) as subtotal,";
		sql = sql + "	total";
		sql = sql + " from vendas.produto pe";
		sql = sql + " left join vendas.dadospedido dp on dp.idPedido = dp.idpedido";
		sql = sql + " left join vendas.empresa e on e.idEmpresa = e.idEmpresa";
		sql = sql + " left join vendas.cliente c on c.idCliente = c.idCliente";
		sql = sql + " left join vendas.pedido p on p.idPedido = dp.idPedido";
		sql = sql + " where pe.nome = '" + nome + "'";
		sql = sql + " group by p.idPedido, e.idempresa, e.nome, e.cpfcnpj, c.idcliente, c.nome, c.cpfcnpj, c.cep, c.email, pe.nome, pe.descricao, pe.idproduto";
		
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) {
				System.out.println("Código\tData\t\t\tEmpresa\t\t\tCnpj Empresa\tCliente\t\t\tCpf Cliente\tCEP Cliente\tEmail Cliente\t\tProduto\tDescrição\t\tQuantidade\tSubtotal\tTotal");
				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			} else {
				System.out.println("Não há dados para serem listados.");
			}
						
			tabela.beforeFirst();
			
			String sData;
			String sEmpresa;
			String sCliente;
			String sEmail;
			String sDescricao;
			
			while (tabela.next()) {
				sData = "";
				sEmpresa = "";
				sCliente = "";
				sEmail = "";
				sDescricao = "";
				
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
				
				if (tabela.getString("descricao").length() < 20) {
					for (int i=20; i>tabela.getString("descricao").length(); i--) {
						sDescricao += " ";
					}					
				}
				sDescricao = tabela.getString("descricao") + sDescricao;
				
				System.out.printf("%s\t%s\t%s\t%-10s\t%s\t%-10s\t%-10s\t%s\t%s\t%s\t%-10s\t%-10s\t%s\n",
						tabela.getString("idPedido"),
						sData,
						sEmpresa,
						tabela.getString("cnpjEmpresa"),
						sCliente,
						tabela.getString("cpfCliente"),
						tabela.getString("cep"),
						sEmail,
						tabela.getString("nomeProduto"),
						sDescricao,
						tabela.getInt("quantidade"),
						tabela.getDouble("subtotal"),
						tabela.getDouble("total")
						);
			}
			
			tabela.close();	
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		return produto;
	}
	
	public void listarProdutos() {
		ResultSet tabela;		
		
		String sql = "select * from vendas.produto order by idProduto";
		
		tabela = conexao.query(sql);
		
		try {
			tabela.last();
			int rowCount = tabela.getRow(); 
			System.out.println("Quantidade de produtos: " +rowCount);
			
			if (rowCount > 0) {
				System.out.println("\nCódigo\tNome\t\t\tDescrição\t\t\tCusto\tPreço unitário\tEstoque");
				System.out.println("-------------------------------------------------------------------------------------------------------");	
			} else {
				System.out.println("\nNão possui dados.");
				return; 
			}

			tabela.beforeFirst();
			
			String sNome;
			String sDescricao;
			
			while (tabela.next()) {
				sNome = "";
				
				if (tabela.getString("nome").length() < 20) {
					for (int i=20; i>tabela.getString("nome").length(); i--) {
						sNome += " ";
					}					
				}
				sNome = tabela.getString("nome") + sNome;
				
				sDescricao = "";
				
				if (tabela.getString("descricao").length() < 30) {
					for (int i=30; i>tabela.getString("descricao").length(); i--) {
						sDescricao += " ";
					}					
				}
				sDescricao = tabela.getString("descricao") + sDescricao;
				
				System.out.printf("%s\t%s\t%s\t%s\t%-10s\t%s\n",
						tabela.getInt("idProduto"),
						sNome,
						sDescricao,
						tabela.getDouble("custo"),
						tabela.getDouble("precoUnitario"),
						tabela.getFloat("estoque")
						);
			}
			
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
}

