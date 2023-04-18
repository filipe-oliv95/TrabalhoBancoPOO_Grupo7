package contas;

import java.util.HashMap;
import java.util.Map;

import agencias.Agencia;
import contas.enums.ContasEnum;
import pessoas.Cliente;

public class ContaCorrente extends Conta {

	private final static double TARIFA_SAQUE = 0.1;
	private final static double TARIFA_DEPOSITO = 0.1;
	private final static double TARIFA_TRANSFERENCIA = 0.2;
	private static double totalSaques;
	private static double totalDepositos;
	private static double totalTransferencias;

	public static Map<String, ContaCorrente> mapaCCorrente = new HashMap<>();

	public ContaCorrente() {
		super();
	}

	public ContaCorrente(Agencia agencia, String numConta, Cliente titular, String cpf, double saldoInicial,
			ContasEnum tipoDeConta) {
		super(agencia, numConta, titular, cpf, saldoInicial, tipoDeConta);
	}	
	
	public static double getTARIFA_SAQUE() {
		return TARIFA_SAQUE;
	}

	public static double getTotalSaques() {
		return totalSaques;
	}

	public static double getTotalDepositos() {
		return totalDepositos;
	}

	public static double getTotalTransferencias() {
		return totalTransferencias;
	}

	public static double getTarifaDeposito() {
		return TARIFA_DEPOSITO;
	}

	public static double getTarifaTransferencia() {
		return TARIFA_TRANSFERENCIA;
	}

	@Override
	public void sacar(double valor) {
		if(this.saldo < valor + TARIFA_SAQUE) {
			System.out.println("Saldo insuficiente");
		}
		else {
			this.saldo -= valor + TARIFA_SAQUE;
			totalSaques ++;
		}
	}
	
	@Override
	public void depositar(double valor) {
		// verificar se o saldo que está fazendo deposito é maior que o tributo
		saldo += valor - TARIFA_DEPOSITO;
		totalDepositos ++;
	}
	

	@Override
	public void transferir (Conta contaDestino, double valor) {
		if(this.saldo < valor) {
	        System.out.println("Seu saldo é insuficiente!");
	     }
	     else {
	         this.saldo -= (valor + TARIFA_TRANSFERENCIA);
	         contaDestino.saldo += valor;
	         System.out.println("Seu saldo após transferência é de: " + this.saldo);
	         totalTransferencias ++;
	     }
	}	

//	@Override
//	public void imprimeExtrato() {
//		System.out.println("### Extrato da Conta Corrente ###");
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/aaaa HH:mm:ss");
//		Date date = new Date();
//		System.out.println("Titular: " + this.getTitular());
//		System.out.println("Número da conta: " + getNumConta());
//		System.out.println("Saldo: " + this.getSaldo());
//		System.out.println("Data: " + sdf.format(date));
//	}

	@Override
	public String toString() { //ALTERAR
		return "Tipo de conta = " + getTipoDeConta() 
					+ ", Agencia = " + getAgencia() 
					+ ", Titular = " + getTitular() 
					+ ", Numero = " + getNumConta()
					+ ", Saldo = " + getSaldo(); 
	}
}