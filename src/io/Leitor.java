package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import principal.SistemaBancario;

public class Leitor {


	public static void leitura(String path) {

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String linha = "";

			while (true) {

				linha = br.readLine();
				if (linha != null) {

//					System.out.println(linha);
					String[] vetor = linha.split(";"); // split divide

					if (vetor[0].equalsIgnoreCase("AGENCIA")) {
						Agencia agencias = new Agencia(vetor[1]);
						SistemaBancario.mapaDeAgencias.put(vetor[1], agencias);
						
					} else if (vetor[0].equalsIgnoreCase(UsuariosEnum.GERENTE.name())) {
						Gerente gerentes = new Gerente(UsuariosEnum.GERENTE, vetor[1], vetor[2],
								Integer.parseInt(vetor[3]), new Agencia(vetor[4]));
						SistemaBancario.mapaDeGerentes.put(vetor[2], gerentes);
						SistemaBancario.mapaDeFuncionarios.put(vetor[2], gerentes);
					} else if (vetor[0].equalsIgnoreCase(UsuariosEnum.DIRETOR.name())) {
						Diretor diretores = new Diretor(UsuariosEnum.DIRETOR, vetor[1], vetor[2],
								Integer.parseInt(vetor[3]));
						SistemaBancario.mapaDeDiretores.put(vetor[2], diretores);
						SistemaBancario.mapaDeFuncionarios.put(vetor[2], diretores);
					} else if (vetor[0].equalsIgnoreCase(UsuariosEnum.PRESIDENTE.name())) {
						Presidente presidente = new Presidente(UsuariosEnum.PRESIDENTE, vetor[1], vetor[2],
								Integer.parseInt(vetor[3]));
						SistemaBancario.mapaDePresidente.put(vetor[2], presidente);
						SistemaBancario.mapaDeFuncionarios.put(vetor[2], presidente);
					} else if (vetor[0].equalsIgnoreCase(UsuariosEnum.CLIENTE.name())) {
						Cliente cliente = new Cliente(UsuariosEnum.CLIENTE, vetor[1], vetor[2],
								Integer.parseInt(vetor[3]));
						SistemaBancario.mapaDeClientes.put(vetor[2], cliente);
					
					} else if (vetor[0].equalsIgnoreCase(ContasEnum.POUPANCA.name())) {
//						System.out.println(vetor[0]);
						Cliente cliente = new Cliente(UsuariosEnum.CLIENTE, vetor[4], vetor[5],
								Integer.parseInt(vetor[6]));
						ContaPoupanca contas = new ContaPoupanca(ContasEnum.POUPANCA, new Agencia(vetor[1]), vetor[2],
								cliente, vetor[7], Double.parseDouble(vetor[8]));
						SistemaBancario.mapaDeContas.put(vetor[5], contas);
//						System.out.println(contas);
					} else if (vetor[0].equalsIgnoreCase(ContasEnum.CCORRENTE.name())) {
						Cliente cliente = new Cliente(UsuariosEnum.CLIENTE, vetor[4], vetor[5],
								Integer.parseInt(vetor[6]));
						ContaCorrente contas = new ContaCorrente(ContasEnum.CCORRENTE, new Agencia(vetor[1]), vetor[2],
								cliente, vetor[7], Double.parseDouble(vetor[8]));
						SistemaBancario.mapaDeContas.put(vetor[5], contas);
//						String cpf = "98756412365";
//						System.out.println(Conta.mapaDeContas.get(cpf));
//						System.out.println(vetor[8]);
//						System.out.println(contas);
					}
					else break;
				}
				else break;
			}
		} catch (IOException e) {
			System.out.println("Erro na leitura dos dados: " + e.getMessage());
		
		}

	}

}
