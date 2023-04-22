package relatorios;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import contas.Conta;
import contas.ContaCorrente;
import contas.ContaPoupanca;
import io.Escritor;
import menus.Menu;
import pessoas.Cliente;
import pessoas.Gerente;
import principal.SistemaBancario;
import segurosDeVida.SeguroDeVida;

public class Relatorio {

	public static void imprimirSaldo(Conta conta) { // RELATORIO CLIENTE e GERENTE
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		System.out.println();
		System.out.println("******** Saldo na Conta ********");
		System.out.println("Tipo de conta: " + conta.getTipoDeConta().name());
		System.out.println("Número da conta: " + conta.getNumConta());
		System.out.println("Saldo: " + String.format("R$ %.2f", conta.getSaldo()));
		System.out.println("Data: " + sdf.format(date));
		System.out.println("********************************");

		Escritor.comprovanteSaldo(conta);
	}

	public static void tributacaoCC(Conta conta) {
		System.out.println();
		System.out.println("****** Relatório de tributação ******");
		System.out.println();
		System.out.println("Valor de tarifa cobrado no saque: R$ " + ContaCorrente.getTarifaSaque());
		System.out.println("Total de saques: " + ContaCorrente.getTotalSaques());
		System.out.println();
		System.out.println("Valor de tarifa cobrado no depósito: R$ " + ContaCorrente.getTarifaDeposito());
		System.out.println("Total de depósitos: " + ContaCorrente.getTotalDepositos());
		System.out.println();
		System.out.println("Valor de tarifa cobrado na tranferência: R$ " + ContaCorrente.getTarifaTransferencia());
		System.out.println("Total de transferências: " + ContaCorrente.getTotalTransferencias());
		System.out.println("**************************************");
		System.out.println();

		double tarifaTotalSaque = ContaCorrente.getTotalSaques() * ContaCorrente.getTarifaSaque();
		double tarifaTotalDeposito = ContaCorrente.getTotalDepositos() * ContaCorrente.getTarifaDeposito();
		double tarifaTotalTransferencia = ContaCorrente.getTotalTransferencias()
				* ContaCorrente.getTarifaTransferencia();

		System.out.println("Total de tarifas cobradas em saques: R$" + tarifaTotalSaque);
		System.out.println("Total de tarifas cobradas em depósitos: R$" + tarifaTotalDeposito);
		System.out.println("Total de tarifas cobradas em transferências: R$" + tarifaTotalTransferencia);
		double somaTarifas = tarifaTotalSaque + tarifaTotalDeposito + tarifaTotalTransferencia;
		System.out.println("Soma de todas as tarifas: R$" + somaTarifas);
		System.out.println();
		
		if(Menu.contratoSeguro == true) {
			System.out.println("O valor adicionado para seu seguro foi de: R$ " + SeguroDeVida.getValorSeguroAposTaxa(false));
		}
		
//		Escritor.relatorioTributacaoCC(conta); // VAI DEIXAR ASSIM OU MODIFICAR OS DADOS ACIMA?
	}

	public static void simularRendimentoPoupanca(Conta conta, Cliente cliente) {

		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("****** Simulação de Rendimento da Poupança ******");
		Double valor = 0.0;
		Integer dias = 0;
		do {
			System.out.print("Qual valor deseja simular: R$ ");
			valor = sc.nextDouble();
			if (valor < 0 || valor == null)
				System.out.println("Insira um valor positivo em reais. \n");
		} while (valor < 0);

		do {
			System.out.printf("Quantos dias deseja saber o rendimento: ");
			dias = sc.nextInt();
			if (dias < 0)
				System.out.println("Insira um valor positivo de dias. \n");
		} while (dias < 0|| dias == null);

		Double rendimento = valor * ((ContaPoupanca.getTaxaRendimento() / 30) * dias);
		System.out.printf("O rendimento para o prazo informado: %.2f%n", rendimento);
		System.out.printf("Valor final ficaria: R$ %.2f", valor + rendimento);
		Escritor.rendimentDaPoupanca(conta, cliente, rendimento);

		System.out.println();
		System.out.println("**************************************************");

		try { // CORREÇÃO DO ERRO DO LOOPING NA CONSULTA RENDIMENTO
			Menu.menuRelatorio(conta, cliente);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sc.close();

	}

	public static void numDeContasNaAgencia(Conta conta, String cpf) throws IOException {
		int totalContas = 0;
		Gerente gerente = SistemaBancario.mapaDeGerentes.get(cpf);
		System.out.println("****** Contas na agência do Gerente ******");
		try {
			if (gerente.getCpf().equals(cpf)) {
				for (String cpfConta : SistemaBancario.mapaDeContas.keySet()) {
					if (SistemaBancario.mapaDeContas.get(cpfConta).getAgencia().getNumAgencia()
							.equals(gerente.getAgencia().getNumAgencia())) {
						System.out.println(SistemaBancario.mapaDeContas.get(cpfConta));
						totalContas++;
					}
				}
				System.out.println("Total de contas na agência : " + totalContas);
				System.out.println("*********************************");
				System.out.println();
				Escritor.relatorioContasPorAgencia(conta);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			// COLOCAR O QUE AQUI?
		}
	}

	public static void informacoesClientes(List<Conta> contas) throws IOException { // RELATORIO DIRETOR
		System.out.println("***** Informações dos Clientes *****");
		Collections.sort(contas); // ordenou a lista usando o Comparable no Contas
		for (Conta conta : contas) {
			System.out.printf("NOME: %s\t| AGÊNCIA: %s\n", conta.getTitular(), conta.getAgencia());
		}
		System.out.println("**************************************************");
		System.out.println();

		Escritor.relatorioClientes(contas);
		// ESCRITOR
	}

	public static void valorTotalCapitalBanco(List<Conta> listaContas) { // PRESIDENTE

		System.out.println("********* Consulta do Capital do Banco ***********");
		double capitalBancoSaldo = 0;
		for (Conta lista : listaContas) {
			capitalBancoSaldo += lista.getSaldo();
		}
		System.out.printf("Total em saldo: R$ %.2f%n", capitalBancoSaldo);

		System.out.println("**************************************************");
		System.out.println();
		// ADICIONAR O ESCRITOR
		Escritor.relatorioCapitalBanco(listaContas);
	}

	public static void SeguroDeVida(Conta conta, Cliente cliente) {

		System.out.println();
		System.out.println("*********** Seguro de vida ***********");
		System.out.println();
		System.out.println("NOME : " + cliente.getNome());
		System.out.println("CPF : " + cliente.getCpf());
		System.out.println("O valor debitado do seu saldo foi de: R$ " + SeguroDeVida.getValorSeguro());
		System.out.println("Seu seguro de vida é de: R$ " + SeguroDeVida.getValorSeguroAposTaxa(true));
		System.out.printf("Você pagou R$ %.2f de tarifa.", SeguroDeVida.getValorTributacao());
		System.out.println();
		System.out.println("**************************************");
	}

}