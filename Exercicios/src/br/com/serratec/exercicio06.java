/*
Verifique a validade de uma data e mostre uma mensagem na tela dizendo se a data Ã© vÃ¡lida ou invÃ¡lida. 
Deve haver trÃªs variÃ¡veis para armazenar esta data: uma para o dia, outra para o mÃªs e outra para o ano. 
Considere que fevereiro pode ter somente 28 dias.
*/

package br.com.serratec;

import java.util.Scanner;

public class exercicio06 {
	
	public static Scanner input = new Scanner(System.in);

	public static void ex06() {

		System.out.println("\nExercício 6\nDigite uma data e veja se ela é válida\n");
	    System.out.println("Informe um dia: ");
	    int dia = input.nextInt();
	    System.out.println("Informe um mês: ");
	    int mes = input.nextInt();
	    System.out.println("Informe um ano: ");
	    int ano = input.nextInt();
	    
	    if(dia >= 1 && dia <= 31) {
	    	if(mes==1 && mes==3 && mes==5 && mes==7 && mes==8 && mes==10 && mes==12) {
	    		if(ano >1) {
	    	    	System.out.println("Data válida!");
	    	    }
		    }
	    }
	    
	    if(dia >= 1 && dia <= 30) {
	    	if(mes==4 && mes==6 && mes==9 && mes==11) {
	    		if(ano >1) {
	    	    	System.out.println("Data válida!");
	    	    }
		    }
	    }
	    
	    if(dia >= 1 && dia <= 28) {
	    	if(mes==2) {
	    		if(ano >1) {
	    	    	System.out.println("Data válida!");
	    	    }
		    }
	    }
	    
	    else {
	    	System.out.println("Data inválida!");
	    }
	    
	}

}
