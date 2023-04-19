package contas;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import agencias.Agencia;
import contas.enums.ContasEnum;
import pessoas.Cliente;

public class ContaPoupanca extends Conta {
    
    private static final double TAXA_RENDIMENTO_MES = 0.5; 
    
    public static Map<String, ContaPoupanca> mapaContasPoupanca = new HashMap<>();
    
    public ContaPoupanca() {
        super();
    }
       
	public ContaPoupanca(ContasEnum tipoDeConta, Agencia agencia, String numConta, Cliente titular, String cpf, double saldoInicial) {
        super(tipoDeConta, agencia, numConta, titular, cpf, saldoInicial);
    }
 
    
    public static double getTaxaRendimento() {
		return TAXA_RENDIMENTO_MES;
	}

	@Override
    public void depositar(double valor) {
        saldo += valor;
    }
	
	@Override
	public void imprimeExtrato() {
		System.out.println("#### Extrato da Conta Poupança ####");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/aaaa HH:mm:ss");
		Date date = new Date();
		System.out.println("Titular: " + this.getTitular());
		System.out.println("Número da conta: " + getNumConta ());
		System.out.println("Saldo: " + String.format("R$ %.2f%n", this.saldo));
		System.out.println("Data: " + sdf.format(date));
	}
    
    @Override
    public String toString() {
        return  ", Agencia = " + getAgencia() 
                + ", Titular = "    + getTitular()
                + ", Numero = " + getNumConta() 
                + ", Saldo = " + getSaldo();
    }
}