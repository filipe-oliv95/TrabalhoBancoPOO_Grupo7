package pessoas;

import java.util.HashMap;
import java.util.Map;

import pessoas.enums.UsuariosEnum;

public class Diretor extends Funcionario {

	public static Map<String, Diretor> mapaDeDiretores = new HashMap<>();
		
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
