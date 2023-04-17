package relatorios;

public class Relatorio {

	public void imprimirSaldo(double valor) { // RELATORIO CLIENTE e GERENTE
		// O sistema deverá imprimir o saldo na tela do terminal;
	}
	
	public void tributacaoCC() { // RELATORIO CLIENTE e GERENTE
		// Relatório de tributação da conta corrente , total gastos de tributação e tarifa de cada operação
		// saque 0.10 - deposito 0.10 - transferencia 0.20 (apenas remetente)
	}
	
	public void rendimentoPoup() { // RELATORIO CLIENTE e GERENTE
		// Relatório de Rendimento da poupança, simulação do valor de rendimento da poupança no prazo
		// valor dinheiro (double) e qtd de dias (inteiro) = rendimento no prazo informado
	}
	
	public void numDeContasNaAgencia() { // RELATORIO GERENTE
		// Relatório do número contas na mesma agência em que este gerente trabalha.
	}
	
	public void informacoesClientes() {  // RELATORIO DIRETOR
		// Nome, CPF, Agencia de todos clientes do Sistema (em ordem alfabética)
	}
	
	private double totalCapital = 0;
	
	public void imprimirCapital() { // RELATORIO PRESIDENTE		
		// Relatório com o valor total do capital armazenado no banco
	}  // somatório dos SALDOs das contas e somatório das tarifas
	
}
