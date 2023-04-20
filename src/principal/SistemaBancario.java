package principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import agencias.Agencia;
import contas.Conta;
import io.Leitor;
import menus.Menu;
import pessoas.Cliente;
import pessoas.Diretor;
import pessoas.Funcionario;
import pessoas.Gerente;
import pessoas.Presidente;
import pessoas.Usuario;
import relatorios.Relatorio;

public class SistemaBancario {

	public static Map<String, Agencia> mapaDeAgencias = new HashMap<>();
	public static Map<String, Gerente> mapaDeGerentes = new HashMap<>();
	public static Map<String, Diretor> mapaDeDiretores = new HashMap<>();
	public static Map<String, Funcionario> mapaDeFuncionarios = new HashMap<>();
	public static Map<String, Cliente> mapaDeClientes = new HashMap<>();
	public static Map<String, Conta> mapaDeContas = new HashMap<>();
	public static Map<String, Usuario> mapaDeUsuarios = new HashMap<>();
	public static Map<String, Presidente> mapaDePresidente = new HashMap<>();
	
	public static void main(String[] args)  {

		Scanner sc = new Scanner(System.in);	
	
		Leitor.leitura(".\\database\\registrodedados.txt"); 
	
		System.out.println("TESTANDO LEITURA");
				
		System.out.println("Testando Mapa Funcionários =>  " + mapaDeClientes.get("56489723114"));
		System.out.println(mapaDeFuncionarios.get("80466528906"));
		
//		List<Conta> listaContas = new ArrayList<>(mapaDeContas.values());
//		for (Conta c : listaContas) {
//			System.out.println(c);
//		}
		
		Cliente cliente;
		Conta conta;
		//Usuario usuario;
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

			cliente = mapaDeClientes.get(cpf);
			conta = mapaDeContas.get(cpf);
		//	usuario = mapaDeUsuarios.get(cpf);
			funcionario = mapaDeFuncionarios.get(cpf);
			List<Conta> listaContas = new ArrayList<>(mapaDeContas.values());
			
			int opcao = 0;
			if (conta != null && funcionario != null) {
				if (funcionario.getSenha() == senha) {
					System.out.println("Olá " + funcionario.getTipoDeUsuario());
					do {
						System.out.println("Deseja entrar na conta de Cliente [1] ou Funcionario [2]");
						opcao = sc.nextInt();

						switch (opcao) { // MENU CLIENTE PARA FUNCIONARIO
						case 1: System.out.println("MENU CLIENTE para FUNCIONARIO"); 
								Menu.imprimirMenuCliente(conta);
							break;
						case 2: // MENU DO FUNCIONÁRIO							
							switch(funcionario.getTipoDeUsuario()) {
							case GERENTE:
								System.out.println("Deseja consultar a lista de contas em sua agência ? S/N ");
								char op = sc.next().charAt(0);
								
									if(op == 'S' || op == 's') {
										Relatorio.numDeContasNaAgencia(cpf); // pegou List<Conta>
									}
									else {
										Menu.imprimirMenuCliente(conta);
									}		
								break;
							case DIRETOR:
								System.out.println("[1] Consulta de contas por agência");
								System.out.println("[2] Relatório de informações dos clientes do banco");
								opcao = sc.nextInt();
								
								opcao = 0; 
								switch (opcao) {
								case 1:
									Relatorio.numDeContasNaAgencia(cpf);
									break;
								case 2: 
										Relatorio.informacoesClientes(listaContas);
									break;
								default:
										Menu.imprimirMenuCliente(conta);	
									break;
								}
							case PRESIDENTE:
								System.out.println("[1] Consulta de contas por agência");
								System.out.println("[2] Relatório de informações dos clientes do banco");
								System.out.println("[3] Relatório valor total armazenado");
								opcao = sc.nextInt();
								
								opcao = 0; 
								switch (opcao) {
								case 1:
									Relatorio.numDeContasNaAgencia(cpf);
									break;
								case 2: 
										Relatorio.informacoesClientes(listaContas);
									break;
								case 3:
										System.out.println("CRIAR RELATORIO DO CAPITAL ARMAZENADO");
								default:
										Menu.imprimirMenuCliente(conta);	
									break;
								}
								
							default:
							System.out.println("DADOS INCORRETOS, aperte Enter e digite novamente \n");
							// retornar função
							break;
							}
						}
					} while (!(opcao == 0)); //PENSAR EM UMA OUTRO SOLUÇÃO
				}
			}
			else if (conta != null && cliente != null) {
				if (cliente.getSenha() == senha) {
					System.out.println("Cliente");
					Menu.imprimirMenuCliente(conta); 					
				} 
				else {
					System.out.println("DADOS INCORRETOS, aperte Enter e digite novamente \n");
				}
			}
		} while (conta == null || cliente == null || cliente.getSenha() != senha || funcionario.getSenha() != senha);

		sc.close();
	}
}