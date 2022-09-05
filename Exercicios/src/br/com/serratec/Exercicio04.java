/*
Imprima os 15 primeiros números da série de Fibonacci. 

A série de Fibonacci tem os seguintes elementos: 0, 1, 1, 2, 3, 5, 8, 13, 21, etc. 
Para calculá-la, o primeiro e segundo elementos valem 1,e daí por diante, 
o n-ésimo elemento vale (n-1)-ésimo elemento somado ao (n-2)-ésimo elemento (ex: 8 = 5 + 3).
*/
package br.com.serratec;

public class Exercicio04 {
	
	public static void ex04() {
	    
		System.out.println("\nExerc�cio 4\nVeja a sequ�ncia Fibonacci at� o d�gito 15\n");
		fibonacci(15);
	}
	
	public static void fibonacci(int num) {
		System.currentTimeMillis();
		int n1 = 1;
	    int n2 = 0;
	      
	    for (int i=1; i<num; i++){
	    	if (i<2){
	    		System.out.println(n1 + 1);
	    	
	    	} else {
	    		n1 = n1 + n2;
	    		n2 = n1 - n2;
	    		System.out.println(n1);
	    	  }
	    }
	}
	          
}