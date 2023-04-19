package principal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import agencias.Agencia;
import contas.Conta;
import contas.ContaCorrente;
import contas.ContaPoupanca;
import contas.enums.ContasEnum;
import io.LeitorEscritor;
import menus.Menu;
import pessoas.Cliente;
import pessoas.Diretor;
import pessoas.Funcionario;
import pessoas.Gerente;
import pessoas.Presidente;
import pessoas.Usuario;
import pessoas.enums.UsuariosEnum;
import relatorios.Relatorio;

public class SistemaBancario {

	public static void main(String[] args)  {

		Scanner sc = new Scanner(System.in);	
	
		System.out.println("TESTANDO LEITURA");
		LeitorEscritor.leitura(".\\registrodedados.txt"); 
	
				
		// populando Agências (para utilizar na instanciação do Gerente, Clientes e
		// Contas)
		Agencia agencia1 = new Agencia("2885");
		Agencia agencia2 = new Agencia("0080");
		Agencia agencia3 = new Agencia("5086");

		
		// criando Lista de Agências
		List<Agencia> agencias = new ArrayList<>();
		agencias.add(agencia1);
		agencias.add(agencia2);
		agencias.add(agencia3);

		// adicionando as agencias no MAP de Agencias
		for (Agencia ag : agencias) {
			//System.out.println(agencias);
			Agencia.mapaDeAgencias.put(ag.getNumAgencia(), ag);
			//System.out.println(ag); // lista de agências salva
		}

		// populando Gerentes
		Gerente gerente1 = new Gerente("Noah da Mota", "82811814515", 6666, UsuariosEnum.valueOf("GERENTE"), agencia1);
		Gerente gerente2 = new Gerente("Bruna Barros", "56016670120", 7777, UsuariosEnum.valueOf("GERENTE"), agencia2);
		Gerente gerente3 = new Gerente("Cristiane Monteiro", "12794354701", 8888, UsuariosEnum.valueOf("GERENTE"), agencia3);

		
		
		List<Gerente> gerentes = new ArrayList<>();
		gerentes.add(gerente1);
		gerentes.add(gerente2);
		gerentes.add(gerente3);

		// adicionando os Gerentes no MAP de Gerentes
		for (Gerente ge : gerentes) {
			Gerente.mapaDeGerentes.put(ge.getCpf(), ge);
			// System.out.println(ge);
		}

		// populando Diretores
		Diretor diretor1 = new Diretor("Anthony Melo", "87259439521", 9999, UsuariosEnum.valueOf("DIRETOR"));
		Diretor diretor2 = new Diretor("Emily Monteiro", "01078232334", 1010, UsuariosEnum.valueOf("DIRETOR"));

		List<Diretor> diretores = new ArrayList<>();
		diretores.add(diretor1);
		diretores.add(diretor2);

		for (Diretor dr : diretores) {
			Diretor.mapaDeDiretores.put(dr.getCpf(), dr);
			// System.out.println(dr);
		}

		// criando o Presidente
		System.out.println();
		Presidente presidente = new Presidente("Vinicius da Silva", "80466528906", 1212, UsuariosEnum.valueOf("PRESIDENTE"));

		// instanciando clientes para popular a Lista de Clientes e a Lista de Contas
		Cliente cliente1 = new Cliente("Fernanda Meirelles", "23156489785", 1111, UsuariosEnum.valueOf("CLIENTE"));
		Cliente cliente2 = new Cliente("Filipe Oliveira", "86541233210", 2222, UsuariosEnum.valueOf("CLIENTE"));
		Cliente cliente3 = new Cliente("Paulo Vicente", "98654712548", 3333, UsuariosEnum.valueOf("CLIENTE"));
		Cliente cliente4 = new Cliente("Jullian Cardoso", "98756412365", 4444, UsuariosEnum.valueOf("CLIENTE"));
		Cliente cliente5 = new Cliente("Adriana Neves", "56489723114", 5555, UsuariosEnum.valueOf("CLIENTE"));		
		Cliente cliente6 = new Cliente("Vinicius da Silva", "80466528906", 1212, UsuariosEnum.valueOf("CLIENTE"));
		Cliente cliente7 = new Cliente("Anthony Melo", "87259439521", 9999, UsuariosEnum.valueOf("CLIENTE"));
		Cliente cliente8 = new Cliente("Emily Monteiro", "01078232334", 1010, UsuariosEnum.valueOf("CLIENTE"));
		Cliente cliente9 = new Cliente("Noah da Mota", "82811814515", 6666, UsuariosEnum.valueOf("CLIENTE"));
		Cliente cliente10 = new Cliente("Bruna Barros", "56016670120", 7777, UsuariosEnum.valueOf("CLIENTE"));
		Cliente cliente11 = new Cliente("Cristiane Monteiro", "12794354701", 8888, UsuariosEnum.valueOf("CLIENTE"));
		Cliente cliente12 = new Cliente("Luan Cailleaux", "56432145896", 1313, UsuariosEnum.valueOf("CLIENTE"));

		// criando lista com classe Cliente e menos atributos
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(cliente1);
		clientes.add(cliente2);
		clientes.add(cliente3);
		clientes.add(cliente4);
		clientes.add(cliente5);
		clientes.add(cliente6);
		clientes.add(cliente7);
		clientes.add(cliente8);
		clientes.add(cliente9);
		clientes.add(cliente10);
		clientes.add(cliente11);
		clientes.add(cliente12);
		
		for (Cliente cs : clientes) {
			Cliente.mapaDeClientes.put(cs.getCpf(), cs); // adicionando os dados no Map de Cliente
			// System.out.println(cs); // e imprimindo a lista de clientes só para verificar
		}

		// instanciando Contas para a LIsta de Contas
		ContaPoupanca conta1 = new ContaPoupanca(agencia1, "658974", cliente1, "23156489785", 1500.0,
				ContasEnum.valueOf("POUPANCA"));
		ContaCorrente conta2 = new ContaCorrente(agencia2, "281564", cliente2, "86541233210", 5000.0,
				ContasEnum.valueOf("CCORRENTE"));
		ContaPoupanca conta3 = new ContaPoupanca(agencia3, "683145", cliente3, "98654712548", 7000.0,
				ContasEnum.valueOf("POUPANCA"));
		ContaCorrente conta4 = new ContaCorrente(agencia2, "112564", cliente4, "98756412365", 3500.0,
				ContasEnum.valueOf("CCORRENTE"));
		ContaCorrente conta5 = new ContaCorrente(agencia1, "278561", cliente5, "56489723114", 2000.0,
				ContasEnum.valueOf("CCORRENTE"));
		ContaCorrente conta6 = new ContaCorrente(agencia3, "681245", cliente6, "80466528906", 2500.0,
				ContasEnum.valueOf("CCORRENTE"));
		ContaCorrente conta7 = new ContaCorrente(agencia2, "286545", cliente7, "87259439521", 2500.0,
				ContasEnum.valueOf("CCORRENTE"));
		ContaCorrente conta8 = new ContaCorrente(agencia1, "634545", cliente8, "01078232334", 2900.0,
				ContasEnum.valueOf("CCORRENTE"));
		ContaCorrente conta9 = new ContaCorrente(agencia3, "696545", cliente9, "82811814515", 2800.0,
				ContasEnum.valueOf("CCORRENTE"));
		ContaCorrente conta10 = new ContaCorrente(agencia2, "287645", cliente10, "56016670120", 2500.0,
				ContasEnum.valueOf("CCORRENTE"));
		ContaCorrente conta11 = new ContaCorrente(agencia1, "647645", cliente11, "12794354701", 2900.0,
				ContasEnum.valueOf("CCORRENTE"));
		ContaPoupanca conta12 = new ContaPoupanca(agencia3, "621564", cliente12, "56432145896", 1700.0,
				ContasEnum.valueOf("POUPANCA"));
				
		List<Conta> contas = new ArrayList<>();
		contas.add(conta1);
		contas.add(conta2);
		contas.add(conta3);
		contas.add(conta4);
		contas.add(conta5);
		contas.add(conta6);
		contas.add(conta7);
		contas.add(conta8);
		contas.add(conta9);
		contas.add(conta10);
		contas.add(conta11);
		
		System.out.println();
		for (Conta co : contas) {
			Conta.mapaDeContas.put(co.getCpf(), co); // adicionando no Map de Contas
//			System.out.println(co);
		}
		
		Collections.sort(contas);
		for (Conta conta : contas) {
			System.out.printf("NOME: %s\t AGÊNCIA: %s\n",
					conta.getTitular(), conta.getAgencia());
		}
		
		
		
		//METODO DEPOSITAR
//		System.out.println("saldo: " + conta1.getSaldo());
//		conta1.depositar(1000);
//		System.out.println("saldo: " + conta1.getSaldo());
		
		//METODO SACAR
//		System.out.println("saldo: " + conta1.getSaldo());
//		conta1.sacar(1000);
//		System.out.println("saldo: " + conta1.getSaldo());
		
		//METODO TRANSFERIR
//		System.out.println("saldo: " + conta1.getSaldo());
//		conta1.transferir(conta2, 1000);
//		System.out.println("saldo: " + conta1.getSaldo());
//		System.out.println("saldo: " + conta2.getSaldo());				

		// IMPRIMIR MENU CLIENTE E TRANSFERIR
//		System.out.println(Conta.mapaDeContas.get("56321452632"));
//		Menu.imprimirMenuCliente(conta3);
//		System.out.println();

//		 teste do Objeto Cliente dentro de Conta
		System.out.println();
		ContaCorrente contaTeste = new ContaCorrente(agencia3, "278561", cliente5, "56489723114", 1200.0,
				ContasEnum.valueOf("CCORRENTE"));
		System.out.println("Pegando Objeto Cliente dentro de Conta => " + contaTeste.getTitular().getNome());

		System.out.println();
		// criando Lista de usuarios para adicionar no Map
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(cliente12);
		usuarios.add(cliente11);
		usuarios.add(cliente10);
		usuarios.add(cliente9);
		usuarios.add(cliente8);
		usuarios.add(cliente7);
		usuarios.add(cliente6);
		usuarios.add(cliente5);
		usuarios.add(cliente4);
		usuarios.add(cliente3);
		usuarios.add(cliente2);
		usuarios.add(cliente1);
		usuarios.add(gerente1);
		usuarios.add(gerente2);
		usuarios.add(gerente3);
		usuarios.add(diretor1);
		usuarios.add(diretor2);
		usuarios.add(presidente);

		for (Usuario usu : usuarios) {
			Usuario.mapaDeUsuarios.put(usu.getCpf(), usu);
		//	 System.out.println(usu);
		}
		System.out.println("Dados do MAPA USUARIOS => " + Usuario.mapaDeUsuarios.get("01078232334"));
		System.out.println("Dados do MAPA USUARIOS => " + Usuario.mapaDeUsuarios.get("23156489785"));

		// criando mapa de Funcionarios
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios.add(presidente);
		funcionarios.add(diretor1);
		funcionarios.add(diretor2);
		funcionarios.add(gerente1);
		funcionarios.add(gerente2);
		funcionarios.add(gerente3);
		for (Funcionario func : funcionarios) {
			Funcionario.mapaDeFuncionarios.put(func.getCpf(), func);
//			 System.out.println(func);
		}
		System.out.println("Testando Mapa Funcionários =>  " + Funcionario.mapaDeFuncionarios.get("80466528906"));

		Conta conta;
		Usuario usuario;
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

			conta = Conta.mapaDeContas.get(cpf);
			usuario = Usuario.mapaDeUsuarios.get(cpf);
			funcionario = Funcionario.mapaDeFuncionarios.get(cpf);
			
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
							switch(usuario.getTipoDeUsuario()) {
							case PRESIDENTE:
								System.out.println("Deseja verificar o capital total? ");
								
								break;
							case DIRETOR:
								System.out.println("Deseja consultar informações dos clientes do banco? S/N ");
								char op = sc.next().charAt(0);
								
									if(op == 'S' || op == 's') {
										Relatorio.informacoesClientes(contas);
									}
									else {
										Menu.imprimirMenuCliente(conta);
									}								
								break;
							case GERENTE:
								System.out.println("Deseja consultar a lista de contas em sua agência ? S/N ");
								op = sc.next().charAt(0);
								
									if(op == 'S' || op == 's') {
										Relatorio.numDeContasNaAgencia(contas, cpf); // pegou List<Conta>
									}
									else {
										Menu.imprimirMenuCliente(conta);
									}		
								break;
							default:
							System.out.println("DADOS INCORRETOS, aperte Enter e digite novamente \n");
							// retornar função
							break;
							}
						}
					} while (!(opcao == 0)); //PENSAR EM UMA OUTRO SOLUÇÃO
				}
			}
			else if (conta != null && usuario != null) {
				if (usuario.getSenha() == senha) {
					System.out.println("Cliente");
					Menu.imprimirMenuCliente(conta); 					
				} 
				else {
					System.out.println("DADOS INCORRETOS, aperte Enter e digite novamente \n");
				}
			}
		} while (conta == null || usuario == null || usuario.getSenha() != senha);

		sc.close();
	}
}
