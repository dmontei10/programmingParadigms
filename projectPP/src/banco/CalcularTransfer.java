package banco;

import java.util.Locale;            // Bibliotecas
import java.util.Scanner;

public class CalcularTransfer {     // Classe CalcularTransfer, que cont�m o m�todo main()
	
	public static final Scanner CONSOLE = new Scanner(System.in);     // Scanner que receber� os inputs

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		System.err.println("Estado Inicial: ");
		System.err.print("N�mero de contas: ");
		int numeroContas = CONSOLE.nextInt();                               // Coloca na vari�vel numeroContas o numero de contas do banco, definido pelo utilizador
		
		System.err.print("Saldo inicial m�ximo nas contas (at� � 2000): ");
		double valorContas = CONSOLE.nextDouble();                          // Coloca na vari�vel valorContas o valor inicial de cada conta
		
		while(valorContas > 2000){                                        
			System.err.println("Valor m�ximo permitido por conta excedido");
			System.err.println("Introduza uma valor at� 2000 �: ");
			valorContas = CONSOLE.nextDouble();                             // Ciclo que funciona se exceder o valor definido de 2000 euros 
		}
		
		double total = numeroContas * valorContas;                          
		System.err.println("Saldo inicial (total) = � " + total);           // Indica o valor do saldo total do banco: numero de contas x valor de cada conta
		double valorTrans = Math.random() * valorContas;                    // Define o valor m�ximo de cada transferencia
		
		Banco.setNr_Contas(numeroContas);                                   // Define no banco, o numero de contas
		Banco.setBalanco_Inicial(valorContas);                              // Define no banco, o valor inicial de cada conta
		Banco.setMax_Transferencia(valorTrans);                             // Define no banco, o valor maximo de cada transferencia
		
		Banco banco = new Banco();                                          // Cria um objecto bank, do tipo Banco;
		
        for (int i = 0; i < Banco.Nr_Contas; i++) {                         // Ciclo for, para criar as Threads e inicializ�-las
        	
        	Transferencia tr = new Transferencia(banco, i);                 // Cria um objecto tr, do tipo Transferencia, que tem como parametro o banco
            Thread t = new Thread(tr);                                      // Cria a Thread, uma vez que foi utilizado a interface Runnable()
            t.setName("Thread n� " + (i + 1));                                    // Define o nome da Thread
            t.start();                                                      // Inicia a Thread
        }
	}
}