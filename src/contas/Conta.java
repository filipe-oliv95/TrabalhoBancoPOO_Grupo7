package contas;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import agencias.Agencia;
import contas.enums.ContasEnum;
import extratos.Extrato;
import operacoes.Operacao;
import pessoas.Cliente;

public abstract class Conta implements Extrato, Operacao, Comparable<Conta> {
	
	private Agencia agencia;  
	private String numConta;
	private Cliente titular;
	private String cpf;
	protected double saldo;  // deixei tipo primitivo pois dá para ver os erros (senão dá nulo)
	private ContasEnum tipoDeConta;  
	
	public static Map<String, Conta> mapaDeContas = new HashMap<>();
	
	public Conta() {

	}
		
	public Conta(Agencia agencia, String numConta, Cliente titular, String cpf, double saldo, ContasEnum tipoDeConta) {
		this.agencia = agencia;
		this.numConta = numConta;
		this.titular = titular;
		this.cpf = cpf;
		this.saldo = saldo;
		this.tipoDeConta = tipoDeConta;
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
	
	public void depositar(double valor) {
		saldo += valor;
	}

	public void sacar(double valor) {
		if(this.saldo < valor) {
			System.out.println("Saldo insuficiente");
		}
		else {
			this.saldo -= valor;
		}
	}
	
	public void transferir (Conta contaDestino, double valor) {
		 if(this.saldo < valor) {
	        System.out.println("Seu saldo é insuficiente!");
	     }
	     else {
	         this.saldo -= valor;
	         contaDestino.saldo += valor;
	     }
	}
	
	@Override
	public int compareTo(Conta c) {
		if (this.getTitular().compareTo(c.getTitular()) > 0) { // compara pelo nome
			return -1; 
		}
		if (this.getTitular().compareTo(c.getTitular()) < 0) {
			return 1;
		} 
		return 0;
	}
	
	@Override
	public void imprimeExtrato() {
		System.out.println("### Extrato da Conta " + tipoDeConta + " ###");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/aaaa HH:mm:ss");
		Date date = new Date();
		System.out.println("Titular: " + this.getTitular());
		System.out.println("Número da conta: " + getNumConta ());
		System.out.println("Saldo: " + String.format("R$ %.2f%n", this.saldo));
		System.out.println("Data: " + sdf.format(date));
	}
	
	@Override
	public String toString() {
		return "Numero = " + getNumConta()
						+ ", Agencia = " + agencia 
						+ ", titular = " + titular 
						+ ", cpf = " + cpf
						+ ", saldo = " + saldo;
	}
}