package br.com.serratec;

import java.util.Scanner;

public class Menu {
	
	public static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		
		int opcao;
		
		do {
			System.out.print("\nEscolha um exercicio (1/2/3/4/5/6/7): ");
			opcao = input.nextInt();
			
			switch (opcao) {
				case 0: System.out.println("Programa finalizado."); break;
				case 1: Exercicio01.ex01(); break;
				case 2: Exercicio02.ex02(); break;
				case 3: Exercicio03.ex03(); break;
				case 4: Exercicio04.ex04(); break;
				case 5: Exercicio05.ex05(); break;
				case 6: exercicio06.ex06(); break;
				case 7: Exercicio07.ex07(); break;
				default: System.out.println("Opção inválida.");
			}
		} while (opcao != 0);
	}
}