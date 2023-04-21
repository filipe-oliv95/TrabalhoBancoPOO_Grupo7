package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import contas.Conta;
import contas.ContaCorrente;
import principal.SistemaBancario;

public class Escritor {

	static final String CAMINHO = "./comprovantes/";
	static final String EXTENSAO = ".txt";

	public static void comprovanteSaque(Conta conta, double valorSaque) throws IOException { // CLIENTE

		String hojeFormatado = LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yy"));
		String arquivo = conta.getCpf() + "_" + conta.getAgencia() + "_" + hojeFormatado + "_comprovanteSaque";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO + arquivo + EXTENSAO, true))) {

			String linha = "*************** SAQUE ***************";

			bw.append(linha + "\n");

			linha = "CPF: " + conta.getCpf();
			bw.append(linha + "\n");

			linha = "Agencia: " + conta.getAgencia();
			bw.append(linha + "\n");

			linha = "Conta: " + conta.getNumConta();
			bw.append(linha + "\n");

			linha = "Valor do saque: R$" + valorSaque;
			bw.append(linha + "\n");

			String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

			linha = "Operação realizada em: " + date;
			bw.append(linha + "\n");

			linha = "************ FIM DO SAQUE ************";
			bw.append(linha + "\n\n");

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {

		}
	}

	public static void comprovanteDeposito(Conta conta, double valorDeposito) { // CLIENTE

		String hojeFormatado = LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yy"));
		String arquivo = conta.getCpf() + "_" + conta.getAgencia() + "_" + hojeFormatado + "_comprovanteDeposito";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO + arquivo + EXTENSAO, true))) {

			String linha = "***************** DEPÓSITO *****************";
			bw.append(linha + "\n");

			linha = "CPF: " + conta.getCpf();
			bw.append(linha + "\n");

			linha = "Agência: " + conta.getAgencia();
			bw.append(linha + "\n");

			linha = "Conta: " + conta.getNumConta();
			bw.append(linha + "\n");

			linha = "Valor: R$" + valorDeposito;
			bw.append(linha + "\n");

			String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

			linha = "Operação realizada em: " + date;
			bw.append(linha + "\n");

			linha = "************* FIM DO DEPÓSITO **************";
			bw.append(linha + "\n\n");

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {

		}

	}

	public static void comprovanteTransferencia(Conta conta, Conta contaDestino, double valorTransferencia) { // CLIENTE

		String hojeFormatado = LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yy"));
		String arquivo = conta.getCpf() + "_" + conta.getAgencia() + "_" + hojeFormatado + "_comprovanteTransferencia";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO + arquivo + EXTENSAO, true))) {

			String linha = "\n*************** TRANSFERÊNCIA ***************";
			bw.append(linha + "\n\n");

			linha = "************ DADOS DO REMETENTE *************";
			bw.append(linha + "\n");

			linha = "Nome: " + conta.getTitular().getNome();
			bw.append(linha + "\n");
			
			linha = "CPF: " + conta.getCpf();
			bw.append(linha + "\n");

			linha = "Agência : " + conta.getAgencia();
			bw.append(linha + "\n");

			linha = "Conta: " + conta.getNumConta();
			bw.append(linha + "\n");

			linha = "********** DADOS DO DESTINATÁRIO ************";
			bw.append(linha + "\n");

			linha = "Nome: " + contaDestino.getTitular().getNome();
			bw.append(linha + "\n");
			
			linha = "CPF: " + contaDestino.getCpf();
			bw.append(linha + "\n");

			linha = "Agência: " + contaDestino.getAgencia();
			bw.append(linha + "\n");

			linha = "Conta: " + contaDestino.getNumConta();
			bw.append(linha + "\n");

			linha = "*********************************************";
			bw.append(linha + "\n");

			linha = "Valor: R$" + valorTransferencia;
			bw.append(linha + "\n");

			String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

			linha = "Operação realizada em: " + date;
			bw.append(linha + "\n");

			linha = "*********** FIM DA TRANSFERÊNCIA ************";
			bw.append(linha + "\n");

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {

		}

	}

	public static void extratoConta() {

	}

	public static void comprovanteSaldo(Conta conta) {

		String hojeFormatado = LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yy"));
		String arquivo = conta.getCpf() + "_" + conta.getAgencia() + "_" + hojeFormatado + "_comprovanteSaldo";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO + arquivo + EXTENSAO, true))) {

			String linha = "*************** SALDO ***************";
			bw.append(linha + "\n");

			linha = "Tipo: " + conta.getTipoDeConta();
			bw.append(linha + "\n");

			linha = "Agencia: " + conta.getAgencia();
			bw.append(linha + "\n");

			linha = "Conta: " + conta.getNumConta();
			bw.append(linha + "\n");

			linha = "Saldo: R$" + conta.getSaldo();
			bw.append(linha + "\n");

			String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
			linha = "Operação realizada em: " + date;
			bw.append(linha + "\n");

			linha = "*************** FIM ***************";
			bw.append(linha + "\n");

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {

		}

	}

	//CRIAR 1 RELATORIO PARA O CLIENTE E 1 PARA O GERENTE/PRESIDENTE/DIRETOR
	public static void relatorioTributacaoCC(ContaCorrente conta) {
		
		String hojeFormatado = LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yy"));
		String arquivo = conta.getCpf() + "_" + conta.getAgencia() + "_" + hojeFormatado + "relatorioTributacaoCC";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO + arquivo + EXTENSAO, true))) {
		
		String linha = "*************** TOTAL DE TRIBUTAÇÕES ***************";
		bw.append(linha + "\n\n");

		linha = "Total recebido em transações = R$" + conta.getTotalTarifas();
		bw.append(linha + "\n");

		linha = "Taxa para saque = R$0,10";
		bw.append(linha + "\n");

		
		// ATUALIZAR
		linha = "Total de saques realizados = " ;
		bw.append(linha + "\n\n");

		linha = "Taxa para deposito = R$0,10";
		bw.append(linha + "\n");

		linha = "Total de depósitos realizados = " ;
		bw.append(linha + "\n\n");

		linha = "Taxa para tranferência = R$0,20";
		bw.append(linha + "\n");

		linha = "Total de tranferências realizadas = " ;
		bw.append(linha + "\n\n");

		String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		
		linha = "Operações realizada em: " + date;
		bw.append(linha + "\n");

		linha = "****************************************************";
		bw.append(linha + "\n\n");
		
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {

		}
		
	}
	
	public static void relatorioContasPorAgencia(Conta conta) throws IOException { // GERENTE
		
		String hojeFormatado = LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yy"));
		String arquivo = conta.getCpf() + "_" + conta.getAgencia() + "_" + hojeFormatado + "relatorioContasPorAgencia";
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO + arquivo + EXTENSAO, true))) {
			
			int totalContas = 0;
			String linha = "****************** RESPONSÁVEL **********************";
			bw.append(linha + "\n\n");
			
			linha = "CPF: " + conta.getCpf();
			bw.append(linha + "\n");

			linha = "Agência : " + conta.getAgencia().getNumAgencia();
			bw.append(linha + "\n");

			linha = "*********************************************************";
			bw.append(linha + "\n\n");

			linha = "****************** TOTAL DE CONTAS NA AGÊNCIA ***************";
			bw.append(linha + "\n\n");

			for (String cpf : SistemaBancario.mapaDeContas.keySet()) {
				if (SistemaBancario.mapaDeContas.get(cpf).getAgencia().getNumAgencia().equals(conta.getAgencia().getNumAgencia())) {

					linha = "CPF: " + SistemaBancario.mapaDeContas.get(cpf).getCpf();
					bw.append(linha + "\n");

					linha = "Agência : " + SistemaBancario.mapaDeContas.get(cpf).getAgencia();
					bw.append(linha + "\n");

					linha = "Conta: " + SistemaBancario.mapaDeContas.get(cpf).getNumConta();
					bw.append(linha + "\n");

					totalContas++;

					linha = "**************************************";
					bw.append(linha + "\n");
			}

			}

			linha = "Total de contas: " + totalContas;
			bw.append(linha + "\n");

			String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
			linha = "Operações realizada em: " + date;
			bw.append(linha + "\n");

			linha = "************************************************************************";
			bw.append(linha + "\n\n");

			bw.close();			
		}
		catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
		
		}
	}

	public static void relatorioClientes() { // DIRETOR

	}

	public static void relatorioCapitalBanco(List<Conta> listaConta, double saldo) { // PRESIDENTE

		
		String hojeFormatado = LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yy"));
		String arquivo =  "_" + hojeFormatado + "relatorioCapitalBanco";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO + arquivo + EXTENSAO, true))) {
				
				String linha = "************************* TOTAL DE CAPITAL ARMAZENADO *************************";
				bw.append(linha + "\n\n");

				linha = "Capital total armazenado no banco: R$" ;
				bw.append(linha + "\n");

				String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
				
				linha = "Operação realizada em: " + date;
				bw.append(linha + "\n");

				linha = "*******************************************************************************";
				bw.append(linha + "\n\n");
		
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {

		}
		
		
	}

}
