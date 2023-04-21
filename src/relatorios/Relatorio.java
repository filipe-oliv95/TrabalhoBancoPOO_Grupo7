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
import pessoas.Gerente;
import principal.SistemaBancario;

public class Relatorio {

	public static void imprimirSaldo(Conta conta) { // RELATORIO CLIENTE e GERENTE
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/aaaa HH:mm:ss");
		Date date = new Date();
		System.out.println("####### Saldo na conta ########");
		System.out.println("Tipo de conta: " + conta.getTipoDeConta());
		System.out.println("Número da conta: " + conta.getNumConta());
		System.out.println("Saldo: " + String.format("R$ %.2f", conta.getSaldo()));
		System.out.println("Data: " + sdf.format(date));

		Escritor.comprovanteSaldo(conta);
	}

	public static void tributacaoCC(ContaCorrente conta) {
		System.out.println("Valor de tarifa cobrado no saque: R$" + ContaCorrente.getTarifaSaque());
		System.out.println("Total de saques: " + ContaCorrente.getTotalSaques());
		System.out.println("Valor de tarifa cobrado no depósito: R$" + ContaCorrente.getTarifaDeposito());
		System.out.println("Total de depósitos: " + ContaCorrente.getTotalDepositos());
		System.out.println("Valor de tarifa cobrado na tranferência: R$" + ContaCorrente.getTarifaTransferencia());
		System.out.println("Total de transferências: " + ContaCorrente.getTotalTransferencias());
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

		Escritor.relatorioTributacaoCC(conta); // VAI DEIXAR ASSIM OU MODIFICAR OS DADOS ACIMA?
	}

	public static void simularRendimentoPoupanca() {
		Scanner sc = new Scanner(System.in);
		// Relatório de Rendimento da poupança, simulação do valor de rendimento da
		System.out.println("Qual valor deseja simular? ");
		double valor = sc.nextDouble();
		System.out.println("Quantos dias deseja saber o rendimento? ");
		int dias = sc.nextInt();

		double rendimento = valor * ((ContaPoupanca.getTaxaRendimento() / 30) * dias);
		System.out.printf("O rendimento para o prazo informado: %.2f%n", rendimento);
		System.out.printf("Valor final ficaria: %.2f", valor + rendimento);

		// LeitorEscritor.escritura();
		sc.close();
	}

	public static void numDeContasNaAgencia(Conta conta,  String cpf) throws IOException {
        int totalContas = 0;
        Gerente gerente = SistemaBancario.mapaDeGerentes.get(cpf);
        
        try {
        	if(gerente.getCpf().equals(cpf)){ 
	           for (String cpfConta : SistemaBancario.mapaDeContas.keySet()) {
	                if (SistemaBancario.mapaDeContas.get(cpfConta).getAgencia().getNumAgencia().equals(gerente.getAgencia().getNumAgencia())) {
	                    System.out.println(SistemaBancario.mapaDeContas.get(cpfConta));
	                    totalContas++;
	                }
	            }
	            System.out.println("Total de contas na agência : " + totalContas);
	            Escritor.relatorioContasPorAgencia(conta);	         
        	}
        }
        catch(IOException e) {
        	System.out.println(e.getMessage());
        }
        finally {
        	// COLOCAR O QUE AQUI?
        }
      }
	
	public static void informacoesClientes(List<Conta> contas) { // RELATORIO DIRETOR
		Collections.sort(contas); // ordenou a lista usando o Comparable no Contas
		for (Conta conta : contas) {
			System.out.printf("NOME: %s\t AGÊNCIA: %s\n", conta.getTitular(), conta.getAgencia());
		}

		// ESCRITOR
	}	
	
	public static void valorTotalCapitalBanco(List<Conta> listaContas) { // PRESIDENTE
		
		double capitalBancoSaldo = 0;
		for(Conta lista : listaContas) {
			capitalBancoSaldo += lista.getSaldo();
		}
		System.out.printf("Total em saldo: R$ %.2f%n", capitalBancoSaldo);
		System.out.printf("Total de tarifas: R$ %.2f%n",  ContaCorrente.getTotalTarifas());
		
		double capitalBancoTotal = capitalBancoSaldo + ContaCorrente.getTotalTarifas();
		System.out.printf("Total em saldo + tarifas: R$ %.2f%n", capitalBancoTotal);
		// ADICIONAR O ESCRITOR
		Escritor.relatorioCapitalBanco(listaContas, capitalBancoTotal);
	}
}