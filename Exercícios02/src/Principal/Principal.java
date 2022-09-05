package Principal;

import java.util.Scanner;

import Dados.*;
import java.util.Scanner;

public class Principal {
	
	public static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		Cliente c = new Cliente();
		Fornecedor f = new Fornecedor();
		
		System.out.println("--- Dados Cliente ---");
		dadosCliente(c);
		
		System.out.println("\n--- Dados Fornecedor ---");
		dadosFornecedor(f);

	}
	
	public static void dadosCliente(Cliente c) {
		
		System.out.println("Pegando do abstract:");
		c.andar();
		c.comer();
		c.correr();
		c.falar();
		
		System.out.print("\nInforme o nome do cliente: ");
		String nome = input.next();
		c.setNome(nome);
		
		System.out.print("Informe o endereço do cliente: ");
		String endereco = input.next();
		c.setEndereco(endereco);
		
		System.out.print("Informe o telefone do cliente: ");
		String telefone = input.next();
		c.setTelefone(telefone);
		
		System.out.print("Informe a idade do cliente: ");
		int idade = input.nextInt();
		c.setIdade(idade);
		
		System.out.print("Informe o sexo do cliente: ");
		String sexo = input.next();
		c.setSexo(sexo);
		
		System.out.print("Informe o cpf do cliente: ");
		String cpf = input.next();
		c.setCpf(cpf);
		
		System.out.println("\nImpressão dos dados");
		System.out.println("Nome: " + c.getNome());
		System.out.println("Endereço: " + c.getEndereco());
		System.out.println("Telefone: " + c.getTelefone());
		System.out.println("Idade: " + c.getIdade());
		System.out.println("Sexo: " + c.getSexo());
		System.out.println("Cpf: " + c.getCpf());

		System.out.print("\nPegando da interface: ");
		c.dados();
	}

	public static void dadosFornecedor(Fornecedor f) {
		
		System.out.println("Pegando do abstract:");
		f.andar();
		f.comer();
		f.correr();
		f.falar();
		
		System.out.print("\nInforme o nome do fornecedor: ");
		String nome = input.next();
		f.setNome(nome);
		
		System.out.print("Informe o endereco do fornecedor: ");
		String endereco = input.next();
		f.setEndereco(endereco);
		
		System.out.print("Informe o telefone do fornecedor: ");
		String telefone = input.next();
		f.setTelefone(telefone);
		
		System.out.print("Informe a idade do fornecedor: ");
		int idade = input.nextInt();
		f.setIdade(idade);
		
		System.out.print("Informe o sexo do fornecedor: ");
		String sexo = input.next();
		f.setSexo(sexo);
		
		System.out.print("Informe o cpf do fornecedor: ");
		String cpf = input.next();
		f.setCpf(cpf);
		
		System.out.println("\nImpressão dos dados:");
		System.out.println("Nome: " + f.getNome());
		System.out.println("Endereço: " + f.getEndereco());
		System.out.println("Telefone: " + f.getTelefone());
		System.out.println("Idade: " + f.getIdade());
		System.out.println("Sexo: " + f.getSexo());
		System.out.println("Cpf: " + f.getCpf());

		System.out.println("\nPegando da interface:");
		f.dados();
	}

}
