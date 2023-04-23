package extratos;

import java.time.LocalDate;

public class Extrato {

	private LocalDate data;
	private String tipoDeMovimentacao;
	private double valor;
	
	public Extrato() {
	}

	public Extrato(LocalDate data, String tipoMovimentacao, double valor) {
		super();
		this.data = data;
		this.tipoDeMovimentacao = tipoMovimentacao;
		this.valor = valor;
	}

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
