package pessoas;

import pessoas.enums.UsuariosEnum;

public class Diretor extends Funcionario {

	public Diretor() {
		super();
	}
	
	public Diretor(UsuariosEnum tipoDeUsuario, String nome, String cpf, Integer senha) {
		super(tipoDeUsuario, nome, cpf, senha);
	}

	@Override
	public String toString() {
		return "Nome = " + getNome() 
				+ ", CPF = " + imprimeCPF(getCpf())
				+ ", Cargo = " + getTipoDeUsuario();
	}
}
