package pessoas.enums;

public enum UsuariosEnum { // TESTE

	CLIENTE("Cliente", 1),
	FUNCIONARIO("Funcionário", 2),
	GERENTE("Gerente", 3),
	DIRETOR("Diretor", 4),
	PRESIDENTE("Presidente", 5);

	private final String tipoPessoa;
	private final Integer idPessoa;
	
	UsuariosEnum(String tipo, int id) {
		this.tipoPessoa = tipo;
		this.idPessoa = id;
	}
	
	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}
	
}
