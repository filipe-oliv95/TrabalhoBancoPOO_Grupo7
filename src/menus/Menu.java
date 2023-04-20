package menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import contas.Conta;
import contas.ContaCorrente;
import pessoas.Cliente;
import pessoas.Funcionario;
import principal.SistemaBancario;
import relatorios.Relatorio;

public class Menu {

	public static void menuInicial() {

		Scanner sc = new Scanner(System.in);
		Cliente cliente;
		Conta conta;
		// Usuario usuario;
		Funcionario funcionario;
		int senha = 0;
		do {
			System.out.println();
			System.out.println("================================");
			System.out.println("=== BEM VINDO AO BANCO JAVA ====");
			System.out.print("[ Digite seu CPF ]:");
			String cpf = sc.nextLine(); // CPF do usuário
			System.out.print("[ Digite sua senha ]:");
			senha = sc.nextInt();
			sc.nextLine();
			System.out.println("================================");

			cliente = SistemaBancario.mapaDeClientes.get(cpf);
			conta = SistemaBancario.mapaDeContas.get(cpf);
			funcionario = SistemaBancario.mapaDeFuncionarios.get(cpf);
			List<Conta> listaContas = new ArrayList<>(SistemaBancario.mapaDeContas.values());

			if (conta != null && funcionario != null) {
				if (funcionario.getSenha() == senha) {
					menuFuncionario(funcionario, conta, listaContas, cpf, cliente);
				}
			} else if (conta != null && cliente != null) {
				if (cliente.getSenha() == senha) {
					System.out.println("Cliente");
					Menu.menuCliente(conta, cliente);
				} else {
					System.out.println("DADOS INCORRETOS. Digite novamente \n");
				}
			}
		} while (conta == null || cliente == null || cliente.getSenha() != senha || funcionario.getSenha() != senha);

		sc.close();

	}

	public static void menuFuncionario(Funcionario funcionario, Conta conta, List<Conta> listaContas, String cpf, Cliente cliente) {
		Scanner sc = new Scanner(System.in);

		int opcao = 0;
		System.out.println();
		System.out.println("Olá " + funcionario.getTipoDeUsuario().getTipoPessoa());
		do {
			System.out.println("Escolha entre as opções abaixo:");
			System.out.println("[1] Entrar como cliente");
			System.out.println("[2] Entrar como " + funcionario.getTipoDeUsuario().getTipoPessoa());
			System.out.println("[3] Logout");
		opcao = sc.nextInt();

			switch (opcao) { // MENU CLIENTE PARA FUNCIONARIO

			case 1:
				Menu.menuCliente(conta, cliente);
				break;
			case 2: // MENU DO FUNCIONÁRIO
				switch (funcionario.getTipoDeUsuario()) {
				case GERENTE:
					System.out.println();
					System.out.println("Escolha entre as opções abaixo:");
					System.out.println("[1] Consulta de contas da agência ");
					System.out.println("[2] Retornar ao menu anterior");
					opcao = sc.nextInt();
					switch (opcao) {
					//ADICIONAR TRY CATCH
					case 1: 
						Relatorio.numDeContasNaAgencia(cpf);
						menuFuncionario(funcionario, conta, listaContas, cpf, cliente);
						break;
					case 2:
						menuFuncionario(funcionario, conta, listaContas, cpf, cliente);
						break;
					}
					break;

				case DIRETOR:
					System.out.println();
					System.out.println("Escolha entre as opções abaixo:");
					System.out.println("[1] Relatório de informações dos clientes do banco");
					System.out.println("[2] Retornar ao menu anterior");
					opcao = sc.nextInt();
					switch (opcao) {
					//ADICIONAR TRY CATCH
					case 1:
						Relatorio.informacoesClientes(listaContas);
						menuFuncionario(funcionario, conta, listaContas, cpf, cliente);
						break;
					case 2:
						menuFuncionario(funcionario, conta, listaContas, cpf, cliente);
						break;
					}
					break;
					
				case PRESIDENTE:
					System.out.println();
					System.out.println("Escolha entre as opções abaixo:");
					System.out.println("[1] Relatório de informações dos clientes do banco");
					System.out.println("[2] Relatório valor total armazenado");
					System.out.println("[3] Retornar ao menu anterior");
					opcao = sc.nextInt();
					switch (opcao) {
					case 1:
						Relatorio.informacoesClientes(listaContas);
						menuFuncionario(funcionario, conta, listaContas, cpf, cliente);
						break;
					case 2:
						Relatorio.valorTotalCapitalBanco(listaContas);
						menuFuncionario(funcionario, conta, listaContas, cpf, cliente);
						break;
					case 3:
						menuFuncionario(funcionario, conta, listaContas, cpf, cliente);
						break;
					}
					break;
					
				default:
					break;
				}
			case 3:
				menuInicial();
				break;
			}
		} while (!(opcao == 0)); // PENSAR EM UMA OUTRO SOLUÇÃO

		sc.close();

	}

	public static void menuCliente(Conta conta, Cliente cliente) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);

		System.out.println();
		System.out.println("Olá " + cliente.getNome() + "!");
		System.out.println("Escolha entre as opções abaixo:");
		System.out.println("[1] Saque");
		System.out.println("[2] Depósito");
		System.out.println("[3] Transferência para outra conta");
		System.out.println("[4] Extrato da conta");
		System.out.println("[5] Relatórios");
		System.out.println("[6] Logout");

		int opcao = sc.nextInt();
		// adicionar o retorno
		switch (opcao) {
		case 1:
			System.out.print("Insira o valor do saque: ");
			double valor = sc.nextDouble();
			conta.sacar(valor);
			System.out.println("SAQUE REALIZADO COM SUCESSO, saldo: " + conta.getSaldo()); // TESTE
			menuCliente(conta, cliente);
			break;
		case 2:
			System.out.println("Quanto você deseja depositar?");
			valor = sc.nextDouble();
			conta.depositar(valor);
			System.out.printf("Novo saldo: %.2f", conta.getSaldo());
			System.out.println();
			menuCliente(conta, cliente);
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
			System.out.println();
			menuCliente(conta, cliente);
			break;
		case 4:
			conta.imprimeExtrato();
			menuCliente(conta, cliente);
			break;
		case 5:
			menuRelatorio(conta);
			break;
		case 6:
			menuInicial();
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
		System.out.println("[5] Logout");

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
			case 4:
				System.out.println("DESAFIO");
			default:
			case 5:
				menuInicial();
				break;
			}
		} while (opcao < 1 || opcao > 4);

		sc.close();
	}

}