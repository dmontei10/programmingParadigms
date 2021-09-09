package banco;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banco {
	
    public static int Nr_Contas;                                 // Variavel que indica o numero de contas
    public static double Balanco_Inicial;
    public static double Max_Transferencia;
   
	private ContaBanco[] contas = new ContaBanco[Nr_Contas];
 
    private Lock LockBanco;
    private Condition fundosSuficientes;
   
    public Banco() {
    	
        for (int i = 0; i < contas.length; i++) {
            contas[i] = new ContaBanco(Balanco_Inicial);
        }
 
        LockBanco = new ReentrantLock();
        fundosSuficientes = LockBanco.newCondition();
        
    }
    
    public void transfer(int contaOrigem, int contaDestino, double valorTransfer) {
    	
    	LockBanco.lock();
        try {
        	while (contas[contaOrigem].getBalanco() < valorTransfer) {
                fundosSuficientes.await();
            }
        	
        	contas[contaOrigem].levantamento(valorTransfer);
        	contas[contaDestino].deposito(valorTransfer);
                
            System.err.println("\n-------------------------------------------------------------\n");
            String saida = "%s\nTransferir %.2f € da conta nº %s para a conta nº %s\n";
            String threadName = Thread.currentThread().getName();
            System.err.printf(saida, threadName, valorTransfer, (contaOrigem + 1), (contaDestino + 1));
            System.err.printf("Saldo Conta nº %s: %.2f €\n", (contaOrigem + 1), contas[contaOrigem].getBalanco());
            System.err.printf("Saldo Conta nº %s: %.2f €\n", (contaDestino + 1), contas[contaDestino].getBalanco());
            System.err.printf("Saldo final banco (total): %.2f €\n", getBalancoTotal());
            
            fundosSuficientes.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
       
        } finally {
            LockBanco.unlock();
        }
    }
 
    public static void setMax_Transferencia(double max_Transferencia) {
		Max_Transferencia = max_Transferencia;
	}

	public static void setNr_Contas(int nr_Contas) {
		Nr_Contas = nr_Contas;
	}

	public static void setBalanco_Inicial(double balanco_Inicial) {
		Balanco_Inicial = balanco_Inicial;
	}

    public double getBalancoTotal() {
        LockBanco.lock();
 
        try {
            double total = 0;
 
            for (int i = 0; i < contas.length; i++) {
                total += contas[i].getBalanco();
            }
 
            return total;
        } finally {
            LockBanco.unlock();
        }
    }
    
}

