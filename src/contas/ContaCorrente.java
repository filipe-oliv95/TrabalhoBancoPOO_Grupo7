package contas;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import agencias.Agencia;
import contas.enums.ContasEnum;
import pessoas.Cliente;

public class ContaCorrente extends Conta {

	private final static double TARIFA_SAQUE = 0.1;
	private final static double TARIFA_DEPOSITO = 0.1;
	private final static double TARIFA_TRANSFERENCIA = 0.2;
	private double totalTributado;

	public static Map<String, ContaCorrente> mapaCCorrente = new HashMap<>();

	public ContaCorrente() {
		super();
	}

	public ContaCorrente(Agencia agencia, String numConta, Cliente titular, String cpf, double saldoInicial,
			ContasEnum tipoDeConta) {
		super(agencia, numConta, titular, cpf, saldoInicial, tipoDeConta);
	}	
	
	public double getTotalTributado() {
		return totalTributado;
	}

	public void setTotalTributado(double totalTributado) {
		this.totalTributado = totalTributado;
	}

	@Override
	public void sacar(double valor) {
		if(this.saldo < valor + TARIFA_SAQUE) {
			System.out.println("Saldo insuficiente");
		}
		else {
			this.saldo -= valor + TARIFA_SAQUE;
		}
	}
	
	@Override
	public void depositar(double valor) {
		// verificar se o saldo que está fazendo deposito é maior que o tributo
		saldo += valor - TARIFA_DEPOSITO;
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