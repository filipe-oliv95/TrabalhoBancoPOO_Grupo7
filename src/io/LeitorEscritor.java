package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import agencias.Agencia;
import contas.Conta;
import pessoas.Cliente;
import pessoas.Diretor;
import pessoas.Funcionario;
import pessoas.Gerente;
import pessoas.Usuario;

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
			while(linha != null) {
				System.out.println(linha);
				linha = br.readLine();
			}
			br.close(); // close fora do while
		}
		catch (IOException e) {
			System.out.println("Erro na leitura dos dados: " + e.getMessage());
		}
	}
	
	public static void escritura(String path) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		
//		System.out.println("Digite o nome do arquivo: ");
//		String nomeArquivo = sc.nextLine(); 
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
		
			while (true) {
				String linha = sc.nextLine();
				if (linha.equals("/fim"))
					break;
				bw.append(linha);
				bw.newLine();
			}
			
			System.out.println("Arquivo gravado com sucesso!");
			bw.close();
			sc.close();
		}
		catch (IOException e){ 
			System.out.println("Erro na escrita dos dados: " + e.getMessage());
		}			
	}
	
	
}
