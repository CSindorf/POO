package br.com.serratec;

public class Exercicio01 {
	
	public static void ex01() {
		double nota1, nota2, nota3, media;
		
		nota1 = 8.5;
		nota2 = 7.5;
		nota3 = 6.0;
		media = ((nota1*3) + (nota2*2) + (nota3*5))/10;
		
		System.out.println("\nExercício 1\nVeja a média de um aluno que tirou as notas 8.5 peso 3, 7.5 peso 2 e 6 peso5\n");
		System.out.println("A média do aluno é: " + media);
	}
}