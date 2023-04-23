package contas;

import java.text.SimpleDateFormat;
import java.util.Date;

import agencias.Agencia;
import contas.enums.ContasEnum;
import pessoas.Cliente;

public class ContaPoupanca extends Conta {
    
    private static final double TAXA_RENDIMENTO_MES = 0.005; 
    
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
	public void imprimeExtrato(Conta conta){
		System.out.println("***** Extrato da Conta Poupança ********");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		System.out.println("Titular: " + this.getTitular().getNome());
		System.out.println("CPF: " + this.getTitular().getCpf());
		System.out.println("Agência: " + getAgencia().getNumAgencia());
		System.out.println("Número da conta: " + getNumConta ());
		System.out.println("Saldo: R$ " + String.format(Double.toString(conta.getSaldo()), 2));
		System.out.println("Data: " + sdf.format(date));		
		System.out.println("************************************");
	}
    
    @Override
    public String toString() {
        return  "Agencia = " + getAgencia() 
                + ", Titular = "    + getTitular()
                + ", Numero = " + getNumConta() 
                + ", Saldo = " + getSaldo();
    }
}