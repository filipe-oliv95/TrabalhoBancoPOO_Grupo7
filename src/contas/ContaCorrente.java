package contas;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import agencias.Agencia;
import contas.enums.ContasEnum;
import extratos.Extrato;
import menus.Menu;
import pessoas.Cliente;
import relatorios.Relatorio;
import segurosDeVida.SeguroDeVida;

public class ContaCorrente extends Conta {

	private final static double TARIFA_SAQUE = 0.1;
	private final static double TARIFA_DEPOSITO = 0.1;
	private final static double TARIFA_TRANSFERENCIA = 0.2;
	private double totalSaques;
	private double totalDepositos;
	private double totalTransferencias;

	public ContaCorrente() {
		super();
	}

	public ContaCorrente(ContasEnum tipoDeConta, Agencia agencia, String numConta, Cliente titular, String cpf,
			double saldoInicial) {
		super(tipoDeConta, agencia, numConta, titular, cpf, saldoInicial);
	}

	public double getTotalSaques() {
		return totalSaques;
	}

	public void setTotalSaques(double totalSaques) {
		this.totalSaques = totalSaques;
	}

	public double getTotalDepositos() {
		return totalDepositos;
	}

	public void setTotalDepositos(double totalDepositos) {
		this.totalDepositos = totalDepositos;
	}

	public double getTotalTransferencias() {
		return totalTransferencias;
	}

	public void setTotalTransferencias(double totalTransferencias) {
		this.totalTransferencias = totalTransferencias;
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

	public double getTotalTarifas() {
		double totalTarifaDeposito = TARIFA_DEPOSITO * totalDepositos;
		double totalTarifaSaque = TARIFA_SAQUE * totalSaques;
		double totalTarifaTransferencia = TARIFA_TRANSFERENCIA * totalTransferencias;
		double totalTarifas = totalTarifaDeposito + totalTarifaSaque + totalTarifaTransferencia;
		return totalTarifas;
	}

	@Override
	public void sacar(double valor, Conta conta) {
		if (this.saldo < valor + TARIFA_SAQUE) {
			System.out.println("Saldo insuficiente");
		} else if (valor < 0) {
			System.out.println("Insira um valor válido.");
		} else {
			this.saldo -= valor + TARIFA_SAQUE;
			totalSaques++;
			System.out.println("Saque realizado com sucesso.");
			Extrato saque = new Extrato(LocalDate.now(), "Saque", valor);
			conta.getlistaDeMovimentacoes().add(saque);
		}
	}

	public void debitarSeguro(double valor, Conta conta, Cliente cliente) {
		if (this.saldo < valor) {
			System.out.println("Saldo insuficiente");
		} else if (valor < 0) {
			System.out.println("Insira um valor válido.");
		} else {
			this.saldo -= valor;
			Extrato seguroVida = new Extrato(LocalDate.now(), "Seguro de Vida", valor);
			conta.getlistaDeMovimentacoes().add(seguroVida);
			Relatorio.SeguroDeVida(conta, cliente);
		}
	}

	@Override
	public void depositar(double valor, Conta conta) {
		if (valor > 0) {
			saldo += valor - TARIFA_DEPOSITO;
			totalDepositos++;
			Extrato deposito = new Extrato(LocalDate.now(), "Depósito", valor);
			conta.getlistaDeMovimentacoes().add(deposito);
		} else {
			System.out.println("Valor inválido");
		}
	}

	@Override
	public void transferir(Conta contaDestino, double valor, Conta conta) {

		if (this.saldo < valor) {
			System.out.println("Seu saldo é insuficiente!");
		} else if (valor < 0) {
			System.out.println("Insira um valor válido.");
		} else {
			this.saldo -= (valor + TARIFA_TRANSFERENCIA);
			contaDestino.saldo += valor;
			totalTransferencias++;
			Extrato transferencia = new Extrato(LocalDate.now(), "Transferêcia", valor);
			conta.getlistaDeMovimentacoes().add(transferencia);
		}
	}

	@Override
	public void imprimeExtrato(Conta conta) {
		System.out.println();
		System.out.println("**************** Extrato da Conta Corrente ****************");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		System.out.println("Titular: " + this.getTitular().getNome() + ", CPF: " + Cliente.imprimeCPF(getCpf()));
		System.out.println("Agência: " + getAgencia().getNumAgencia() + ", Número da conta: " + getNumConta());
		System.out.println();

		for (Extrato cc : getlistaDeMovimentacoes()) {
			System.out.println(cc);
		}

		System.out.printf("Total gasto em tributos = R$ %.2f%n", ((ContaCorrente) conta).getTotalTarifas());

		if (Menu.contratoSeguro == true) {
			System.out.printf("O valor do seguro após tributação = R$ %.2f%n", SeguroDeVida.getValorSeguroAposTaxa());
		}

		System.out.println();
		System.out.printf("Saldo atualizado: R$ %.2f%n", conta.getSaldo());
		System.out.println("Data: " + sdf.format(date));
		System.out.println("**********************************************************");
	}

	@Override
	public String toString() {
		return "Agencia = " + getAgencia() + ", Titular = " + getTitular() + ", Numero = " + getNumConta()
				+ ", Saldo = " + getSaldo();
	}

}