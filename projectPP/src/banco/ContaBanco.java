package banco;

public class ContaBanco {                        // Conta do banco.
	
	private double balanco = 0;                  // Cada conta inicializa a zero
	 
    public ContaBanco(double balanco) {          // Constructor da conta, composto pelo balanço.
        this.balanco = balanco;
    }
 
    public void deposito(double montante) {      // Método depósito, que coloca o valor do montante na conta.
        this.balanco += montante;
    }
    
    public void levantamento(double montante) {  // Método levantamento, que retira o montante da conta.
        this.balanco -= montante;
    }

    public double getBalanco() {                 // Método getBalanco, que mostra o valor disponivel na conta.
        return this.balanco;
    }
}