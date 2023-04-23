package contas;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import agencias.Agencia;
import contas.enums.ContasEnum;
import extratos.Extrato;
import pessoas.Cliente;

public class ContaPoupanca extends Conta {

	private static final double TAXA_RENDIMENTO_MES = 0.005;

	public ContaPoupanca() {
		super();
	}

	public ContaPoupanca(ContasEnum tipoDeConta, Agencia agencia, String numConta, Cliente titular, String cpf,
			double saldoInicial) {
		super(tipoDeConta, agencia, numConta, titular, cpf, saldoInicial);
	}

	public static double getTaxaRendimento() {
		return TAXA_RENDIMENTO_MES;
	}

	@Override
	public void debitarSeguro(double valor, Conta conta, Cliente cliente) {
		if (this.saldo < valor) {
			System.out.println("Saldo insuficiente");
		} else if (valor < 0) {
			System.out.println("Insira um valor válido.");
		} else {
			this.saldo -= valor;
		}
	}

	@Override
	public void imprimeExtrato(Conta conta) {
		System.out.println();
		System.out.println("************* Extrato da Conta Poupança *************");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		System.out.println("Titular: " + this.getTitular().getNome() + ", CPF: " + Cliente.imprimeCPF(getCpf()));
		System.out.println("Agência: " + getAgencia().getNumAgencia() + ", Número da conta: " + getNumConta());
		System.out.println();
		for (Extrato cc : getlistaDeMovimentacoes()) {
			System.out.println(cc);
		}
		System.out.println();
		System.out.printf("Saldo atualizado: R$%.2f%n", conta.getSaldo());
		System.out.println("Data: " + sdf.format(date));
		System.out.println("****************************************************");
	}

	@Override
	public String toString() {
		return "Agencia = " + getAgencia() + ", Titular = " + getTitular() + ", Numero = " + getNumConta()
				+ ", Saldo = " + getSaldo();
	}

	@Override
	public void sacar(double valor, Conta conta) {
		if (this.saldo < valor) {
			System.out.println("Saldo insuficiente");
		} else if (valor < 0) {
			System.out.println("Insira um valor válido.");
		} else {
			this.saldo -= valor;
			System.out.println("Saque realizado com sucesso.");
			Extrato saque = new Extrato(LocalDate.now(), "Saque", valor);
			conta.getlistaDeMovimentacoes().add(saque);
		}

	}

	@Override
	public void transferir(Conta contaDestino, double valor, Conta conta) {
		if (this.saldo < valor) {
			System.out.println("Seu saldo é insuficiente!");
		} else if (valor < 0) {
			System.out.println("Insira um valor válido.");
		} else {
			this.saldo -= valor;
			contaDestino.saldo += valor;
			Extrato transferencia = new Extrato(LocalDate.now(), "Transferêcia", valor);
			conta.getlistaDeMovimentacoes().add(transferencia);
		}
	}

	@Override
	public void depositar(double valor, Conta conta) {
		if (valor > 0) {
			saldo += valor;
			Extrato deposito = new Extrato(LocalDate.now(), "Depósito", valor);
			conta.getlistaDeMovimentacoes().add(deposito);
		} else {
			System.out.println("Insira um valor válido.");
		}
	}

}