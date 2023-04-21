package menus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import contas.Conta;
import io.Escritor;
import pessoas.Cliente;
import pessoas.Funcionario;
import principal.SistemaBancario;
import relatorios.Relatorio;

public class Menu{

	public static void menuInicial() throws InputMismatchException, IOException,  NullPointerException {
											
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		Cliente cliente = null;
		Conta conta = null;
		Funcionario funcionario;
		int senha = 0;

		try {
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
			}while (conta == null || cliente == null || cliente.getSenha() != senha || funcionario.getSenha() != senha);
		}

		catch (InputMismatchException e) {
			System.out.println(e.getMessage());  // ADICIONEI ALGUNS TIPOS DE EXCECAO
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		finally{
			menuInicial();
		}
		sc.close();
	}
	

	public static void menuFuncionario(Funcionario funcionario, Conta conta, List<Conta> listaContas, String cpf,
			Cliente cliente) throws IOException,  NullPointerException  {  
		Scanner sc = new Scanner(System.in);  
		Locale.setDefault(Locale.US);
		int opcao = 0;
		System.out.println();
		System.out.println("Olá " + funcionario.getTipoDeUsuario().getTipoPessoa());
		
		try {
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
						// ADICIONAR TRY CATCH
						case 1:
							Relatorio.numDeContasNaAgencia(conta, cpf);
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
						// ADICIONAR TRY CATCH
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
		}
		catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		finally {
			menuInicial(); // MENU INIICAL OU SERIA MENU FUNCIONARIO?
	}
		sc.close();

	}

	public static void menuCliente(Conta conta, Cliente cliente) throws IOException,  NullPointerException {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);

		try {
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
				System.out.print("Insira o valor do saque: R$ ");
				double valor = sc.nextDouble();
				conta.sacar(valor);
				Escritor.comprovanteSaque(conta, valor); // ADICIONEI CHAMADA PARA ESCRITA DO SAQUE
				menuCliente(conta, cliente);
				break;
			case 2:
				System.out.printf("Insira o valor para depósito: R$ ");
				valor = sc.nextDouble();
				conta.depositar(valor);
				Escritor.comprovanteDeposito(conta, valor); // ADICIONEI CHAMADA PARA ESCRITA DO DEPOSITO
				System.out.printf("Saldo atual: R$ %.2f", conta.getSaldo()); // TESTE REMOVER DEPOIS 
				System.out.println();
				menuCliente(conta, cliente);
				break;
			case 3:
				System.out.printf("Insira o valor da transferencia: R$ ");
				valor = sc.nextDouble();
				if(valor < 0 || valor > conta.getSaldo()) {
					System.out.println("Insira um valor válido.");
					menuCliente(conta, cliente);
				}
				sc.nextLine();
				System.out.printf("Insira o CPF do destinatário: ");
				String cpf = sc.nextLine();
				Conta contaDestino = SistemaBancario.mapaDeContas.get(cpf);
				conta.transferir(contaDestino, valor);
				System.out.println("Transferência realizada com sucesso.");
				Escritor.comprovanteTransferencia(conta, contaDestino, valor); // ADICIONEI CHAMADA PARA ESCRITA TRANSFERENCIA
				menuCliente(conta, cliente);
				break;
			case 4:
				conta.imprimeExtrato();
				menuCliente(conta, cliente);
				break;
			case 5:
				menuRelatorio(conta, cliente);
				break;
			case 6:
				menuInicial();
			default:
				break;
			}
		} 
		catch (InputMismatchException e) {
			System.out.println("Ocorreu um erro na transferência.");
			System.out.println("Possível solução para o erro:");
			System.out.println("- Insira apenas números com ou sem ponto (.). Não utilize vírgula.");
		}
		catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		catch (NullPointerException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		finally {
			menuCliente(conta, cliente);
		}
		sc.close();
	}

	public static void menuRelatorio(Conta conta, Cliente cliente) throws IOException,  NullPointerException {
		System.out.println();
		Scanner sc = new Scanner(System.in);     
		Locale.setDefault(Locale.US);
		System.out.println("******** Menu relatório ********");
		System.out.println();
		System.out.println("Escolha entre as opções abaixo:");
		System.out.println("[1] Saldo");
		System.out.println("[2] Relatório de tributação da Conta Corrente");
		System.out.println("[3] Relatório de Rendimento da Conta Poupança");
		System.out.println("[4] Desafio");
		System.out.println("[5] Retornar ao menu anterior");

		int opcao = 0;
		
		try {
			do {
				opcao = sc.nextInt();
	
				switch (opcao) {
				case 1:
					Relatorio.imprimirSaldo(conta);
					break;
				case 2:
					Relatorio.tributacaoCC (conta);
					break;
				case 3:
					Relatorio.simularRendimentoPoupanca();
					break;
				case 4:
					System.out.println("DESAFIO");
					break;
				case 5:
					menuCliente(conta, cliente);
					break;
				default:
					menuRelatorio(conta, cliente);
					break;
				}
			} while (opcao < 1 || opcao > 5);
		}	 
		catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}		
		finally {
			menuRelatorio(conta, cliente);
		}			
		sc.close();
	}
}