package principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import contas.Conta;
import io.Leitor;
import menus.Menu;
import pessoas.Funcionario;
import pessoas.Usuario;
import relatorios.Relatorio;

public class SistemaBancario {

	public static void main(String[] args)  {

		Scanner sc = new Scanner(System.in);	
	
		System.out.println("TESTANDO LEITURA");
		Leitor.leitura(".\\registrodedados.txt"); 
	
				List<Conta> contas = new ArrayList<>();
				
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
