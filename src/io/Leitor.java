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

public class Leitor {
	
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
				else if(vetor[0] == UsuariosEnum.GERENTE.getTipoPessoa()) {
					Gerente gerentes = new Gerente(UsuariosEnum.GERENTE, vetor[1], vetor[2], Integer.parseInt(vetor[3]), new Agencia(vetor[4]));
					Gerente.mapaDeGerentes.put(vetor[2], gerentes);
					listaGerentes.add(gerentes);
					
				}
				else if(vetor[0] == UsuariosEnum.DIRETOR.getTipoPessoa()){
					Diretor diretores = new Diretor(UsuariosEnum.DIRETOR, vetor[1], vetor[2], Integer.parseInt(vetor[3]));
					Diretor.mapaDeDiretores.put(vetor[2], diretores);
					listaDiretores.add(diretores);
				}
				else if(vetor[0] == UsuariosEnum.PRESIDENTE.getTipoPessoa()) {
					Presidente presidente = new Presidente(UsuariosEnum.PRESIDENTE, vetor[1], vetor[2], Integer.parseInt(vetor[3]));
					Presidente.mapaPresidente.put(vetor[2], presidente);
				}
				else if(vetor[0] == UsuariosEnum.CLIENTE.getTipoPessoa()) {
					Cliente cliente = new Cliente(UsuariosEnum.CLIENTE, vetor[1], vetor[2], Integer.parseInt(vetor[3]));
					Cliente.mapaDeClientes.put(vetor[2], cliente);
					listaClientes.add(cliente);
					
				}
				else if(vetor[0] == ContasEnum.POUPANCA.getTipoConta()) {
					Cliente cliente = new Cliente(UsuariosEnum.CLIENTE, vetor[4], vetor[5], Integer.parseInt(vetor[6]));
					ContaPoupanca contas = new ContaPoupanca(ContasEnum.POUPANCA, new Agencia(vetor[1]), vetor[2], cliente, vetor[7], Double.parseDouble(vetor[8]));
					Conta.mapaDeContas.put(vetor[4], contas);
					listaContas.add(contas);
				}
				else if(vetor[0] == ContasEnum.CCORRENTE.getTipoConta()) {
					Cliente cliente = new Cliente(UsuariosEnum.CLIENTE, vetor[4], vetor[5], Integer.parseInt(vetor[6]));
					ContaCorrente contas = new ContaCorrente(ContasEnum.CCORRENTE, new Agencia(vetor[1]), vetor[2], cliente, vetor[7], Double.parseDouble(vetor[8]));
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

}
