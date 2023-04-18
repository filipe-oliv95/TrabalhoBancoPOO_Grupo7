package contas;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import agencias.Agencia;
import contas.enums.ContasEnum;
import pessoas.Cliente;

public class ContaCorrente extends Conta {

	private final double tarifaSaque = 0.1;
	private final double tarifaDeposito = 0.1;
	private final double tarifaTransferencia = 0.2;

	public static Map<String, ContaCorrente> mapaCCorrente = new HashMap<>();

	public ContaCorrente() {
		super();
	}

	public ContaCorrente(Agencia agencia, String numConta, Cliente titular, String cpf, double saldoInicial,
			ContasEnum tipoDeConta) {
		super(agencia, numConta, titular, cpf, saldoInicial, tipoDeConta);
	}	

	@Override
	public void sacar(double valor) {
		this.saldo = super.saldo;
		if(this.saldo < valor + tarifaSaque) {
			System.out.println("Saldo insuficiente");
		}
		else {
			this.saldo -= valor + tarifaSaque;
		}
	}
	
	@Override
	public void depositar(double valor) {
		saldo += valor - tarifaDeposito;
	}
	

	@Override
	public void transferir (Conta contaDestino, double valor) {
		if(this.saldo < valor) {
	        System.out.println("Seu saldo é insuficiente!");
	     }
	     else {
	         this.saldo -= (valor + tarifaTransferencia);
	         contaDestino.saldo += valor;
	         System.out.println("Seu saldo após transferência é de: " + this.saldo);
	     }
	}	

	@Override
	public void imprimeExtrato() {
		System.out.println("### Extrato da Conta Corrente ###");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/aaaa HH:mm:ss");
		Date date = new Date();
		System.out.println("Titular: " + this.getTitular());
		System.out.println("Número da conta: " + getNumConta());
		System.out.println("Saldo: " + this.getSaldo());
		System.out.println("Data: " + sdf.format(date));
	}

	@Override
	public String toString() { //ALTERAR
		return "Tipo de conta = " + getTipoDeConta() 
					+ ", Agencia = " + getAgencia() 
					+ ", Titular = " + getTitular() 
					+ ", Numero = " + getNumConta()
					+ ", Saldo = " + getSaldo(); 
	}

}
