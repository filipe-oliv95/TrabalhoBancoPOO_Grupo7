package contas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import agencias.Agencia;
import contas.enums.ContasEnum;
import extratos.Extrato;
import operacoes.Operacao;
import pessoas.Cliente;

public abstract class Conta implements Operacao, Comparable<Conta> {

	private Agencia agencia;
	private String numConta;
	private Cliente titular;
	private String cpf;
	protected double saldo;
	private ContasEnum tipoDeConta;

	private List<Extrato> listaDeMovimentacoes = new ArrayList<>();

	public Conta() {

	}

	public Conta(ContasEnum tipoDeConta, Agencia agencia, String numConta, Cliente titular, String cpf, double saldo) {
		this.tipoDeConta = tipoDeConta;
		this.agencia = agencia;
		this.numConta = numConta;
		this.titular = titular;
		this.cpf = cpf;
		this.saldo = saldo;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public String getNumConta() {
		return numConta;
	}

	public void setNumConta(String numConta) {
		this.numConta = numConta;
	}

	public Cliente getTitular() {
		return titular;
	}

	public void setTitular(Cliente titular) {
		this.titular = titular;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public ContasEnum getTipoDeConta() {
		return tipoDeConta;
	}

	public void setTipoDeConta(ContasEnum tipoDeConta) {
		this.tipoDeConta = tipoDeConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public List<Extrato> getlistaDeMovimentacoes() {
		return listaDeMovimentacoes;
	}

	public void depositar(double valor, Conta conta) {
		if (valor > 0) {
			saldo += valor;
			Extrato deposito = new Extrato(LocalDate.now(), "Depósito", valor);
			conta.getlistaDeMovimentacoes().add(deposito);
		} else {
			System.out.println("Insira um valor válido.");
		}
	}

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

	public abstract void debitarSeguro(double valor, Conta conta, Cliente cliente);

	public String imprimeCPF(String CPF) {
		return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-"
				+ CPF.substring(9, 11));
	}

	@Override
	public int compareTo(Conta cont) {
		if (this.getTitular().compareTo(cont.getTitular()) > 0) {
			return -1;
		}
		if (this.getTitular().compareTo(cont.getTitular()) < 0) {
			return 1;
		}
		return 0;
	}

	public abstract void imprimeExtrato(Conta conta);

	@Override
	public String toString() {
		return "Numero = " + getNumConta() + ", agencia = " + agencia + ", titular = " + titular + ", cpf = " + cpf
				+ ", saldo = " + saldo;
	}

}
