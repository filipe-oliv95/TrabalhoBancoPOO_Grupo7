package menus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import contas.Conta;
import contas.ContaCorrente;
import contas.enums.ContasEnum;
import io.Escritor;
import pessoas.Cliente;
import pessoas.Funcionario;
import principal.SistemaBancario;
import relatorios.Relatorio;
import segurosDeVida.SeguroDeVida;

public class Menu {

	public static boolean contratoSeguro = false;

	public static void menuEntrada() throws InputMismatchException, NullPointerException, IOException {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);

		try {
			System.out.println();
			System.out.println("********************************");
			System.out.println("*** BEM VINDO AO BANCO JAVA ****");
			System.out.println("********************************");
			System.out.println("[1] Login");
			System.out.println("[2] Encerrar");
			int opcao = sc.nextInt();
			switch (opcao) {
			case 1:
				menuInicial();
				break;
			case 2:
				System.out.println();
				System.out.println("Sistema encerrado.");
				System.exit(0);
				break;
			default:
				menuEntrada();
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println();
			System.out.println("Ocorreu um erro.");
			System.out.println("Possível solução para o erro:");
			System.out.println("- Insira apenas números com ou sem ponto (.)");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} finally {
			menuEntrada();
		}
		sc.close();
	}

	public static void menuInicial() throws InputMismatchException, IOException, NullPointerException {

		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		Cliente cliente;
		Conta conta;
		Funcionario funcionario;
		int senha = 0;

		try {
			do {
				System.out.println();
				System.out.println("******** Menu de Login **********");
				System.out.print("[ Digite seu CPF ]: ");
				String cpf = sc.nextLine();
				System.out.print("[ Digite sua senha ]: ");
				senha = sc.nextInt();
				sc.nextLine();
				System.out.println("**********************************");

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
						Menu.menuCliente(conta, cliente);
					} else {
						System.out.println("DADOS INCORRETOS. Digite novamente \n");
					}
				}
			} while (conta == null || cliente == null || cliente.getSenha() != senha
					|| funcionario.getSenha() != senha);
		}

		catch (InputMismatchException e) {
			System.out.println("Ocorreu um erro.");
			System.out.println("Possível solução para o erro:");
			System.out.println("- Insira apenas números com ou sem ponto (.)");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} finally {
			menuInicial();
		}
		sc.close();
	}

	public static void menuFuncionario(Funcionario funcionario, Conta conta, List<Conta> listaContas, String cpf,
			Cliente cliente) throws IOException, NullPointerException {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		int opcao = 0;
		System.out.println();
		System.out.println("Olá " + funcionario.getTipoDeUsuario().getTipoPessoa() + " " + funcionario.getNome() + ".");
		System.out.println();
		try {
			do {
				System.out.println("Escolha entre as opções abaixo:");
				System.out.println("[1] Entrar como Cliente");
				System.out.println("[2] Entrar como " + funcionario.getTipoDeUsuario().getTipoPessoa());
				System.out.println("[3] Logout");
				opcao = sc.nextInt();

				switch (opcao) {

				case 1:
					Menu.menuCliente(conta, cliente);
					break;
				case 2:
					switch (funcionario.getTipoDeUsuario()) {
					case GERENTE:
						System.out.println();
						System.out.println("******** Menu Gerente ********");
						System.out.println("Escolha entre as opções abaixo:");
						System.out.println("[1] Consulta de contas da sua agência ");
						System.out.println("[2] Retornar ao menu anterior");
						opcao = sc.nextInt();
						switch (opcao) {
						case 1:
							Relatorio.numDeContasNaAgencia(conta, cpf, funcionario);
							menuFuncionario(funcionario, conta, listaContas, cpf, cliente);
							break;
						case 2:
							menuFuncionario(funcionario, conta, listaContas, cpf, cliente);
							break;
						default:
							menuFuncionario(funcionario, conta, listaContas, cpf, cliente);
							break;
						}
						break;

					case DIRETOR:
						System.out.println();
						System.out.println("******** Menu Diretor ********");
						System.out.println("Escolha entre as opções abaixo:");
						System.out.println("[1] Relatório de informações dos clientes do banco");
						System.out.println("[2] Retornar ao menu anterior");
						opcao = sc.nextInt();
						switch (opcao) {
						case 1:
							Relatorio.informacoesClientes(listaContas, conta, funcionario);
							menuFuncionario(funcionario, conta, listaContas, cpf, cliente);
							break;
						case 2:
							menuFuncionario(funcionario, conta, listaContas, cpf, cliente);
							break;
						default:
							menuFuncionario(funcionario, conta, listaContas, cpf, cliente);
							break;
						}
						break;

					case PRESIDENTE:
						System.out.println();
						System.out.println("******** Menu Presidente ********");
						System.out.println("Escolha entre as opções abaixo:");
						System.out.println("[1] Relatório de informações dos clientes do banco");
						System.out.println("[2] Relatório do capital total armazenado");
						System.out.println("[3] Retornar ao menu anterior");
						opcao = sc.nextInt();
						switch (opcao) {
						case 1:
							System.out.println();
							Relatorio.informacoesClientes(listaContas, conta, funcionario);
							menuFuncionario(funcionario, conta, listaContas, cpf, cliente);
							break;
						case 2:
							System.out.println();
							Relatorio.valorTotalCapitalBanco(listaContas, conta, funcionario);
							menuFuncionario(funcionario, conta, listaContas, cpf, cliente);
							break;
						case 3:
							menuFuncionario(funcionario, conta, listaContas, cpf, cliente);
							break;
						default:
							menuFuncionario(funcionario, conta, listaContas, cpf, cliente);
							break;
						}
						break;

					default:
						break;
					}
				case 3:
					contratoSeguro = false;
					menuEntrada();
					break;
				}
			} while (opcao != 1 || opcao != 2 || opcao != 3);
		} catch (InputMismatchException e) {
			System.out.println("Ocorreu um erro.");
			System.out.println("Possíveis soluções para o erro:");
			System.out.println("- Insira apenas números com ou sem ponto (.)");
			System.out.println("- Digite números ao invés de letras");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} finally {
			menuFuncionario(funcionario, conta, listaContas, cpf, cliente);
		}
		sc.close();

	}

	public static void menuCliente(Conta conta, Cliente cliente) throws IOException, NullPointerException {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);

		try {
			System.out.println();
			System.out.println("******** Menu cliente ********\n");
			System.out.println("Olá " + cliente.getNome() + "!");
			System.out.println("Escolha entre as opções abaixo:");
			System.out.println("[1] Saque");
			System.out.println("[2] Depósito");
			System.out.println("[3] Transferência para outra conta");
			System.out.println("[4] Extrato da conta");
			System.out.println("[5] Relatórios e Saldo");
			System.out.println("[6] Logout");

			int opcao = sc.nextInt();
			switch (opcao) {
			case 1:
				System.out.print("Insira o valor do saque: R$ ");
				double valor = sc.nextDouble();

				conta.sacar(valor, conta);
				Escritor.comprovanteSaque(conta, valor);

				menuCliente(conta, cliente);
				break;
			case 2:
				System.out.printf("Insira o valor para depósito: R$ ");
				valor = sc.nextDouble();

				conta.depositar(valor, conta);

				Escritor.comprovanteDeposito(conta, valor); 
				System.out.printf("Saldo atual: R$ %.2f", conta.getSaldo());
				System.out.println();

				menuCliente(conta, cliente);
				break;
			case 3:
				System.out.printf("Insira o valor da transferencia: R$ ");
				valor = sc.nextDouble();
				if (valor < 0) {
					System.out.println("Insira um valor válido.");
					menuCliente(conta, cliente);
				}
				if (valor > conta.getSaldo()) {
					System.out.println("Saldo insuficiente.");
					menuCliente(conta, cliente);
				}
				sc.nextLine();

				System.out.printf("Insira o CPF do destinatário: ");
				String cpfDestinatario = sc.nextLine();

				while (cpfDestinatario.equals(conta.getCpf())) {
					System.out.println();
					System.out.println("Você não pode fazer transferência para si mesmo!");
					System.out.printf("Entre com um CPF válido: ");
					cpfDestinatario = sc.nextLine();
				}

				Conta contaDestino = SistemaBancario.mapaDeContas.get(cpfDestinatario);
				conta.transferir(contaDestino, valor, conta);
				Escritor.comprovanteTransferencia(conta, contaDestino, valor);

				System.out.println("Transferência realizada com sucesso.");
				menuCliente(conta, cliente);
				break;
			case 4:
				conta.imprimeExtrato(conta);
				Escritor.extratoConta(conta);
				menuCliente(conta, cliente);
				break;
			case 5:
				menuRelatorio(conta, cliente);
				break;
			case 6:
				contratoSeguro = false;
				menuEntrada();
				break;
			default:
				System.out.println();
				System.out.println("Opção não existe. Digite o número correto.");
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println();
			System.out.println("Ocorreu um erro na transferência.");
			System.out.println("Possível solução para o erro:");
			System.out.println("- Insira apenas números com ou sem ponto (.)");
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (NullPointerException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			menuCliente(conta, cliente);
		}
		sc.close();
	}

	public static void menuRelatorio(Conta conta, Cliente cliente) throws IOException, NullPointerException {
		System.out.println();
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		System.out.println("******** Menu relatório ********");
		System.out.println();
		System.out.println("Escolha entre as opções abaixo:");
		System.out.println("[1] Saldo");
		System.out.println("[2] Relatório de Tributação da Conta Corrente");
		System.out.println("[3] Relatório de Rendimento da Conta Poupança");
		System.out.println("[4] Seguro de vida");
		System.out.println("[5] Retornar ao menu anterior");

		int opcao = 0;

		try {
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				Relatorio.imprimirSaldo(conta);
				menuRelatorio(conta, cliente);
				break;
			case 2:
				if (conta.getTipoDeConta().equals(ContasEnum.CORRENTE)) {
					Relatorio.tributacaoCC((ContaCorrente) conta);
					Escritor.relatorioTributacaoCC((ContaCorrente) conta);
				} else {
					System.out.println();
					System.out.println("Desculpe, você não possui Conta Corrente.");
				}
				menuRelatorio(conta, cliente);
				break;
			case 3:
				if (conta.getTipoDeConta().equals(ContasEnum.POUPANCA)) {
					Relatorio.simularRendimentoPoupanca(conta, cliente);
				} else {
					System.out.println();
					System.out.println("Desculpe, você não possui Conta Poupança.");

					menuRelatorio(conta, cliente);
				}
				break;
			case 4:
				if (contratoSeguro == true) {
					System.out.println();
					System.out.println("Você ja contratou esse serviço.");
				} else if (conta.getTipoDeConta().equals(ContasEnum.CORRENTE)) {
					System.out
							.println("Ao realizar a contratação do seguro de vida, você pagará 20% do valor como taxa");
					System.out.println("Deseja contratar um seguro de vida? Sim [1] Não [2] ");

					do {
						opcao = sc.nextInt();
						switch (opcao) {
						case 1:
							System.out.print("Qual o valor que será segurado? R$ ");
							double valor = sc.nextDouble();
							while (valor < 0) {
								System.out.print("Insira um valor válido: R$ ");
								valor = sc.nextDouble();
							}
							SeguroDeVida.setValorSeguro(valor);
							conta.debitarSeguro(valor, conta, cliente);
							contratoSeguro = true;

							menuRelatorio(conta, cliente);
							break;
						case 2:
							menuRelatorio(conta, cliente);
							break;
						default:
							System.out.println();
							System.out.println("Insira um valor válido. ");
							System.out.println("Deseja contratar um seguro de vida? Sim [1] Não [2] ");
							break;
						}

					} while (opcao != 1 || opcao != 2);

				} else {
					System.out.println();
					System.out.println("Desculpe, você não possui Conta Corrente.");
					menuRelatorio(conta, cliente);
				}
			case 5:
				menuCliente(conta, cliente);
				break;
			default:
				menuRelatorio(conta, cliente);
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println();
			System.out.println("Ocorreu um erro.");
			System.out.println("Possíveis soluções para o erro:");
			System.out.println("- Insira apenas números com ou sem ponto (.)");
			System.out.println("- Digite números ao invés de letras");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());

		} finally {
			menuRelatorio(conta, cliente);
		}
		sc.close();
	}
}