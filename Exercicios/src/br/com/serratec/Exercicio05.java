/*
Escreva um programa que imprime na saída os valores assumidos por x.
Esta variável x deve iniciar com algum valor escolhido por você.

Se x for par, x deve receber o valor dele mesmo somado com 5. 
Já se x for ímpar, x deve receber o valor dele multiplicado por 2.
O programa termina assim que x for maior que 1000.
Por exemplo: para x = 10, a saída deve ser: 15, 30, 35 ,70, 75, 150, 155, 310, 315, 630, 635, 1270

Crie este programa primeiro usando a estrutura de console if-else e depois usando a estrutura de controle switch.
*/

package br.com.serratec;

import java.util.Scanner;

public class Exercicio05 {
	
	public static Scanner input = new Scanner(System.in);
	
	public static void ex05() {

		int resto;
		
		System.out.println("\nExercício 5\nSaída de valores. Se o número for par ele soma 5, se for ímpar multiplica por 2\n");
		
	    System.out.println("Digite um número: ");
	    int num = input.nextInt();
	    
	    resto = (num % 2);
	    
	    System.out.println("\nResultado com if/else");
	    ifelse(num);
	    
	    System.out.println("---------------------------");
	    
	    System.out.println("Resultado com switch");
	    comSwitch(resto, num);
	}
	
	public static void ifelse(int num) {
		
		while(num<1000){
			if((num % 2) == 0) {
				System.out.println(num += 5);
			}
			else {
				System.out.println(num *= 2);
			}
		}
	}
	
	public static void comSwitch(int resto, int num) {
		
		switch(resto) {
		case 0:
			while(num<1000){
				if((num % 2) == 0) {
					System.out.println(num += 5);
				}
				else {
					System.out.println(num *= 2);
				}
			}
		case 1:
			while(num<1000){
				if((num % 2) == 0) {
					System.out.println(num += 5);
				}
				else {
					System.out.println(num *= 2);
				}
			}
		
		}
	}
}