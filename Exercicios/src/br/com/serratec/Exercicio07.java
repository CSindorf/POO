/*
Crie uma aplica��o que possibilita a entrada via console de um conjunto de notas de um aluno.
Assim que -1 for informado como nota, calcule a m�dia das notas informadas anteriormente e mostre na tela. 
Caso a nota do aluno seja invv�lida, mostre uma mensagem na tela solicitando uma nova nota.
*/

package br.com.serratec;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercicio07 {
	
	public static void ex07() {
		
		Scanner scanner = new Scanner(System.in);

		List<Double> notas = new ArrayList<>();

		double somaDasNotas = 0;

		System.out.println("\nExerc�cio 7\nDigite notas para um aluno e veja a m�dia. Para parar, digite -1\n");
		System.out.println("Informe as notas do aluno");

		while (true) {
			System.out.println("Informe a nota " + (notas.size() + 1) + " do aluno: ");
			try {
				double nota = scanner.nextDouble();
				notas.add(nota);
				if (nota == -1) {
					break;
				}
			} catch (Exception e) {
				System.out.println("Voc� informou uma nota inv�lida, informe novamente.");
				scanner.nextLine();
			}
		}

		scanner.close();

		for (double nota : notas) {
			somaDasNotas += nota;
		}

		System.out.println("A m�dia do aluno �: " + (somaDasNotas / notas.size()));
	}

}
