package contas;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	private static double totalDeGastos; // ADIICONA TOTAL DE GASTOS COM TRIBUTACAO?

	public ContaCorrente() {
		super();
	}

	public ContaCorrente(ContasEnum tipoDeConta, Agencia agencia, String numConta, Cliente titular, String cpf, double saldoInicial) {
		super(tipoDeConta, agencia, numConta, titular, cpf, saldoInicial);
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
	
	public static double getTarifaSaque() {
		return TARIFA_SAQUE;
	}

	public static double getTarifaDeposito() {
		return TARIFA_DEPOSITO;
	}

	public static double getTarifaTransferencia() {
		return TARIFA_TRANSFERENCIA;
	}

	public static double getTotalTarifas() {
		double totalTarifaDeposito = TARIFA_DEPOSITO * totalDepositos;
		double totalTarifaSaque = TARIFA_SAQUE * totalSaques;
		double totalTarifaTransferencia = TARIFA_TRANSFERENCIA * totalTransferencias;
		double totalTarifas = totalTarifaDeposito + totalTarifaSaque + totalTarifaTransferencia;
		return totalTarifas;
	}

	@Override
	public void sacar(double valor) {
		if(this.saldo < valor + TARIFA_SAQUE) {
			System.out.println("Saldo insuficiente");
		}
		else {
			this.saldo -= valor + TARIFA_SAQUE;
			totalSaques ++;
		//	System.out.println("Seu saldo agora e : $" + saldo);
		}
	}
	
	@Override
	public void depositar(double valor) {
		// VERIFICAR se o saldo que está fazendo deposito é maior que o tributo?
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

	@Override
	public void imprimeExtrato() {
		System.out.println("### Extrato da Conta Corrente ###");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		System.out.println("Titular: " + this.getTitular());
		System.out.println("Número da conta: " + getNumConta());
		System.out.println("Saldo: " + this.getSaldo());
		System.out.println("Data: " + sdf.format(date));
	}

	@Override
	public String toString() { //ALTERAR
		return "Agencia = " + getAgencia() 
					+ ", Titular = " + getTitular() 
					+ ", Numero = " + getNumConta()
					+ ", Saldo = " + getSaldo(); 
	}
}