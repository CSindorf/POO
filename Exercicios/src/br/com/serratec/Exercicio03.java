/*
O cálculo do fatorial de um número (!) segue a seguinte regra:

0! = 1
1! = 0! x 1
2! = 1! x 2
...
n! = (n -1)! x n

Escreva uma aplicação que efetua o cálculo do fatorial utilizando a forma iterativa (usando estruturas de repetição) 
e outra que efetua o mesmo cálculo, mas de forma recursiva (o método de cálculo do fatorial chama ele mesmo).
*/
package br.com.serratec;

public class Exercicio03 {
	
	public static void ex03() {

		System.out.println("\nExerc�cio 3\nVeja abaixo os fatoriais dos n�mero 5 e 6\n");
		//fatorial normal
		fatorial(5);
	       
		//fatorial recursivo
		int n = 6;
		System.out.println("!" + n + " = " + factorial(n));
	        
	}
	 
	//fatorial normal
	public static void fatorial(int num) {

		int fat = 1;
		int cont = 1;

		do{
			for(int i=1; i<=num; i++){
				fat = fat * i;
			}
	            
			System.out.println("!" + num + " = " + fat);
			cont++;
	            
		}while(cont < 2);
		
	}

	//fatorial recursivo
	public static long factorial(int n) {
        
		if(n==1) {
			return 1;
		}
		else {
			return n * factorial(n - 1);
		}

    }
}
