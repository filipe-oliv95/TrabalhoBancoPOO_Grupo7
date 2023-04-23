package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import contas.Conta;
import contas.ContaCorrente;
import contas.enums.ContasEnum;
import extratos.Extrato;
import menus.Menu;
import pessoas.Cliente;
import pessoas.Funcionario;
import principal.SistemaBancario;
import segurosDeVida.SeguroDeVida;

public class Escritor {

	static final String CAMINHO = "./comprovantes/";
	static String SUB_CAMINHO = null;
	static final String EXTENSAO = ".txt";

	public static void comprovanteSaque(Conta conta, double valorSaque) throws IOException {

		SUB_CAMINHO = conta.getCpf() + "_" + conta.getTitular().getNome() + "_" + conta.getTitular().getTipoDeUsuario();
		new File(CAMINHO + "\\" + SUB_CAMINHO).mkdir();

		String hojeFormatado = LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yy"));
		String arquivo = conta.getCpf() + "_" + conta.getAgencia() + "_" + hojeFormatado + "_comprovanteSaque";

		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(CAMINHO + "\\" + SUB_CAMINHO + "\\" + arquivo + EXTENSAO, true))) {

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
		}
	}

	public static void comprovanteDeposito(Conta conta, double valorDeposito) {

		SUB_CAMINHO = conta.getCpf() + "_" + conta.getTitular().getNome() + "_" + conta.getTitular().getTipoDeUsuario();
		new File(CAMINHO + "\\" + SUB_CAMINHO).mkdir();

		String hojeFormatado = LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yy"));
		String arquivo = conta.getCpf() + "_" + conta.getAgencia() + "_" + hojeFormatado + "_comprovanteDeposito";

		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(CAMINHO + "\\" + SUB_CAMINHO + "\\" + arquivo + EXTENSAO, true))) {

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
		}
	}

	public static void comprovanteTransferencia(Conta conta, Conta contaDestino, double valorTransferencia) { 

		SUB_CAMINHO = conta.getCpf() + "_" + conta.getTitular().getNome() + "_" + conta.getTitular().getTipoDeUsuario();
		new File(CAMINHO + "\\" + SUB_CAMINHO).mkdir();

		String hojeFormatado = LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yy"));
		String arquivo = conta.getCpf() + "_" + conta.getAgencia() + "_" + hojeFormatado + "_comprovanteTransferencia";

		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(CAMINHO + "\\" + SUB_CAMINHO + "\\" + arquivo + EXTENSAO, true))) {

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
		}
	}

	public static void extratoConta(Conta conta) {

		SUB_CAMINHO = conta.getCpf() + "_" + conta.getTitular().getNome() + "_" + conta.getTitular().getTipoDeUsuario();
		new File(CAMINHO + "\\" + SUB_CAMINHO).mkdir();
		
		String hojeFormatado = LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yy"));
		String arquivo = conta.getCpf() + "_" + conta.getAgencia() + "_" + hojeFormatado + "_comprovanteExtrato";

		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(CAMINHO + "\\" + SUB_CAMINHO + "\\" + arquivo + EXTENSAO, true))) {

			String linha = "\n********************* EXTRATO *********************";
			bw.append(linha + "\n");

			linha = "Tipo: " + conta.getTipoDeConta();
			bw.append(linha + "\n");

			linha = "Titular: " + conta.getTitular().getNome() + " | CPF: " + conta.imprimeCPF(conta.getCpf());
			bw.append(linha + "\n");

			linha = "Agencia: " + conta.getAgencia().getNumAgencia() + " | Conta: " + conta.getNumConta();
			bw.append(linha + "\n");
			
			bw.append("\n");
			for (Extrato listaMovimentacao : conta.getlistaDeMovimentacoes()) {
				bw.append(listaMovimentacao.toString() + "\n");
			}
			bw.append("\n");
					
			if(conta.getTipoDeConta().equals(ContasEnum.CORRENTE)) {
				linha = "Total gasto em tributos = R$" + String.format("%.2f", ((ContaCorrente) conta).getTotalTarifas());
				bw.append(linha + "\n");
			}
			
			if (Menu.contratoSeguro == true) {
				linha = "Valor do Seguro de Vida = R$ " + String.format("%.2f",SeguroDeVida.getValorSeguroAposTaxa());
				bw.append(linha + "\n");
			}
								
			linha = "Saldo: R$" + String.format("%.2f", conta.getSaldo());
			bw.append(linha + "\n");

			String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
			linha = "Operação realizada em: " + date;
			bw.append(linha + "\n");

			linha = "********************* FIM **************************\n";
			bw.append(linha + "\n");

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public static void comprovanteSaldo(Conta conta) {

		SUB_CAMINHO = conta.getCpf() + "_" + conta.getTitular().getNome() + "_" + conta.getTitular().getTipoDeUsuario();
		new File(CAMINHO + "\\" + SUB_CAMINHO).mkdir();

		String hojeFormatado = LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yy"));
		String arquivo = conta.getCpf() + "_" + conta.getAgencia() + "_" + hojeFormatado + "_comprovanteSaldo";

		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(CAMINHO + "\\" + SUB_CAMINHO + "\\" + arquivo + EXTENSAO, true))) {

			String linha = "*************** SALDO ******************";
			bw.append(linha + "\n");

			linha = "Tipo: " + conta.getTipoDeConta();
			bw.append(linha + "\n");

			linha = "Agencia: " + conta.getAgencia();
			bw.append(linha + "\n");

			linha = "Conta: " + conta.getNumConta();
			bw.append(linha + "\n");

			linha = "Saldo: R$" + String.format("%.2f", (conta.getSaldo()));
			bw.append(linha + "\n");

			String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
			linha = "Operação realizada em: " + date;
			bw.append(linha + "\n");

			linha = "*************** FIM ******************";
			bw.append(linha + "\n");

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public static void relatorioTributacaoCC(ContaCorrente conta) {

		SUB_CAMINHO = conta.getCpf() + "_" + conta.getTitular().getNome() + "_" + conta.getTitular().getTipoDeUsuario();
		new File(CAMINHO + "\\" + SUB_CAMINHO).mkdir();

		String hojeFormatado = LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yy"));
		String arquivo = conta.getCpf() + "_" + conta.getAgencia() + "_" + hojeFormatado + "_"
				+ "relatorioTributacaoCC";

		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(CAMINHO + "\\" + SUB_CAMINHO + "\\" + arquivo + EXTENSAO, true))) {

			String linha = "*************** TOTAL DE TRIBUTAÇÕES *****************";
			bw.append(linha + "\n\n");

			linha = "Total gasto em transações = R$" + conta.getTotalTarifas();
			bw.append(linha + "\n\n");

			linha = "Taxa para saque = " + ContaCorrente.getTarifaSaque();
			bw.append(linha + "\n");

			linha = "Total de saques realizados = " + conta.getTotalSaques();
			bw.append(linha + "\n\n");

			linha = "Taxa para deposito = " + ContaCorrente.getTarifaDeposito();
			bw.append(linha + "\n");

			linha = "Total de depósitos realizados = " + conta.getTotalDepositos();
			bw.append(linha + "\n\n");

			linha = "Taxa para tranferência = " + ContaCorrente.getTarifaTransferencia();
			bw.append(linha + "\n");

			linha = "Total de tranferências realizadas = " + conta.getTotalTransferencias();
			bw.append(linha + "\n\n");

			if (Menu.contratoSeguro == true) {
				linha = "Valor segurado do Seguro de Vida = R$ " + String.format("%.2f", SeguroDeVida.getValorSeguroAposTaxa());
				bw.append(linha + "\n\n");
			}

			String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
			linha = "Operações realizada em: " + date;
			bw.append(linha + "\n");

			linha = "****************************************************";
			bw.append(linha + "\n\n");

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public static void rendimentDaPoupanca(Conta conta, Cliente cliente, Double rendimento) {

		SUB_CAMINHO = conta.getCpf() + "_" + conta.getTitular().getNome() + "_" + conta.getTitular().getTipoDeUsuario();
		new File(CAMINHO + "\\" + SUB_CAMINHO).mkdir();

		String hojeFormatado = LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yy"));
		String arquivo = conta.getCpf() + "_" + conta.getAgencia() + "_" + hojeFormatado
				+ "_relatorioRendimentoPoupanca";

		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(CAMINHO + "\\" + SUB_CAMINHO + "\\" + arquivo + EXTENSAO, true))) {

			String linha = "******* Simulação de Rendimento da Poupança ********";
			bw.append(linha + "\n\n");

			linha = "Nome = " + conta.getTitular().getNome();
			bw.append(linha + "\n");

			linha = "Simulação para CPF = " + conta.getCpf();
			bw.append(linha + "\n");

			linha = "O rendimento foi de: R$" + String.format("%.2f", rendimento);
			bw.append(linha + "\n");

			String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
			linha = "Operações realizada em: " + date;
			bw.append(linha + "\n");

			linha = "****************************************************";
			bw.append(linha + "\n\n");
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public static void relatorioContasPorAgencia(Conta conta, Funcionario funcionario) throws IOException { 

		SUB_CAMINHO = conta.getCpf() + "_" + conta.getTitular().getNome() + "_" + funcionario.getTipoDeUsuario();
		new File(CAMINHO + "\\" + SUB_CAMINHO).mkdir();

		String hojeFormatado = LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yy"));
		String arquivo = conta.getCpf() + "_" + conta.getAgencia() + "_" + hojeFormatado + "_relatorioContasPorAgencia";

		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(CAMINHO + "\\" + SUB_CAMINHO + "\\" + arquivo + EXTENSAO, true))) {

			int totalContas = 0;
			String linha = "****************** RESPONSÁVEL **********************";
			bw.append(linha + "\n\n");

			linha = "Nome = " + conta.getTitular().getNome();
			bw.append(linha + "\n");

			linha = "CPF: " + conta.getCpf();
			bw.append(linha + "\n");

			linha = "Agência : " + conta.getAgencia().getNumAgencia();
			bw.append(linha + "\n");

			linha = "*********************************************************";
			bw.append(linha + "\n\n");

			linha = "****************** TOTAL DE CONTAS NA AGÊNCIA ***************";
			bw.append(linha + "\n\n");

			for (String cpf : SistemaBancario.mapaDeContas.keySet()) {
				if (SistemaBancario.mapaDeContas.get(cpf).getAgencia().getNumAgencia()
						.equals(conta.getAgencia().getNumAgencia())) {

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
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void relatorioClientes(List<Conta> contas, Conta conta, Funcionario funcionario) { 

		SUB_CAMINHO = conta.getCpf() + "_" + conta.getTitular().getNome() + "_" + funcionario.getTipoDeUsuario();
		new File(CAMINHO + "\\" + SUB_CAMINHO).mkdir();

		String hojeFormatado = LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yy"));

		String arquivo = conta.getCpf() + "_" + hojeFormatado + "_relatorioDeClientes";

		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(CAMINHO + "\\" + SUB_CAMINHO + "\\" + arquivo + EXTENSAO, true))) {

			String linha = "******************* Informações dos Clientes *******************";
			bw.append(linha + "\n\n");

			Collections.sort(contas); 
			for (Conta c : contas) {
				linha = c.getAgencia().getNumAgencia() + " - " + c.getTitular();
				bw.append(linha + "\n");
			}

			String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
			linha = "Operações realizada em: " + date;
			bw.append(linha + "\n");

			linha = "************************************************************************";
			bw.append(linha + "\n\n");
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void relatorioCapitalBanco(List<Conta> listaContas, Conta conta, Funcionario funcionario) { 

		SUB_CAMINHO = conta.getCpf() + "_" + conta.getTitular().getNome() + "_" + funcionario.getTipoDeUsuario();
		new File(CAMINHO + "\\" + SUB_CAMINHO).mkdir();

		String hojeFormatado = LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yy"));
		String arquivo = conta.getCpf() + "_" + hojeFormatado + "_" + "relatorioCapitalBanco";

		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(CAMINHO + "\\" + SUB_CAMINHO + "\\" + arquivo + EXTENSAO, true))) {
			double capitalTotalBanco = 0;
			String linha = "******** TOTAL DE CAPITAL ARMAZENADO NO BANCO ********";
			bw.append(linha + "\n\n");

			for (Conta lista : listaContas) {
				capitalTotalBanco += lista.getSaldo();
			}

			linha = "Total do Capital armazenado no banco: R$" + capitalTotalBanco;
			bw.append(linha + "\n\n");

			String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
			linha = "Operação realizada em: " + date;
			bw.append(linha + "\n");

			linha = "******************************************************";
			bw.append(linha + "\n\n");

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
		
		public static void comprovanteSeguroDeVida(Conta conta, Cliente cliente) {

		SUB_CAMINHO = conta.getCpf() + "_" + conta.getTitular().getNome() + "_" + conta.getTitular().getTipoDeUsuario();
		new File(CAMINHO + "\\" + SUB_CAMINHO).mkdir();

		String hojeFormatado = LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yy"));
		String arquivo = conta.getCpf() + "_" + conta.getAgencia() + "_" + hojeFormatado + "_comprovanteSeguroDeVida";

		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(CAMINHO + "\\" + SUB_CAMINHO + "\\" + arquivo + EXTENSAO, true))) {

			String linha = "*************** COMPROVANTE SEGURO DE VIDA ********************";
			bw.append(linha + "\n");

			linha = "Nome = " + conta.getTitular().getNome();
			bw.append(linha + "\n");

			linha = "Simulação para CPF = " + Cliente.imprimeCPF(conta.getCpf());
			bw.append(linha + "\n");
			
			linha = "Agência: " + conta.getAgencia();
			bw.append(linha + "\n");

			linha = "Conta: " + conta.getNumConta();
			bw.append(linha + "\n");

			linha = "O valor pago na contratação do Seguro de Vida foi de: R$" +  String.format("%.2f", SeguroDeVida.getValorSeguro());
			bw.append(linha + "\n");
			
			linha = "O valor segurado após taxação foi de: R$" + String.format("%.2f", SeguroDeVida.getValorSeguroAposTaxa());
			bw.append(linha + "\n");
			
			linha = "Você pagou de tarifa R$" + String.format("%.2f", SeguroDeVida.getValorTributacao());
			bw.append(linha + "\n");

			String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

			linha = "Operação realizada em: " + date;
			bw.append(linha + "\n");

			linha = "*************************************************************";
			bw.append(linha + "\n\n");

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
}