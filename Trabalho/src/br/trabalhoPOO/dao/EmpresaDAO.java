package br.trabalhoPOO.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.trabalhoPOO.classes.Empresa;
import br.trabalhoPOO.conexao.Conexao;

public class EmpresaDAO {

private Conexao conexao;
	
	PreparedStatement pInclusao; 
	
	public EmpresaDAO(Conexao conexao) { 
		this.conexao = conexao;
		prepararSqlInclusao();
	}
	
	private void prepararSqlInclusao() {
		String sql = "insert into vendas.empresa";
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
	
	public int incluirEmpresa(Empresa empresa) {
		try {
			pInclusao.setString(1, empresa.getNome());
			pInclusao.setString(2, empresa.getCpfCnpj());
			pInclusao.setString(3, empresa.getLogradouro());
			pInclusao.setString(4, empresa.getNrEndereco());
			pInclusao.setString(5, empresa.getComplemento());
			pInclusao.setString(6, empresa.getBairro());
			pInclusao.setString(7, empresa.getCidade());
			pInclusao.setString(8, empresa.getCep());
			pInclusao.setString(9, empresa.getTelefone());
			pInclusao.setString(10, empresa.getEmail());
			
			return pInclusao.executeUpdate();
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public void alterarEmpresa(Empresa empresa) {
		String sql = "update vendas.empresa set " +
				"nome = '" + empresa.getNome() + "'" +
				", cpfCnpj = '" + empresa.getCpfCnpj() + "'" +
				", logradouro = '" + empresa.getLogradouro() + "'" +
				", nrEndereco = '" + empresa.getNrEndereco() + "'" +
				", complemento = '" + empresa.getComplemento() + "'" +
				", bairro = '" + empresa.getBairro() + "'" +
				", cidade = '" + empresa.getCidade() + "'" +
				", cep = '" + empresa.getCep() + "'" +
				", telefone = '" + empresa.getTelefone() + "'" +
				", email = '" + empresa.getEmail() + "'" +
				"where idEmpresa = " + empresa.getIdEmpresa();
		conexao.query(sql);
	}
	
	public Empresa selecionarEmpresa(int idEmpresa) {
		Empresa empresa = new Empresa();
		ResultSet tabela;
		
		String sql = "select * from vendas.empresa where idEmpresa = " + idEmpresa;
		
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) {
				empresa.setIdEmpresa(tabela.getInt("idEmpresa"));
				empresa.setNome(tabela.getString("nome"));
				empresa.setCpfCnpj(tabela.getString("cpfCnpj"));
				empresa.setLogradouro(tabela.getString("logradouro"));
				empresa.setNrEndereco(tabela.getString("nrEndereco"));
				empresa.setComplemento(tabela.getString("complemento"));
				empresa.setBairro(tabela.getString("bairro"));
				empresa.setCidade(tabela.getString("cidade"));
				empresa.setCep(tabela.getString("cep"));
				empresa.setTelefone(tabela.getString("telefone"));
				empresa.setEmail(tabela.getString("email"));
			} else
				System.out.println("idEmpresa " + idEmpresa + " não localizado.");
			
			tabela.close();	
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		return empresa;
	}
	
	public void apagarEmpresa(int idEmpresa) {
		String sql = "delete from vendas.empresa" +
						" where idEmpresa = " + idEmpresa;
		
		conexao.query(sql);		
	}
	
	public Empresa localizarEmpresa(String nome, int idEmpresa) {
		Empresa empresa = new Empresa();
		
		String sql;
		ResultSet tabela;
		
		if (nome == null) {
			sql = "select * from vendas.empresa where idEmpresa = " + idEmpresa;
		} else
			sql = "select * from vendas.empresa where nome = '" + nome + "'";
		
		tabela = conexao.query(sql);
		
		try {
			if (tabela.next()) {
				empresa.setIdEmpresa(tabela.getInt("idEmpresa"));
				empresa.setNome(tabela.getString("nome"));
				empresa.setCpfCnpj(tabela.getString("cpfCnpj"));
				empresa.setLogradouro(tabela.getString("logradouro"));
				empresa.setNrEndereco(tabela.getString("nrEndereco"));
				empresa.setComplemento(tabela.getString("complemento"));
				empresa.setBairro(tabela.getString("bairro"));
				empresa.setCidade(tabela.getString("cidade"));
				empresa.setCep(tabela.getString("cep"));
				empresa.setTelefone(tabela.getString("telefone"));
				empresa.setEmail(tabela.getString("email"));
			} else {
				if (nome == null) {
					System.out.println("idEmpresa " + idEmpresa + " não localizado.");
				} else
					System.out.println("Empresa '" + nome + "' não localizada.");
				
				empresa = null;
			}
			
			tabela.close();	
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		return empresa;
	}
	
	public void listarEmpresa() {
		ResultSet tabela;		
		
		String sql = "select * from vendas.empresa order by idEmpresa";
		
		tabela = conexao.query(sql);
		
		try {
			tabela.last();
			int rowCount = tabela.getRow(); 
			System.out.println("Quantidade de empresas: " +rowCount);
			
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
						tabela.getInt("idEmpresa"),
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
