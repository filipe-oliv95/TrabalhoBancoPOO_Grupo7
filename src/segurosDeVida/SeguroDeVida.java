package segurosDeVida;

public abstract class SeguroDeVida {

	private final static double TAXA_SEGURO = 0.20;
	private static double TARIFA; // vai somando os valores a cada contratação de seguro
	private static double valorSeguro;
	private static double valorSeguroAposTaxa;
	
	public SeguroDeVida(double valorSeguro, double valor) {
		super();
	}

	public static double getTARIFA() {
		return TARIFA;
	}

	public static void setValorSeguroAposTaxa(double valorSeguroAposTaxa) {
		SeguroDeVida.valorSeguroAposTaxa = valorSeguroAposTaxa;
	}

	public static double getValorSeguro() { //retorna 1000
		return valorSeguro;
	}

	public static void setValorSeguro(double valorSeguro) {
		SeguroDeVida.valorSeguro = valorSeguro;
	}

	public static void setTARIFA(double tARIFA) {
		TARIFA = tARIFA;
	}

	public static double getTaxaSeguro() {
		return TAXA_SEGURO;
	}

	public static double getValorTributacao() {

		return getValorSeguro() * TAXA_SEGURO;
	}

	public static double getValorSeguroAposTaxa(boolean somarTarifa) {
		valorSeguroAposTaxa = valorSeguro * (1 - TAXA_SEGURO);
		if(somarTarifa == true) {
			TARIFA += (valorSeguro - valorSeguroAposTaxa);
		}
		return valorSeguroAposTaxa; // retorna 800
	}

}