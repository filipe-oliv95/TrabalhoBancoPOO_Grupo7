package segurosDeVida;

public abstract class SeguroDeVida {

	private final static double TAXA_SEGURO = 0.20;
	private static double valorSeguro;
	private static double valorSeguroAposTaxa;
	
	public SeguroDeVida(double valorSeguro) {
		super();
	}

	public static void setValorSeguroAposTaxa(double valorSeguroAposTaxa) {
		SeguroDeVida.valorSeguroAposTaxa = valorSeguroAposTaxa;
	}

	public static double getValorSeguro() {
		return valorSeguro;
	}

	public static void setValorSeguro(double valorSeguro) {
		SeguroDeVida.valorSeguro = valorSeguro;
	}

	public static double getTaxaSeguro() {
		return TAXA_SEGURO;
	}

	public static double getValorTributacao() {

		return getValorSeguro() * TAXA_SEGURO;
	}

	public static double getValorSeguroAposTaxa() {
		valorSeguroAposTaxa = valorSeguro * (1 - TAXA_SEGURO);
		return valorSeguroAposTaxa; 
	}

}