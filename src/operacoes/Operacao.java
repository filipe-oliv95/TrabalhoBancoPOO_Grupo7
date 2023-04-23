package operacoes;

import contas.Conta;

public interface Operacao {
	
	  public void sacar(double valor, Conta conta);
	  
	  public void transferir(Conta contaDestino, double valor, Conta conta);
	  
	  public void depositar(double valor, Conta conta);
}
