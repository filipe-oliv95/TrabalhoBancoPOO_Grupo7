package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import agencias.Agencia;
import contas.Conta;
import contas.ContaCorrente;
import contas.ContaPoupanca;
import contas.enums.ContasEnum;
import pessoas.Cliente;
import pessoas.Diretor;
import pessoas.Funcionario;
import pessoas.Gerente;
import pessoas.Presidente;
import pessoas.Usuario;
import pessoas.enums.UsuariosEnum;

public class LeitorEscritor {

	static List<Agencia> listaAgencias = new ArrayList<Agencia>();
	static List<Gerente> listaGerentes = new ArrayList<Gerente>();
	static List<Diretor> listaDiretores = new ArrayList<>();
	static List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
	static List<Cliente> listaClientes = new ArrayList<Cliente>();
	static List<Conta> listaContas = new ArrayList<Conta>();
	static List<Usuario> listaUsuarios = new ArrayList<>();
	
	public static void leitura(String path) {
		
				
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			String linha = br.readLine();
			linha = br.readLine();
			
			while(linha != null) {
				String[] vetor = linha.split(";"); // split divide 
				
				if(vetor[0] == "AGENCIA"){
					Agencia agencias = new Agencia(vetor[1]);
					Agencia.mapaDeAgencias.put(vetor[1], agencias);
					listaAgencias.add(agencias);
				}
				else if(vetor[3] == UsuariosEnum.GERENTE.getTipoPessoa()) {
					Gerente gerentes = new Gerente(vetor[0], vetor[1], Integer.parseInt(vetor[2]), UsuariosEnum.GERENTE, new Agencia(vetor[4]));
					Gerente.mapaDeGerentes.put(vetor[1], gerentes);
					listaGerentes.add(gerentes);
					
				}
				else if(vetor[3] == UsuariosEnum.DIRETOR.getTipoPessoa()){
					Diretor diretores = new Diretor(vetor[0], vetor[1], Integer.parseInt(vetor[2]), UsuariosEnum.DIRETOR);
					Diretor.mapaDeDiretores.put(vetor[1], diretores);
					listaDiretores.add(diretores);
				}
				else if(vetor[3] == UsuariosEnum.PRESIDENTE.getTipoPessoa()) {
					Presidente presidente = new Presidente(vetor[0], vetor[1], Integer.parseInt(vetor[2]), UsuariosEnum.PRESIDENTE);
					Presidente.mapaPresidente.put(vetor[1], presidente);
				}
				else if(vetor[3] == UsuariosEnum.CLIENTE.getTipoPessoa()) {
					Cliente cliente = new Cliente(vetor[0], vetor[1], Integer.parseInt(vetor[2]), UsuariosEnum.CLIENTE);
					Cliente.mapaDeClientes.put(vetor[1], cliente);
					listaClientes.add(cliente);
					
				}
				else if(vetor[8] == ContasEnum.POUPANCA.getTipoConta()) {
					Cliente cliente = new Cliente(vetor[2], vetor[3], Integer.parseInt(vetor[4]), UsuariosEnum.CLIENTE);
					ContaPoupanca contas = new ContaPoupanca(new Agencia(vetor[0]), vetor[1], cliente, vetor[6], Double.parseDouble(vetor[7]), ContasEnum.POUPANCA);
					Conta.mapaDeContas.put(vetor[0], contas);
					listaContas.add(contas);
				}
				else if(vetor[8] == ContasEnum.CCORRENTE.getTipoConta()) {
					Cliente cliente = new Cliente(vetor[2], vetor[3], Integer.parseInt(vetor[4]), UsuariosEnum.CLIENTE);
					ContaCorrente contas = new ContaCorrente(new Agencia(vetor[0]), vetor[1], cliente, vetor[6], Double.parseDouble(vetor[7]), ContasEnum.CCORRENTE);
					Conta.mapaDeContas.put(vetor[0], contas);
					listaContas.add(contas);
				}			
			}
			br.close(); // close fora do while
		}
		catch (IOException e) {
			System.out.println("Erro na leitura dos dados: " + e.getMessage());
		}
	}
	
//	public static void escritura(String path) throws IOException {
//		
//		Scanner sc = new Scanner(System.in);
//		
////		System.out.println("Digite o nome do arquivo: ");
////		String nomeArquivo = sc.nextLine(); 
//		
//		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
//		
//			while (true) {
//				String linha = sc.nextLine();
//				if (linha.equals("/fim"))
//					break;
//				bw.append(linha);
//				bw.newLine();
//			}
//			
//			System.out.println("Arquivo gravado com sucesso!");
//			bw.close();
//			sc.close();
//		}
//		catch (IOException e){ 
//			System.out.println("Erro na escrita dos dados: " + e.getMessage());
//		}			
//	}
//	
	
}
