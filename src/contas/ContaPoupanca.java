package contas;

import java.util.HashMap;
import java.util.Map;

import agencias.Agencia;
import contas.enums.ContasEnum;
import pessoas.Cliente;

public class ContaPoupanca extends Conta {
    
    private static final double TAXA_RENDIMENTO = 0.002; 
    
    public static Map<String, ContaPoupanca> mapaContasPoupanca = new HashMap<>();
    
    public ContaPoupanca() {
        super();
    }

    public ContaPoupanca(Agencia agencia, String numConta, Cliente titular, String cpf, double saldoInicial,
            ContasEnum tipoDeConta) {
        super(agencia, numConta, titular, cpf, saldoInicial, tipoDeConta);
    }
 
    
    public static double getTaxaRendimento() {
		return TAXA_RENDIMENTO;
	}

	@Override
    public void depositar(double valor) {
        saldo += valor;
    }
    
    @Override
    public String toString() {
        return  "Tipo de conta = " + getTipoDeConta()
                + ", Agencia = " + getAgencia() 
                + ", Titular = "    + getTitular()
                + ", Numero = " + getNumConta() 
                + ", Saldo = " + getSaldo()
                + ", rendimento = " + TAXA_RENDIMENTO;
    }
}