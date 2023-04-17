package contas;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import agencias.Agencia;
import contas.enums.ContasEnum;
import pessoas.Cliente;

public class ContaCorrente extends Conta {

	private Double tarifa;
	private Double limite;

	public static Map<String, ContaCorrente> mapaCCorrente = new HashMap<>();

	public ContaCorrente() {
		super();
	}

	public ContaCorrente(Agencia agencia, String numConta, Cliente titular, String cpf, double saldoInicial,
			ContasEnum tipoDeConta, Double tarifa, Double limite) {
		super(agencia, numConta, titular, cpf, saldoInicial, tipoDeConta);
		this.tarifa = tarifa;
		this.limite = limite;
	}	

	public double getTarifa() {
		return tarifa;
	}

	public void setTarifa(Double tarifa) {
		this.tarifa = tarifa;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(Double limite) {
		this.limite = limite;
	}	

	@Override
	public void sacar(double valor) {
		this.saldo = super.saldo + limite;
		if(this.saldo < valor + tarifa) {
			System.out.println("Saldo insuficiente");
		}
		else {
			this.saldo -= valor + tarifa;
		}
	}
	
	@Override
	public void depositar(double valor) {
		saldo += saldo + valor; // alterar
	}
	

	@Override
	public void transferir (Conta contaDestino, double valor) {
		this.saldo = super.saldo + limite; 
		if(this.saldo < valor) {
	        System.out.println("Seu saldo é insuficiente!");
	     }
	     else {
	         this.saldo -= valor;
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
		System.out.println("Limite: " + this.limite);
		System.out.println("Data: " + sdf.format(date));
	}

	@Override
	public String toString() {
		return "Tipo de conta = " + getTipoDeConta() 
					+ ", Agencia = " + getAgencia() 
					+ ", Titular = " + getTitular() 
					+ ", Numero = " + getNumConta()
					+ ", Saldo = " + getSaldo() 
					+ ", Tarifa = " + tarifa 
					+ ", Limite = " + limite;
	}

}
