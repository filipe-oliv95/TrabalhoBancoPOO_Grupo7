package relatorios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import contas.Conta;
import contas.ContaCorrente;
import contas.ContaPoupanca;
import pessoas.Gerente;

public class Relatorio {

	public static void imprimirSaldo(Conta conta) { // RELATORIO CLIENTE e GERENTE
		// O sistema deverá imprimir o saldo na tela do terminal;
		System.out.println("Saldo: " + conta.getSaldo());
		
	}

	public static void tributacaoCC(ContaCorrente conta) { // RELATORIO CLIENTE e GERENTE
//		 Relatório de tributação da conta corrente , total gastos de tributação e tarifa de cada operação
		 System.out.println("Valor de tarifa cobrado no saque: R$" + ContaCorrente.getTARIFA_SAQUE());
		 System.out.println("Total de saques: " + ContaCorrente.getTotalSaques());
		 System.out.println("Valor de tarifa cobrado no depósito: R$" + ContaCorrente.getTarifaDeposito());
		 System.out.println("Total de depósitos: " + ContaCorrente.getTotalDepositos());
		 System.out.println("Valor de tarifa cobrado na tranferência: R$" + ContaCorrente.getTarifaTransferencia());
		 System.out.println("Total de transferências: " + ContaCorrente.getTotalTransferencias());
		 System.out.println();
		 
		 double tarifaTotalSaque = ContaCorrente.getTotalSaques() * ContaCorrente.getTARIFA_SAQUE();
		 double tarifaTotalDeposito = ContaCorrente.getTotalDepositos() * ContaCorrente.getTarifaDeposito();
		 double tarifaTotalTransferencia = ContaCorrente.getTotalTransferencias() * ContaCorrente.getTarifaTransferencia();
		 System.out.println("Total de tarifas cobradas em saques: R$" + tarifaTotalSaque);
		 System.out.println("Total de tarifas cobradas em depósitos: R$" + tarifaTotalDeposito);
		 System.out.println("Total de tarifas cobradas em transferências: R$" + tarifaTotalTransferencia);
		 double somaTarifas = tarifaTotalSaque + tarifaTotalDeposito + tarifaTotalTransferencia;
		 System.out.println("Soma de todas as tarifas: R$" + somaTarifas);
		 
		 // ESCRITOR AQUI
	}

	public static void simularRendimentoPoupanca() { // RELATORIO CLIENTE e GERENTE
		Scanner sc = new Scanner(System.in);
		// Relatório de Rendimento da poupança, simulação do valor de rendimento da
		System.out.println("Qual valor deseja simular? ");
		double valor = sc.nextDouble();
		System.out.println("Quantos dias deseja saber o rendimento? ");
		int dias = sc.nextInt();
		
		double rendimento = valor * dias * ContaPoupanca.getTaxaRendimento();
		System.out.printf("O rendimento para o prazo informado: %.2f%n", rendimento);
		System.out.printf("Valor final ficaria: %.2f", valor + rendimento);
		
		// ESCRITOR AQUI
	}

	public static void numDeContasNaAgencia(List<Conta> contas, String cpf) { // RELATORIO GERENTE
		// Relatório do número contas na mesma agência em que este gerente trabalha.
		// ADICIONAR UM COMANDO PARA O LEITOR E ESCRITOR

		Gerente gerente = Gerente.mapaDeGerentes.get(cpf);
		int totalContas = 0;
		for (String cpf2 : Conta.mapaDeContas.keySet()) {
			if (Conta.mapaDeContas.get(cpf2).getAgencia() == gerente.getAgencia()) { //conta do gerente
				System.out.println(Conta.mapaDeContas.get(cpf2));
				totalContas++;
			}
		}
		System.out.println("Total de contas: " + totalContas); 

	}

	public static void informacoesClientes(List<Conta> contas) { // RELATORIO DIRETOR
		// Nome, CPF, Agencia de todos clientes do Sistema (em ordem alfabética)
						
	
	}

	private double totalCapital = 0;

	public void imprimirCapital() { // RELATORIO PRESIDENTE
		// Relatório com o valor total do capital armazenado no banco
	} // somatório dos SALDOs das contas e somatório das tarifas

}