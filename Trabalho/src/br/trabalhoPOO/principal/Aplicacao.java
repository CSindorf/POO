package br.trabalhoPOO.principal;

import java.util.Scanner;
import br.trabalhoPOO.classes.*;
import br.trabalhoPOO.dao.*;
import br.trabalhoPOO.conexao.Conexao;

/**
 * @author Grupo02
 * 			Arthur Carvalho Giangiarulo Lopes
 * 			Bruno Brum Janiques
 * 			Catarina Sindorf
 *			Erica Lessa Da Silva
 *			Pedro Henrique Almeida Burger
 */

public class Aplicacao {
public static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		Conexao con = new Conexao("PostgreSql", "localhost", "5432", "ecommerce", "postgres", "123456");	
		con.conect();	
		menuPrincipal(con);	
		con.disconect();
	}
	
	public static void menuPrincipal(Conexao con) {
		int opcao;
		
		do {
			System.out.println("\nMENU PRINCIPAL");
			System.out.println("---------------------");
			System.out.println("0: Sair");
			System.out.println("1: Cliente");
			System.out.println("2: Empresa");
			System.out.println("3: Produto");
			System.out.println("4: Pedido");
			System.out.println("5: Dados do pedido");			
			
			opcao = informeOpcao("Informe uma opção: ");
			
			switch(opcao) {
			case 0: System.out.println("Sistema encerrado."); break;
			case 1: menuCliente(con); break;	
			case 2: menuEmpresa(con); break;
			case 3: menuProduto(con); break;
			case 4: menuPedido(con); break;
			case 5: incluirDadosPedido(con); break;
			default: System.out.println("Opção inválida.");
			}		
			
		} while (opcao!=0);	
		
	}
	
	public static int informeOpcao(String msg) {
		System.out.print("\n"+ msg);
		String resposta = input.next();
		int opcao;
		
		try {
			opcao = Integer.parseInt(resposta);
		} catch (Exception e) {					
			opcao = 0;
		}
		
		return opcao;
	}
	
	public static void menuCRUD( ) {
		System.out.println("\n");
		System.out.println("1: Incluir");
		System.out.println("2: Alterar");
		System.out.println("3: Excluir");
		System.out.println("4: Localizar");
		System.out.println("5: Listar");
		System.out.println("6: Voltar");
	}
	
	public static void menuCliente(Conexao con) {
		int opcao;
		
		do {
			menuCRUD();
			opcao = informeOpcao("Informe uma opção: ");
			
			switch(opcao) {			
			case 1: incluirCliente(con); break;
			case 2: alterarCliente(con); break;
			case 3: excluirCliente(con); break;
			case 4: localizarCliente(con); break;
			case 5: listarCliente(con); break;
			case 6: break;
			default: System.out.println("Opção inválida.");
			}	
		} while (opcao!=6);
	}
	
	public static void incluirCliente(Conexao con) {
		ClienteDAO clienteDAO = new ClienteDAO(con);
		
		Cliente cliente = new Cliente();
		
		System.out.println("\n--- Dados do cliente ---");
		System.out.println("Informe o nome: ");
		String nome = input.next();
		System.out.println("Informe o CPF: ");
		String cpfCnpj = input.next();
		System.out.println("Informe o logradouro: ");
		String logradouro = input.next();
		System.out.println("Informe o número: ");
		String nrEndereco = input.next();
		System.out.println("Informe o complemento: ");
		String complemento = input.next();
		System.out.println("Informe o bairro: ");
		String bairro = input.next();
		System.out.println("Informe a cidade: ");
		String cidade = input.next();
		System.out.println("Informe o cep: ");
		String cep = input.next();
		System.out.println("Informe o telefone: ");
		String telefone = input.next();
		System.out.println("Informe o email: ");
		String email = input.next();
		
		cliente.setNome(nome);
		cliente.setCpfCnpj(cpfCnpj);
		cliente.setLogradouro(logradouro);
		cliente.setNrEndereco(nrEndereco);
		cliente.setComplemento(complemento);
		cliente.setBairro(bairro);
		cliente.setCidade(cidade);
		cliente.setCep(cep);
		cliente.setTelefone(telefone);
		cliente.setEmail(email);
		
		clienteDAO.incluirCliente(cliente);
	}

	public static void alterarCliente(Conexao con) {
		ClienteDAO clienteDAO = new ClienteDAO(con);
		
		String nome;
		String cpfCnpj;
		String logradouro;
		String nrEndereco;
		String complemento;
		String bairro;
		String cidade;
		String cep;
		String telefone;
		String email;
		
		System.out.println("Informe o ID do cliente: ");
		int codigo = input.nextInt();
		Cliente cliente = clienteDAO.localizarCliente(null, codigo);
				
		if (cliente!=null) {
			System.out.println("\nDados do cliente: ");
			System.out.println("-------------------------");
			System.out.println("Código: " + cliente.getIdCliente());
			System.out.println("Nome: " + cliente.getNome());
			System.out.println("CPF: " + cliente.getCpfCnpj());
			System.out.println("Logradouro: " + cliente.getLogradouro());
			System.out.println("Número: " + cliente.getNrEndereco());
			System.out.println("Complemento: " + cliente.getComplemento());
			System.out.println("Telefone: " + cliente.getTelefone());
			
			System.out.println("\nAltere o nome: ");
			nome = input.next();
			if (!nome.isBlank()) {
				cliente.setNome(nome);
			}
			
			System.out.println("Altere o cpf: ");
			cpfCnpj = input.next();
			if (!cpfCnpj.isBlank()) {
				cliente.setCpfCnpj(cpfCnpj);
			}
			
			System.out.println("Altere o logradouro: ");
			logradouro = input.next();
			if (!logradouro.isBlank()) {
				cliente.setLogradouro(logradouro);
			}
			
			System.out.println("Altere o número: ");
			nrEndereco = input.next();
			if (!nrEndereco.isBlank()) {
				cliente.setNrEndereco(nrEndereco);
			}
			
			System.out.println("Altere o complemento: ");
			complemento = input.next();
			if (!complemento.isBlank()) {
				cliente.setComplemento(complemento);
			}
			
			System.out.println("Altere o bairro: ");
			bairro = input.next();
			if (!bairro.isBlank()) {
				cliente.setBairro(bairro);
			}
			
			System.out.println("Altere a cidade: ");
			cidade = input.next();
			if (!cidade.isBlank()) {
				cliente.setCidade(cidade);
			}
			
			System.out.println("Altere o cep: ");
			cep = input.next();
			if (!cep.isBlank()) {
				cliente.setCep(cep);
			}
			
			System.out.println("Altere o telefone: ");
			telefone = input.next();
			if (!telefone.isBlank()) {
				cliente.setTelefone(telefone);
			}
			
			System.out.println("Altere o email: ");
			email = input.next();
			if (!email.isBlank()) {
				cliente.setEmail(email);
			}
			
			clienteDAO.alterarCliente(cliente);
		}	
	}
	
	public static void excluirCliente(Conexao con) {
		ClienteDAO clienteDAO = new ClienteDAO(con);
		
		int codigo = informeOpcao("\nInforme o ID do cliente: "); 
		
		Cliente cliente = clienteDAO.localizarCliente(null, codigo);
		
		if (cliente != null) {
			clienteDAO.apagarCliente(cliente.getIdCliente());
		}
	}
	
	public static void localizarCliente(Conexao con) {
		ClienteDAO clienteDAO = new ClienteDAO(con);
		
		int opcao;
		
		System.out.println("Buscar cliente por ID (1) ou nome (2)?");
		opcao = input.nextInt();
		
		if(opcao == 1) {	
			System.out.println("Informe o ID do cliente: ");
			int codigo = input.nextInt();
			Cliente cliente = clienteDAO.localizarCliente(null, codigo);
			if (cliente!=null) {
				System.out.println("\nDados do cliente: ");
				System.out.println("-------------------------");
				System.out.println("Código: " + cliente.getIdCliente());
				System.out.println("Nome: " + cliente.getNome());
				System.out.println("CPF: " + cliente.getCpfCnpj());
				System.out.println("Logradouro: " + cliente.getLogradouro());
				System.out.println("Número: " + cliente.getNrEndereco());
				System.out.println("Complemento: " + cliente.getComplemento());
				System.out.println("Telefone: " + cliente.getTelefone());
				
				System.out.println("\nExibir pedidos deste cliente?:");
				System.out.println("1: Sim");
				System.out.println("0: Não");
				opcao = input.nextInt();
					
				switch(opcao) {
				case 1: clienteDAO.localizarPedidosClienteID(codigo); break;
				case 0: System.out.println("Voltando"); break;
				default: System.out.println("Opção inválida!");
				} 
			}
		}
		if(opcao == 2) {
			System.out.println("Informe o nome do cliente: ");
			String nome = input.next();
			Cliente cliente = clienteDAO.localizarCliente(nome, 0);
			if (cliente!=null) {
				System.out.println("\nDados do cliente: ");
				System.out.println("-------------------------");
				System.out.println("Código: " + cliente.getIdCliente());
				System.out.println("Nome: " + cliente.getNome());
				System.out.println("CPF: " + cliente.getCpfCnpj());
				System.out.println("Logradouro: " + cliente.getLogradouro());
				System.out.println("Número: " + cliente.getNrEndereco());
				System.out.println("Complemento: " + cliente.getComplemento());
				System.out.println("Telefone: " + cliente.getTelefone());
				
				System.out.println("\nExibir pedidos deste cliente?:");
				System.out.println("1: Sim");
				System.out.println("0: Não");
				opcao = input.nextInt();
					
				switch(opcao) {
				case 1: clienteDAO.localizarPedidosClienteNome(nome); break;
				case 0: System.out.println("Voltando"); break;
				default: System.out.println("Opção inválida!");
				} 
			}
		}

	}
	
	public static void listarCliente(Conexao con) {
		ClienteDAO clienteDAO = new ClienteDAO(con);
		
		clienteDAO.listarClientes();
	}
	
	public static void menuEmpresa(Conexao con) {
		int opcao;
		
		do {
			menuCRUD();
			opcao = informeOpcao("Informe uma opção: ");
			
			switch(opcao) {			
			case 1: incluirEmpresa(con); break;
			case 2: alterarEmpresa(con); break;
			case 3: excluirEmpresa(con); break;
			case 4: localizarEmpresa(con); break;
			case 5: listarEmpresa(con); break;
			case 6: break;
			default: System.out.println("Opção inválida.");
			}	
		} while (opcao!=6);
	}
	
	public static void incluirEmpresa(Conexao con) {
		EmpresaDAO empresaDAO = new EmpresaDAO(con);
		
		Empresa empresa = new Empresa();
		
		System.out.println("\n--- Dados da empresa ---");
		System.out.println("Informe o nome: ");
		String nome = input.next();
		System.out.println("Informe o CNPJ: ");
		String cpfCnpj = input.next();
		System.out.println("Informe o logradouro: ");
		String logradouro = input.next();
		System.out.println("Informe o número: ");
		String nrEndereco = input.next();
		System.out.println("Informe o complemento: ");
		String complemento = input.next();
		System.out.println("Informe o bairro: ");
		String bairro = input.next();
		System.out.println("Informe a cidade: ");
		String cidade = input.next();
		System.out.println("Informe o cep: ");
		String cep = input.next();
		System.out.println("Informe o telefone: ");
		String telefone = input.next();
		System.out.println("Informe o email: ");
		String email = input.next();
		
		empresa.setNome(nome);
		empresa.setCpfCnpj(cpfCnpj);
		empresa.setLogradouro(logradouro);
		empresa.setNrEndereco(nrEndereco);
		empresa.setComplemento(complemento);
		empresa.setBairro(bairro);
		empresa.setCidade(cidade);
		empresa.setCep(cep);
		empresa.setTelefone(telefone);
		empresa.setEmail(email);
		
		empresaDAO.incluirEmpresa(empresa);
	}
	
	public static void alterarEmpresa(Conexao con) {
		EmpresaDAO empresaDAO = new EmpresaDAO(con);
		
		String nome;
		String cpfCnpj;
		String logradouro;
		String nrEndereco;
		String complemento;
		String bairro;
		String cidade;
		String cep;
		String telefone;
		String email;
		
		System.out.println("Informe o ID do cliente: ");
		int codigo = input.nextInt();
		Empresa empresa= empresaDAO.localizarEmpresa(null, codigo);
				
		if (empresa!=null) {
			System.out.println("\nDados da empresa: ");
			System.out.println("-------------------------");
			System.out.println("Código: " + empresa.getIdEmpresa());
			System.out.println("Nome: " + empresa.getNome());
			System.out.println("CPF: " + empresa.getCpfCnpj());
			System.out.println("Logradouro: " + empresa.getLogradouro());
			System.out.println("Número: " + empresa.getNrEndereco());
			System.out.println("Complemento: " + empresa.getComplemento());
			System.out.println("Telefone: " + empresa.getTelefone());
			
			System.out.println("\nAltere o nome: ");
			nome = input.next();
			if (!nome.isBlank()) {
				empresa.setNome(nome);
			}
			
			System.out.println("Altere o cpf: ");
			cpfCnpj = input.next();
			if (!cpfCnpj.isBlank()) {
				empresa.setCpfCnpj(cpfCnpj);
			}
			
			System.out.println("Altere o logradouro: ");
			logradouro = input.next();
			if (!logradouro.isBlank()) {
				empresa.setLogradouro(logradouro);
			}
			
			System.out.println("Altere o número: ");
			nrEndereco = input.next();
			if (!nrEndereco.isBlank()) {
				empresa.setNrEndereco(nrEndereco);
			}
			
			System.out.println("Altere o complemento: ");
			complemento = input.next();
			if (!complemento.isBlank()) {
				empresa.setComplemento(complemento);
			}
			
			System.out.println("Altere o bairro: ");
			bairro = input.next();
			if (!bairro.isBlank()) {
				empresa.setBairro(bairro);
			}
			
			System.out.println("Altere a cidade: ");
			cidade = input.next();
			if (!cidade.isBlank()) {
				empresa.setCidade(cidade);
			}
			
			System.out.println("Altere o cep: ");
			cep = input.next();
			if (!cep.isBlank()) {
				empresa.setCep(cep);
			}
			
			System.out.println("Altere o telefone: ");
			telefone = input.next();
			if (!telefone.isBlank()) {
				empresa.setTelefone(telefone);
			}
			
			System.out.println("Altere o email: ");
			email = input.next();
			if (!email.isBlank()) {
				empresa.setEmail(email);
			}
			
			empresaDAO.alterarEmpresa(empresa);
		}
	}
	
	public static void excluirEmpresa(Conexao con) {
		EmpresaDAO empresaDAO = new EmpresaDAO(con);
		
		int codigo = informeOpcao("\nInforme o ID da empresa: "); 
		
		Empresa empresa = empresaDAO.localizarEmpresa(null, codigo);
		
		if (empresa != null) {
			empresaDAO.apagarEmpresa(empresa.getIdEmpresa());
		}
	}
	
	public static void localizarEmpresa(Conexao con) {
		EmpresaDAO empresaDAO = new EmpresaDAO(con);
		int opcao;
		
		System.out.println("Buscar empresa por ID (1) ou nome (2)?");
		opcao = input.nextInt();
		
		if(opcao == 1) {	
			System.out.println("Informe o ID da empresa: ");
			int codigo = input.nextInt();
			Empresa empresa = empresaDAO.localizarEmpresa(null, codigo);
			if (empresa!=null) {
				System.out.println("\nDados da empresa: ");
				System.out.println("-------------------------");
				System.out.println("Código: " + empresa.getIdEmpresa());
				System.out.println("Nome: " + empresa.getNome());
				System.out.println("CPF: " + empresa.getCpfCnpj());
				System.out.println("Logradouro: " + empresa.getLogradouro());
				System.out.println("Número: " + empresa.getNrEndereco());
				System.out.println("Complemento: " + empresa.getComplemento());
				System.out.println("Telefone: " + empresa.getTelefone());
			}
		}
		if(opcao == 2) {
			System.out.println("Informe o nome da empresa: ");
			String nome = input.next();
			Empresa empresa = empresaDAO.localizarEmpresa(nome, 0);
			if (empresaDAO!=null) {
				System.out.println("\nDados da empresa: ");
				System.out.println("-------------------------");
				System.out.println("Código: " + empresa.getIdEmpresa());
				System.out.println("Nome: " + empresa.getNome());
				System.out.println("CPF: " + empresa.getCpfCnpj());
				System.out.println("Logradouro: " + empresa.getLogradouro());
				System.out.println("Número: " + empresa.getNrEndereco());
				System.out.println("Complemento: " + empresa.getComplemento());
				System.out.println("Telefone: " + empresa.getTelefone());
			}
		}
	}
	
	public static void listarEmpresa(Conexao con) {
		EmpresaDAO empresaDAO = new EmpresaDAO(con);
		
		empresaDAO.listarEmpresa();
	}
	
	public static void menuProduto(Conexao con) {
		int opcao;
		
		do {
			menuCRUD();
			opcao = informeOpcao("Informe uma opção: ");
			
			switch(opcao) {			
			case 1: incluirProduto(con); break;
			case 2: alterarProduto(con); break;
			case 3: excluirProduto(con); break;
			case 4: localizarProduto(con); break;
			case 5: listarProdutos(con); break;
			case 6: break;
			default: System.out.println("Opção inválida.");
			}	
		} while (opcao!=6);
	}
	
	public static void incluirProduto(Conexao con) {
		ProdutoDAO produtoDAO = new ProdutoDAO(con);
		
		Produto produto = new Produto();
		
		System.out.println("\n--- Dados do produto ---");
		System.out.println("Informe o nome: ");
		String nome = input.next();
		System.out.println("Informe a descrição: ");
		String descricao = input.next();
		System.out.println("Informe o custo: ");
		double custo = input.nextDouble();
		System.out.println("Informe o preço unitário: ");
		double precoUnitario = input.nextDouble();
		System.out.println("Informe o estoque: ");
		int estoque = input.nextInt();
		
		produto.setNome(nome);
		produto.setDescricao(descricao);
		produto.setCusto(custo);
		produto.setPrecoUnitario(precoUnitario);
		produto.setEstoque(estoque);
		
		produtoDAO.incluirProduto(produto);
	}
	
	public static void alterarProduto(Conexao con) {
		ProdutoDAO produtoDAO = new ProdutoDAO(con);
		
		String nome;
		String descricao;
		double custo;
		double precoUnitario;
		int estoque;
		
		int codigo = informeOpcao("\nInforme o ID do produto: "); 
		
		Produto produto = produtoDAO.localizarProduto(null, codigo);
		
		if (produto!=null) {
			System.out.println("\nDados do produto: ");
			System.out.println("-------------------------");
			System.out.println("Código: " + produto.getIdProduto());
			System.out.println("Nome: " + produto.getNome());
			System.out.println("Custo: " + produto.getCusto());
			System.out.println("Preço unitário: " + produto.getPrecoUnitario());
			System.out.println("Estoque: " + produto.getEstoque());
			
			System.out.println("\nAltere o nome: ");
			nome = input.next();
			produto.setNome(nome);
			
			System.out.println("Altere a descrição: ");
			descricao = input.next();
			produto.setDescricao(descricao);
			
			System.out.println("Altere o custo: ");
			custo = input.nextDouble();
			produto.setCusto(custo);
			
			System.out.println("Altere o preço unitário: ");
			precoUnitario = input.nextDouble();
			produto.setPrecoUnitario(precoUnitario);
			
			System.out.println("Informe o estoque: ");
			estoque = input.nextInt();
			produto.setEstoque(estoque);
			
			produtoDAO.alterarProduto(produto);
		}
	}
	
	public static void excluirProduto(Conexao con) {
		ProdutoDAO produtoDAO = new ProdutoDAO(con);
		
		int codigo = informeOpcao("\nInforme o ID do produto: "); 
		
		Produto produto = produtoDAO.localizarProduto(null, codigo);
		
		if (produto != null) {
			produtoDAO.apagarProduto(produto.getIdProduto());
		}
	}
	
	public static void localizarProduto(Conexao con) {
		ProdutoDAO produtoDAO = new ProdutoDAO(con);
		
		int opcao;
		
		System.out.println("Buscar produto por ID (1) ou nome (2)?");
		opcao = input.nextInt();
		
		if(opcao == 1) {	
			System.out.println("Informe o ID do produto: ");
			int codigo = input.nextInt();
			Produto produto = produtoDAO.localizarProduto(null, codigo);
			if (produto!=null) {
				System.out.println("\nDados do produto: ");
				System.out.println("-------------------------");
				System.out.println("Código: " + produto.getIdProduto());
				System.out.println("Nome: " + produto.getNome());
				System.out.println("Descrição: " + produto.getDescricao());
				System.out.println("Custo: " + produto.getCusto());
				System.out.println("Preço unitário: " + produto.getPrecoUnitario());
				System.out.println("Estoque: " + produto.getEstoque());
				
				System.out.println("\nExibir pedidos deste produto?:");
				System.out.println("1: Sim");
				System.out.println("0: Não");
				opcao = input.nextInt();
					
				switch(opcao) {
				case 1: produtoDAO.localizarPedidosProdutoID(codigo); break;
				case 0: System.out.println("Voltando"); break;
				default: System.out.println("Opção inválida!");
				} 
			}
		}
		if(opcao == 2) {
			System.out.println("Informe o nome do produto: ");
			String nome = input.next();
			Produto produto = produtoDAO.localizarProduto(nome, 0);
			if (produto!=null) {
				System.out.println("\nDados do produto: ");
				System.out.println("-------------------------");
				System.out.println("Código: " + produto.getIdProduto());
				System.out.println("Nome: " + produto.getNome());
				System.out.println("Descrição: " + produto.getDescricao());
				System.out.println("Custo: " + produto.getCusto());
				System.out.println("Preço unitário: " + produto.getPrecoUnitario());
				System.out.println("Estoque: " + produto.getEstoque());
				
				System.out.println("\nExibir pedidos deste produto?:");
				System.out.println("1: Sim");
				System.out.println("0: Não");
				opcao = input.nextInt();
					
				switch(opcao) {
				case 1: produtoDAO.localizarPedidosProdutoNome(nome); break;
				case 0: System.out.println("Voltando"); break;
				default: System.out.println("Opção inválida!");
				} 
			}
		}

	}
	
	public static void listarProdutos(Conexao con) {
		ProdutoDAO produtoDAO = new ProdutoDAO(con);
		
		produtoDAO.listarProdutos();
	}
	
	public static void menuPedido(Conexao con) {
		int opcao;
		
		do {
			menuCRUD();
			opcao = informeOpcao("Informe uma opção: ");
			
			switch(opcao) {			
			case 1: incluirPedido(con); break;
			case 2: alterarPedido(con); break;
			case 3: excluirPedido(con); break;
			case 4: localizarPedido(con); break;
			case 5: listarPedidos(con); break;
			case 6: break;
			default: System.out.println("Opção inválida.");
			}	
		} while (opcao!=6);
	}
	
	public static void incluirPedido(Conexao con) {
		PedidoDAO pedidoDAO = new PedidoDAO(con);
		Pedido pedido = new Pedido();
		
		System.out.println("--- Dados do pedido ---");
		System.out.println("Informe o ID do cliente: ");
		int idCliente = input.nextInt();
		System.out.println("Informe o ID da empresa: ");
		int idEmpresa = input.nextInt();
		System.out.println("Informe a data do pedido (dd/mm/aaaa): ");
		String data = input.next();
		
		pedido.setIdCliente(idCliente);
		pedido.setIdEmpresa(idEmpresa);
		pedido.setData(data);
		
		pedidoDAO.incluirPedido(pedido);
		
		DadosPedidoDAO dadosPedidoDAO = new DadosPedidoDAO(con);
		DadosPedido dadosPedido = new DadosPedido();
		System.out.println("\nDeseja cadastrar produtos para o pedido agora?");
		int opcao;
		do {
			System.out.println("0: Não adicionar produtos ao pedido");
			System.out.println("1: Adicionar produtos ao pedido ");
			opcao = input.nextInt();
			switch(opcao) {
			case 0: System.out.println("Voltando"); break;
			case 1: 
				pedidoDAO.ListarPedidoSimples();
				System.out.println("--- Adicionar produtos ao pedido ---");
				System.out.println("Informe o ID do pedido (consultar acima): ");
				int idPedido = input.nextInt();
				System.out.println("Informe o ID do produto: ");
				int idProduto = input.nextInt();
				System.out.println("Informe a quantidade: ");
				int quantidade = input.nextInt();

				dadosPedido.setIdPedido(idPedido);
				dadosPedido.setIdProduto(idProduto);
				dadosPedido.setQuantidade(quantidade);
				
				dadosPedidoDAO.incluirDadosPedido(dadosPedido);
				dadosPedidoDAO.updatePreco(idProduto);
				pedidoDAO.updateTotal(idPedido);
			break;
			default: System.out.println("Opção inválida!");
			} 
		} while (opcao!=0);

	}
	
	public static void incluirDadosPedido(Conexao con) {
		DadosPedidoDAO dadosPedidoDAO = new DadosPedidoDAO(con);
		PedidoDAO pedidodao = new PedidoDAO(con);
		DadosPedido dadosPedido = new DadosPedido();
		
		int opcao;
		do {
			System.out.println("\nEscolha uma opção (0/1):");
			System.out.println("0: Voltar");
			System.out.println("1: Adicionar produtos ao pedido ");
			opcao = input.nextInt();
			
			switch(opcao) {
			case 0: System.out.println("Voltando"); break;
			case 1: 
				pedidodao.ListarPedidoSimples();
				System.out.println("--- Adicionar produtos ao pedido ---");
				System.out.println("Informe o ID do pedido (consultar acima): ");
				int idPedido = input.nextInt();
				System.out.println("Informe o ID do produto: ");
				int idProduto = input.nextInt();
				System.out.println("Informe a quantidade: ");
				int quantidade = input.nextInt();

				dadosPedido.setIdPedido(idPedido);
				dadosPedido.setIdProduto(idProduto);
				dadosPedido.setQuantidade(quantidade);
				
				dadosPedidoDAO.incluirDadosPedido(dadosPedido);
				dadosPedidoDAO.updatePreco(idProduto);
				pedidodao.updateTotal(idPedido);
			break;
			default: System.out.println("Opção inválida!");
			} 
		} while (opcao!=0);
		
	}
	
	public static void alterarPedido(Conexao con) {
		PedidoDAO pedidoDAO = new PedidoDAO(con);
		
		int codigo = informeOpcao("\nInforme o ID do pedido: "); 
		
		Pedido pedido = pedidoDAO.localizarPedido(null, codigo);
		
		if (pedido!=null) {
			System.out.println("\nDados do pedido: ");
			System.out.println("-------------------------");
			System.out.println("Código: " + pedido.getIdPedido());
			System.out.println("IdEmpresa: " + pedido.getIdEmpresa());
			System.out.println("IdCliente: " + pedido.getIdCliente());
			System.out.println("Data: " + pedido.getData());
			System.out.println("Total: " + pedido.getTotal());
			
			System.out.println("\nAltere o cliente (digite o ID): ");
			int idCliente = input.nextInt();
			pedido.setIdCliente(idCliente);
			
			System.out.println("Altere a empresa (digite o ID): ");
			int idEmpresa = input.nextInt();
			pedido.setIdEmpresa(idEmpresa);
			
			System.out.println("Altere a data (dd/mm/aaaa): ");
			String data = input.next();
			pedido.setData(data);
			
			System.out.println("Altere o total: ");
			Double total = input.nextDouble();
			pedido.setTotal(total);
			
			pedidoDAO.alterarPedido(pedido);
		}
		
	}

	public static void localizarPedido(Conexao con) {
		PedidoDAO pedidoDAO = new PedidoDAO(con);
		int opcao;
		
		System.out.println("Buscar pedido por ID (1) ou data (2)?");
		opcao = input.nextInt();
		
		if(opcao == 1) {	
			System.out.println("Informe o ID do pedido: ");
			int codigo = input.nextInt();
			Pedido pedido = pedidoDAO.localizarPedido(null, codigo);
			if (pedido!=null) {
				System.out.println("\nDados do pedido: ");
				System.out.println("-------------------------");
				System.out.println("Código: " + pedido.getIdPedido());
				System.out.println("IdEmpresa: " + pedido.getIdEmpresa());
				System.out.println("IdCliente: " + pedido.getIdCliente());
				System.out.println("Data: " + pedido.getData());
				
				System.out.println("\nExibir dados completos deste pedido?:");
				System.out.println("1: Sim");
				System.out.println("0: Não");
				opcao = input.nextInt();
					
				switch(opcao) {
				case 1: pedidoDAO.localizarPedidosID(codigo); break;
				case 0: System.out.println("Voltando"); break;
				default: System.out.println("Opção inválida!");
				} 
			}
		}
		if(opcao == 2) {
			System.out.println("Informe a data (dd/mm/aaaa) do pedido: ");
			String data = input.next();
			Pedido pedido = pedidoDAO.localizarPedido(data, 0);
			if (pedido!=null) {
				System.out.println("\nDados do pedido: ");
				System.out.println("-------------------------");
				System.out.println("Código: " + pedido.getIdPedido());
				System.out.println("IdEmpresa: " + pedido.getIdEmpresa());
				System.out.println("IdCliente: " + pedido.getIdCliente());
				System.out.println("Data: " + pedido.getData());
				
				System.out.println("\nExibir dados completos deste pedido?:");
				System.out.println("1: Sim");
				System.out.println("0: Não");
				opcao = input.nextInt();
					
				switch(opcao) {
				case 1: pedidoDAO.localizarPedidosData(data); break;
				case 0: System.out.println("Voltando"); break;
				default: System.out.println("Opção inválida!");
				} 
			}
		}

	}
	
	public static void listarPedidos(Conexao con) {
		PedidoDAO pedidoDAO = new PedidoDAO(con);
		
		pedidoDAO.listarPedido();
	}
	
	public static void excluirPedido(Conexao con) {
		PedidoDAO pedidoDAO = new PedidoDAO(con);
		
		int codigo = informeOpcao("\nInforme ID do pedido: "); 
		
		pedidoDAO.apagarPedido(codigo);
	}
	
}
