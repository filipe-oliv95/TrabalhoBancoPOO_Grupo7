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

	public ContaCorrente() {
		super();
	}

	public ContaCorrente(ContasEnum tipoDeConta, Agencia agencia, String numConta, Cliente titular, String cpf,
			double saldoInicial) {
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
		if (this.saldo < valor + TARIFA_SAQUE) {
			System.out.println("Saldo insuficiente");
		} else if (valor < 0) {
			System.out.println("Insira um valor válido.");
		} else {
			this.saldo -= valor + TARIFA_SAQUE;
			totalSaques++;
			System.out.println("Saque realizado com sucesso.");
		}
	}
	
	public void debitarSeguro(double valor) {
		if (this.saldo < valor) {
			System.out.println("Saldo insuficiente");
		} else if (valor < 0) {
			System.out.println("Insira um valor válido.");
		} else {
			this.saldo -= valor;
		}
	}

	@Override
	public void depositar(double valor) {
		if (valor > 0) {
			saldo += valor - TARIFA_DEPOSITO;
			totalDepositos++;
		} else {
			System.out.println("Valor inválido");
		}
	}

	@Override
	public void transferir(Conta contaDestino, double valor) {

		if (this.saldo < valor) {
			System.out.println("Seu saldo é insuficiente!");
		} else if (valor < 0) {
			System.out.println("Insira um valor válido.");
		} else {
			this.saldo -= (valor + TARIFA_TRANSFERENCIA);
			contaDestino.saldo += valor;
			totalTransferencias++;
		}
	}

	@Override
	public void imprimeExtrato(Conta conta) {
		System.out.println();
		System.out.println("******** Extrato da Conta Corrente ********");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		System.out.println("Titular: " + this.getTitular().getNome());
		System.out.println("CPF: " + this.getTitular().getCpf());
		System.out.println("Número da conta: " + getNumConta());
		System.out.println("Saldo: R$" + this.getSaldo());
		System.out.println("Data: " + sdf.format(date));
		System.out.println("******************************************");
	}

	@Override
	public String toString() { // ALTERAR
		return "Agencia = " + getAgencia() + ", Titular = " + getTitular() + ", Numero = " + getNumConta()
				+ ", Saldo = " + getSaldo();
	}
}