package banco;

public class Transferencia implements Runnable{     // Classe Transferencia que implementa a interface Runnable 
	
	private Banco banco;
	private int contaOrigem;
	
	public Transferencia(Banco banco, int contaOrigem) {             // Constructor da Classe Transferencia
		this.banco = banco;
		this.contaOrigem = contaOrigem;
	}
	
	public void run() {                             // Método Run(), que cria um ciclo infinito para a criação de contas aleatórias e valores de transferencia
	 
		while(true){                                // Ciclo infinito para correr as Threads
	        	
			       // Gera conta aleatória de Origem
	        int contaDestino = (int) (Math.random() * Banco.Nr_Contas);      // Gera conta aleatória de Destino
	            
	        if (contaDestino == contaOrigem) continue;                       // Se as duas contas geradas forem iguais o programa continua à procura
	        
	        double valorTransfer = Math.random() * Banco.Max_Transferencia;  // Gera um valor de transferencia aleatorio entre 0 e o valor do saldo inicial de cada conta
	 
	        if (valorTransfer == 0) continue;                                // Se o valor de transferencia for igual a 0, o programa continua a gerar um valor aleatório de transferencia maxima
	            
	        banco.transfer(contaOrigem, contaDestino, valorTransfer);        // Chama o método transfer() da classe Banco, que efectua a transferencia duma conta para outra
	 
	        try {
	                Thread.sleep(100);                                       // Tempo de execução entre cada thread
	        } catch (InterruptedException e) {
	                e.printStackTrace();
	        }
	    }
	}
	
}