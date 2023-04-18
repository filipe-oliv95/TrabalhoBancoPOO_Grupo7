package relatorios;

import java.util.List;

import contas.Conta;
import contas.ContaCorrente;
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
	}

	public void rendimentoPoup() { // RELATORIO CLIENTE e GERENTE
		// Relatório de Rendimento da poupança, simulação do valor de rendimento da
		// poupança no prazo
		// valor dinheiro (double) e qtd de dias (inteiro) = rendimento no prazo
		// informado
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

	public void informacoesClientes() { // RELATORIO DIRETOR
		// Nome, CPF, Agencia de todos clientes do Sistema (em ordem alfabética)
	}

	private double totalCapital = 0;

	public void imprimirCapital() { // RELATORIO PRESIDENTE
		// Relatório com o valor total do capital armazenado no banco
	} // somatório dos SALDOs das contas e somatório das tarifas

}