package br.com.serratec;

public class Exercicio02 {

	public static void ex02() {
		
		System.out.println("\nExerc�cio 2\nVeja abaixo os fatoriais dos n�mero 5 e 6\n");
		
		funcao1(25);
		
		System.out.println("-------------");
		
		funcao2(100);
		
		System.out.println("-------------");
		
		funcao3();
		
		System.out.println("-------------");
		
		tabuada(9);
	}
	
	public static void funcao1(int num) {
		
		System.out.println("Imprima todos os n�meros inteiros de 10 a 25 utilizando uma estrutura de repeti��o");
		
		for (int i=10; i<=num; i++) {
			System.out.print(i);
		}
	}
	
	public static void funcao2(int num) {
		
		int soma = 0;
		
		System.out.println("Imprima a soma dos n�meros de 1 a 100, pulando de dois em dois (1, 3, 5, 7, etc)");
		
		for (int i=1; i<=num; i+=2) {
			soma += i;
			System.out.println(soma);
		}
		//se n�o quiser mostrar a soma toda e mostrar s� o resultado final, joga o print aqui: System.out.println(soma);
	}
	
	public static void funcao3() {
		
		int soma = 0;
		
		System.out.println("Come�ando em 0, imprima os n�meros seguintes, enquanto a soma dos n�meros j� impressos for menor que 100");
		
		for (int i=0; i<=100; i++) {
			soma += i;
			if(soma<100) {
				System.out.println(i);
			}
		}
	}
	
	//faz e exibe a conta da tabuada
	public static void tabuada(int num) {

		System.out.println("Tabuada do 9 at� o d�cimo valor");
			
		for (int i=0; i<=10; i++) {
			System.out.println(num + "\t x \t" + i + "\t = " + num*i);
		}
	}
}
