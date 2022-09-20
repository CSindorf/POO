package br.trabalhoPOO.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.trabalhoPOO.classes.Cliente;
import br.trabalhoPOO.conexao.Conexao;

public class ClienteDAO {
	private Conexao conexao;
	PreparedStatement pInclusao; 
	
	public ClienteDAO(Conexao conexao) { 
		this.conexao = conexao;
		prepararSqlInclusao();
	}
	
	private void prepararSqlInclusao() {
		String sql = "insert into vendas.cliente";
		sql = sql + "(nome, cpfCnpj, logradouro, nrEndereco, complemento, bairro, cidade, cep, telefone, email)";
		sql = sql + " values ";
		sql = sql + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pInclusao =  conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public int incluirCliente(Cliente cliente) {
		try {	
			pInclusao.setString(1, cliente.getNome());
			pInclusao.setString(2, cliente.getCpfCnpj());
			pInclusao.setString(3, cliente.getLogradouro());
			pInclusao.setString(4, cliente.getNrEndereco());
			pInclusao.setString(5, cliente.getComplemento());
			pInclusao.setString(6, cliente.getBairro());
			pInclusao.setString(7, cliente.getCidade());
			pInclusao.setString(8, cliente.getCep());
			pInclusao.setString(9, cliente.getTelefone());
			pInclusao.setString(10, cliente.getEmail());
			
			return pInclusao.executeUpdate();
			
			
			
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public void alterarCliente(Cliente cliente) {
		String sql = "update vendas.cliente set " +
				"nome = '" + cliente.getNome() + "'" +
				", cpfCnpj = '" + cliente.getCpfCnpj() + "'" +
				", logradouro = '" + cliente.getLogradouro() + "'" +
				", nrEndereco = '" + cliente.getNrEndereco() + "'" +
				", complemento = '" + cliente.getComplemento() + "'" +
				", bairro = '" + cliente.getBairro() + "'" +
				", cidade = '" + cliente.getCidade() + "'" +
				", cep = '" + cliente.getCep() + "'" +
				", telefone = '" + cliente.getTelefone() + "'" +
				", email = '" + cliente.getEmail() + "'" +
				"where idCliente = " + cliente.getIdCliente();
		conexao.query(sql);
	}
	
	public Cliente selecionarCliente(int idCliente) {
		Cliente cliente = new Cliente();
		ResultSet tabela;
		
		String sql = "select * from vendas.cliente where id = " + idCliente;
		
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) {
				cliente.setIdCliente(tabela.getInt("idCliente"));
				cliente.setNome(tabela.getString("nome"));
				cliente.setCpfCnpj(tabela.getString("cpfCnpj"));
				cliente.setLogradouro(tabela.getString("logradouro"));
				cliente.setNrEndereco(tabela.getString("nrEndereco"));
				cliente.setComplemento(tabela.getString("complemento"));
				cliente.setBairro(tabela.getString("bairro"));
				cliente.setCidade(tabela.getString("cidade"));
				cliente.setCep(tabela.getString("cep"));
				cliente.setTelefone(tabela.getString("telefone"));
				cliente.setEmail(tabela.getString("email"));
			} else
				System.out.println("idCliente " + idCliente + " não localizado.");
			
			tabela.close();	
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		return cliente;
	}
	
	public void apagarCliente(int idCliente) {
		String sql = "delete from vendas.cliente" +
						" where idCliente = " + idCliente;
		
		conexao.query(sql);		
	}

	public Cliente localizarCliente(String nome, int idCliente) {
		Cliente cliente = new Cliente();
		
		String sql;
		ResultSet tabela;
		
		if (nome == null) {
			sql = "select * from vendas.cliente where idCliente = " + idCliente;
		} else
			sql = "select * from vendas.cliente where nome = '" + nome + "'";
		
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) {
				cliente.setIdCliente(tabela.getInt("idCliente"));
				cliente.setNome(tabela.getString("nome"));
				cliente.setCpfCnpj(tabela.getString("cpfCnpj"));
				cliente.setLogradouro(tabela.getString("logradouro"));
				cliente.setNrEndereco(tabela.getString("nrEndereco"));
				cliente.setComplemento(tabela.getString("complemento"));
				cliente.setBairro(tabela.getString("bairro"));
				cliente.setCidade(tabela.getString("cidade"));
				cliente.setCep(tabela.getString("cep"));
				cliente.setTelefone(tabela.getString("telefone"));
				cliente.setEmail(tabela.getString("email"));

			} else {
				if (nome == null) {
					System.out.println("idCliente " + idCliente + " não localizado.");
				} else
					System.out.println("Cliente '" + nome + "' não localizado.");
				
				cliente = null;
			}
			
			tabela.close();	
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		return cliente;
	}
	
	public Cliente localizarPedidosClienteID(int idCliente) {
		Cliente cliente = new Cliente();
		
		String sql;
		ResultSet tabela;
		
			sql = "select ";
			sql = sql + " 	d.idPedido, ";
			sql = sql + " 	data,";
			sql = sql + " 	e.nome as nomeEmpresa, ";
			sql = sql + " 	e.cpfcnpj as cnpjEmpresa,";
			sql = sql + " 	p.nome as nomeProduto,";
			sql = sql + " 	p.descricao,";
			sql = sql + " 	sum (dp.quantidade) as quantidade,";
			sql = sql + " 	sum (dp.subtotal) as subtotal,";
			sql = sql + "	total";
			sql = sql + " from vendas.cliente c";
			sql = sql + " left join vendas.pedido d on d.idPedido = d.idpedido";
			sql = sql + " left join vendas.dadospedido dp on dp.idPedido = d.idpedido";
			sql = sql + " left join vendas.empresa e on e.idEmpresa = d.idEmpresa";
			sql = sql + " left join vendas.produto p on p.idProduto = dp.idProduto";
			sql = sql + " where c.idCliente = " + idCliente;
			sql = sql + " group by d.idPedido, e.nome, e.cpfcnpj, p.nome, p.descricao";
		
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) {
				System.out.println("\n---- Pedidos do cliente ----");
			}
				System.out.println("Código\tData\t\t\tEmpresa\t\t\tCnpj Empresa\tProduto\t\tDescrição\t\tQuantidade\tSubtotal\tTotal");
				System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
						
			tabela.beforeFirst();
			
			String sData;
			String sEmpresa;
			
			while (tabela.next()) {
				sData = "";
				sEmpresa = "";
				
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
				
				System.out.printf("%s\t%s\t%s\t%-10s\t%-10s\t%-20s\t%-10s\t%-10s\t%s\n",
						tabela.getString("idPedido"),
						sData,
						sEmpresa,
						tabela.getString("cnpjEmpresa"),
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
		
		return cliente;
	}
	
	public Cliente localizarPedidosClienteNome(String nome) {
		Cliente cliente = new Cliente();
		
		String sql;
		ResultSet tabela;
		
			sql = "select ";
			sql = sql + " 	d.idPedido, ";
			sql = sql + " 	data,";
			sql = sql + " 	c.nome as nome,";
			sql = sql + " 	e.nome as nomeEmpresa, ";
			sql = sql + " 	e.cpfcnpj as cnpjEmpresa,";
			sql = sql + " 	p.nome as nomeProduto,";
			sql = sql + " 	p.descricao,";
			sql = sql + " 	sum (dp.quantidade) as quantidade,";
			sql = sql + " 	sum (dp.subtotal) as subtotal,";
			sql = sql + "	total";
			sql = sql + " from vendas.cliente c";
			sql = sql + " left join vendas.pedido d on d.idPedido = d.idpedido";
			sql = sql + " left join vendas.dadospedido dp on dp.idPedido = d.idpedido";
			sql = sql + " left join vendas.empresa e on e.idEmpresa = d.idEmpresa";
			sql = sql + " left join vendas.produto p on p.idProduto = dp.idProduto";
			sql = sql + " where c.nome = '" + nome + "'";
			sql = sql + " group by d.idPedido, c.nome, e.nome, e.cpfcnpj, p.nome, p.descricao";
		
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) {
				System.out.println("\n\n---- Pedidos do cliente ----");
			}
				System.out.println("Código\tData\t\t\tEmpresa\t\t\tCnpj Empresa\tProduto\t\tDescrição\t\tQuantidade\tSubtotal\tTotal");
				System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
						
			tabela.beforeFirst();
			
			String sData;
			String sEmpresa;
			
			while (tabela.next()) {
				sData = "";
				sEmpresa = "";
				
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
				
				System.out.printf("%s\t%s\t%s\t%-10s\t%-10s\t%-20s\t%-10s\t%-10s\t%s\n",
						//tabela.getString("nome"),
						tabela.getString("idPedido"),
						sData,
						sEmpresa,
						tabela.getString("cnpjEmpresa"),
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
		
		return cliente;
	}
	
	/*public Cliente localizarPedidosClienteNome(String nome, int idCliente) {
		Cliente cliente = new Cliente();
		
		String sql;
		ResultSet tabela, tbcliente;
		
		if (nome == null) {
			sql = "select * from vendas.cliente where idCliente = '" + idCliente + "'";
			System.out.println("Utilize a opção 1 para buscar por ID");
		} else
			sql = "select * from vendas.cliente where nome = '" + nome + "'";
			
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) {
				sql = "select * from vendas.cliente where idcliente = " + tabela.getInt("idCliente");
				
				tbcliente = conexao.query(sql);
				
				if (tbcliente.next()) {
					cliente.setNome(tbcliente.getString("nome"));
				}
				
				sql = "select ";
				sql = sql + " 	d.idPedido, ";
				//sql = sql + " 	data,";
				sql = sql + " 	c.nome,";
				sql = sql + " 	e.nome as nomeEmpresa, ";
				sql = sql + " 	e.cpfcnpj as cnpjEmpresa,";
				sql = sql + " 	p.nome as nomeProduto,";
				sql = sql + " 	p.descricao,";
				sql = sql + " 	dp.quantidade,";
				sql = sql + " 	dp.subtotal,";
				sql = sql + "	total";
				sql = sql + " from vendas.cliente c";
				sql = sql + " left join vendas.pedido d on d.idPedido = d.idpedido";
				sql = sql + " left join vendas.dadospedido dp on dp.idPedido = d.idpedido";
				sql = sql + " left join vendas.empresa e on e.idEmpresa = d.idEmpresa";
				sql = sql + " left join vendas.produto p on p.idProduto = dp.idProduto";
				sql = sql + " where c.idCliente = " + tabela.getInt("idCliente");
				
				tabela = conexao.query(sql);
				
				tabela.setIdPedido(tabela.getInt("idPedido"));
				tabela.setIdPedido()
				tabela.setCliente(cliente);
				tabela.setDataPedido(tbPedido.getDate("data"));
				tabela.setValorPedido(tbPedido.getDouble("valorpedido"));
				tabela.setNumero(tbPedido.getString("numero"));
				
				System.out.println("\n\n---- Pedidos do cliente ----");
			}
				System.out.println("Código\tData\t\t\tEmpresa\t\t\tCnpj Empresa\tProduto\t\tDescrição\t\tQuantidade\tSubtotal\tTotal");
				System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
						
			tabela.beforeFirst();
			
			String sData;
			String sEmpresa;
			
			while (tabela.next()) {
				sData = "";
				sEmpresa = "";
				
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
				
				System.out.printf("%s\n",
						tabela.set
						//tabela.getString("nome"),
						tabela.getString("idPedido")
						//sData
						//sEmpresa,
						//tabela.getString("cnpjEmpresa"),
						//tabela.getString("nomeProduto"),
						//tabela.getString("descricao"),
						//tabela.getInt("quantidade"),
						//tabela.getDouble("subtotal"),
						//tabela.getDouble("total")
						);
			}
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		return cliente;
	}*/
	
	public void listarClientes() {
		ResultSet tabela;		
		
		String sql = "select * from vendas.cliente order by idCliente";
		
		tabela = conexao.query(sql);
		
		try {
			tabela.last();
			int rowCount = tabela.getRow(); 
			System.out.println("Quantidade de clientes: " +rowCount);
			
			if (rowCount > 0) {
				System.out.println("\nCódigo\tNome\t\t\tCpf\t\tLogradouro\t\t\tNúmero\tComplemento\t\tBairro\t\tCidade\t\tCep\tTelefone\tEmail");
				System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			} else {
				System.out.println("\nNão possui dados.");
				return; 
			}
			
			tabela.beforeFirst();
			
			String sNome;
			String sLogradouro;
			String sComplemento;
			
			while (tabela.next()) {
				sNome = "";
				
				if (tabela.getString("nome").length() < 20) {
					for (int i=20; i>tabela.getString("nome").length(); i--) {
						sNome += " ";
					}					
				}
				sNome = tabela.getString("nome") + sNome;
				
				sLogradouro = "";
				
				if (tabela.getString("logradouro").length() < 30) {
					for (int i=30; i>tabela.getString("logradouro").length(); i--) {
						sLogradouro += " ";
					}					
				}
				sLogradouro = tabela.getString("logradouro") + sLogradouro;
				
				sComplemento = "";
				
				if (tabela.getString("complemento").length() < 20) {
					for (int i=20; i>tabela.getString("complemento").length(); i--) {
						sComplemento += " ";
					}					
				}
				sComplemento = tabela.getString("complemento") + sComplemento;
				
				System.out.printf("%s\t%s\t%-10s\t%s\t%s\t%s\t%s\t%s\t%s\t%-10s\t%s\n",
						tabela.getInt("idCliente"),
						sNome,
						tabela.getString("cpfCnpj"),
						sLogradouro,
						tabela.getString("nrEndereco"),
						sComplemento,
						tabela.getString("bairro"),
						tabela.getString("cidade"),
						tabela.getString("cep"),
						tabela.getString("telefone"),
						tabela.getString("email")
						);
			}
			
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
}
