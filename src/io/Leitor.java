package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import agencias.Agencia;
import contas.ContaCorrente;
import contas.ContaPoupanca;
import contas.enums.ContasEnum;
import pessoas.Cliente;
import pessoas.Diretor;
import pessoas.Gerente;
import pessoas.Presidente;
import pessoas.enums.UsuariosEnum;
import principal.SistemaBancario;

public class Leitor {
	public static void leitura(String path) {

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String linha = "";

			while (true) {

				linha = br.readLine();
				if (linha != null) {

					String[] vetor = linha.split(";");

					if (vetor[0].equalsIgnoreCase("AGENCIA")) {

						Agencia agencia = new Agencia(vetor[1]);
						SistemaBancario.listaAgencias.add(agencia);

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
						SistemaBancario.mapaDeFuncionarios.put(vetor[2], presidente);

					} else if (vetor[0].equalsIgnoreCase(UsuariosEnum.CLIENTE.name())) {

						Cliente cliente = new Cliente(UsuariosEnum.CLIENTE, vetor[1], vetor[2],
								Integer.parseInt(vetor[3]));
						SistemaBancario.mapaDeClientes.put(vetor[2], cliente);

					}

					else if (vetor[0].equalsIgnoreCase(ContasEnum.POUPANCA.name())) {

						Cliente cliente = new Cliente(UsuariosEnum.CLIENTE, vetor[4], vetor[5],
								Integer.parseInt(vetor[6]));
						ContaPoupanca contas = new ContaPoupanca(ContasEnum.POUPANCA, new Agencia(vetor[1]), vetor[2],
								cliente, vetor[7], Double.parseDouble(vetor[8]));
						SistemaBancario.mapaDeContas.put(vetor[5], contas);

					}

					else if (vetor[0].equalsIgnoreCase(ContasEnum.CORRENTE.name())) {
						Cliente cliente = new Cliente(UsuariosEnum.CLIENTE, vetor[4], vetor[5],
								Integer.parseInt(vetor[6]));
						ContaCorrente contas = new ContaCorrente(ContasEnum.CORRENTE, new Agencia(vetor[1]), vetor[2],
								cliente, vetor[7], Double.parseDouble(vetor[8]));
						SistemaBancario.mapaDeContas.put(vetor[5], contas);
					} else
						break;
				} else
					break;
			}
		} catch (IOException e) {
			System.out.println("Erro na leitura dos dados: " + e.getMessage());
		}
	}
}
