package extratos;

import java.time.LocalDate;

public class Extrato {

//	private int numComprovante;
//	private String numConta;
	private LocalDate data;
	private String tipoDeMovimentacao;
	private double valor;
	
//	private static int totalDeOperacoes;	
	
	public Extrato() {
//		this.totalDeOperacoes++;
//		this.numComprovante = totalDeOperacoes++;
	}

	public Extrato(LocalDate data, String tipoMovimentacao, double valor) {
		super();
//		this.totalDeOperacoes++;
//		this.numComprovante = totalDeOperacoes++;
		this.data = data;
		this.tipoDeMovimentacao = tipoMovimentacao;
		this.valor = valor;
	}
	
//	public static int getTotalDeOperacoes() {
//		return totalDeOperacoes;
//	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getTipoDeMovimentacao() {
		return tipoDeMovimentacao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}	

	@Override
	public String toString() {
		return data + "  |  " +  tipoDeMovimentacao 
				+ "  |  R$ " + String.format("%.2f", valor);
	}	
}
