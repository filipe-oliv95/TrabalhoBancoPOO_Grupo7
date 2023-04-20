package pessoas;

import pessoas.enums.UsuariosEnum;

public class Presidente extends Funcionario {

	public Presidente() {
		super();
	}

	public Presidente(UsuariosEnum tipoDeUsuario, String nome, String cpf, Integer senha) {
		super(tipoDeUsuario, nome, cpf, senha);
	}

	
	@Override
	public String toString() {
		return "Nome =" + getNome() 
					+ ", CPF = " + imprimeCPF(getCpf()) 
					+ ", Cargo = " + getTipoDeUsuario();
	}	
}
