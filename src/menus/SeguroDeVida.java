package menus;

import java.util.Scanner;

import contas.Conta;
import io.Escritor;
import pessoas.Cliente;
import principal.SistemaBancario;
import relatorios.Relatorio;

public class SeguroDeVida {

	private final static double SEGURO_VIDA = 20.0;
	private static double RESULTADO;
	private static double VALOR_SALDO;
	private static  double VALOR;
	private static double SEGURO;
	
	public static void SeguroVida(Conta conta , Cliente cliente) {
		
		Scanner sc = new Scanner(System.in);
		
		//Conta con = SistemaBancario.mapaDeContas.get(conta);
		
		System.out.print("Informe o valor que deseja adicionar ao seu seguro: R$ " );
		VALOR = sc.nextDouble();
		
		RESULTADO = conta.getSaldo() - VALOR;
		
		VALOR_SALDO = VALOR * (SEGURO_VIDA / 100.0);
		
		SEGURO = VALOR - VALOR_SALDO;
		
		RESULTADO = VALOR * (SEGURO_VIDA / 100.0);
		
    	VALOR_SALDO = VALOR - RESULTADO;
		
		System.out.println(RESULTADO);//teste de pocentagem...
		//Escritor.SeguroDeVida(conta, cliente);
		Relatorio.SeguroDeVida(conta, cliente);
	}

	public static double getRESULTADO() {
		return RESULTADO;
	}

	public static void setRESULTADO(double rESULTADO) {
		RESULTADO = rESULTADO;
	}

	public static double getVALOR_SALDO() {
		return VALOR_SALDO;
	}

	public static void setVALOR_SALDO(double vALOR_SALDO) {
		VALOR_SALDO = vALOR_SALDO;
	}

	public static double getVALOR() {
		return VALOR;
	}

	public static void setVALOR(double vALOR) {
		VALOR = vALOR;
	}

	public static double getSeguroVida() {
		return SEGURO_VIDA;
	}

	public static double getSEGURO() {
		return SEGURO;
	}

	public static void setSEGURO(double sEGURO) {
		SEGURO = sEGURO;
	}

	
}
