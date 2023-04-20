package menus;

import java.util.Locale;
import java.util.Scanner;

import contas.Conta;
import contas.ContaCorrente;
import principal.SistemaBancario;
import relatorios.Relatorio;

public class Menu {

	public static void imprimirMenuCliente(Conta conta) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);

		System.out.println("Escolha entre as opções abaixo:");
		System.out.println("[1] Saque");
		System.out.println("[2] Depósito");
		System.out.println("[3] Transferência para outra conta");
		System.out.println("[4] Extrato da conta");
		System.out.println("[5] Relatórios");

		int opcao = sc.nextInt();
		// adicionar o retorno
		switch (opcao) {
		case 1:
			System.out.print("Insira o valor do saque: ");
			double valor = sc.nextDouble();
			conta.sacar(valor);
			System.out.println("SAQUE REALIZADO COM SUCESSO, saldo: " + conta.getSaldo()); // TESTE
			break;
		case 2:
			System.out.println("Quanto você deseja depositar?");
			valor = sc.nextDouble();
			conta.depositar(valor);
			System.out.printf("Novo saldo: %.2f", conta.getSaldo());
			break;
		case 3:
			System.out.println("Qual o valor da transferencia?");
			valor = sc.nextDouble();
			sc.nextLine();
			System.out.println("Qual o CPF do destinatário?");
			String cpf = sc.nextLine();

			Conta contaDestino = SistemaBancario.mapaDeContas.get(cpf);
			System.out.println("\nQuem é o titular da conta (Objeto Cliente dentro de Conta) => "
					+ contaDestino.getTitular().getNome());
			System.out.println(contaDestino + "\n");

			System.out.printf("Saldo destino antes de receber: %.2f%n", contaDestino.getSaldo());
			conta.transferir(contaDestino, valor);
			System.out.printf("Novo saldo: %.2f%n", contaDestino.getSaldo());
			break;
		case 4:
			conta.imprimeExtrato();
			break;
		case 5:
			menuRelatorio(conta);
			break;
		default:
			break;
		}
		sc.close();
	}
	
	public static void menuRelatorio(Conta conta) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[1] Saldo");
		System.out.println("[2] Relatório de tributação da conta corrente");
		System.out.println("[3] Relatório de Rendimento da poupança");
		System.out.println("[4] Desafio");
		
		int opcao = 0; 
		
		do {
			opcao = sc.nextInt();
			
			switch (opcao) {
			case 1:
				Relatorio.imprimirSaldo(conta);
				break;
			case 2:	
				Relatorio.tributacaoCC((ContaCorrente) conta);
				break;
			case 3: 
				Relatorio.simularRendimentoPoupanca();
				break;
			case 4: System.out.println("DESAFIO");
			default:
				break;
			}
		}while(opcao < 1 || opcao > 4);
		
		sc.close();
	}	
}