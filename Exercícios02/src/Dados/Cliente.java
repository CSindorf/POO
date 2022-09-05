package Dados;

import Abstract.*;

public class Cliente extends AbstractPessoa {
	Dados d = new Dados();
	
	@Override
	public void andar() {
		System.out.println("Cliente anda");
	}

	@Override
	public void comer() {
		System.out.println("Cliente come");
	}

	@Override
	public void correr() {
		System.out.println("Cliente corre");
	}

	@Override
	public void falar() {
		System.out.println("Cliente fala");
	}
	
	public void dadosCliente(String nome, String endereco, String telefone, int idade, String sexo, String cpf) {

		d.setNome(nome);
		d.setEndereco(endereco);
		d.setTelefone(telefone);
		d.setIdade(idade);
		d.setSexo(sexo);
		d.setCpf(cpf);
	}
	
	public String getNome() {
		return d.getNome();
	}
	public String setNome(String nome) {
		return this.d.nome = nome;
	}
	public String getEndereco() {
		return d.getEndereco();
	}
	public String setEndereco(String endereco) {
		return this.d.endereco = endereco;
	}
	public String getTelefone() {
		return d.getTelefone();
	}
	public String setTelefone(String telefone) {
		return this.d.telefone = telefone;
	}
	public int getIdade() {
		return d.getIdade();
	}
	public int setIdade(int idade) {
		return this.d.idade = idade;
	}
	public String getSexo() {
		return d.getSexo();
	}
	public String setSexo(String sexo) {
		return this.d.sexo = sexo;
	}
	public String getCpf() {
		return d.getCpf();
	}
	public String setCpf(String cpf) {
		return this.d.cpf = cpf;
	}
	
	public void dados() {
		Implementainterface d = new Implementainterface();
		
		d.incluir();
		d.alterar();
		d.excluir();
		d.imprimir();
	}
	
}
