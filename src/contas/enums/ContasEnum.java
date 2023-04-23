package contas.enums;

public enum ContasEnum {  // TESTE

	CORRENTE("Conta Corrente", 1),
    POUPANCA("Poupança", 2);

    private final String tipoDeConta;
    private final Integer idConta;

    ContasEnum(String tipoDeConta, Integer idConta) {
        this.tipoDeConta = tipoDeConta;
        this.idConta = idConta;
    }

    public String getTipoConta() {
        return this.tipoDeConta;
    }

    public Integer getIdConta() {
        return this.idConta;
    }
}