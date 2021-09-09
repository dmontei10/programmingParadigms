package banco;

public class ContaBanco {                        // Conta do banco.
	
	private double balanco = 0;                  // Cada conta inicializa a zero
	 
    public ContaBanco(double balanco) {          // Constructor da conta, composto pelo balan�o.
        this.balanco = balanco;
    }
 
    public void deposito(double montante) {      // M�todo dep�sito, que coloca o valor do montante na conta.
        this.balanco += montante;
    }
    
    public void levantamento(double montante) {  // M�todo levantamento, que retira o montante da conta.
        this.balanco -= montante;
    }

    public double getBalanco() {                 // M�todo getBalanco, que mostra o valor disponivel na conta.
        return this.balanco;
    }
}