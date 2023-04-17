package pessoas;

import java.util.HashMap;
import java.util.Map;

import pessoas.enums.UsuariosEnum;

public class Diretor extends Funcionario {

	public static Map<String, Diretor> mapaDeDiretores = new HashMap<>();
		
	public Diretor() {
		super();
	}
	
	public Diretor(String nome, String cpf, Integer senha, UsuariosEnum tipoDeUsuario) {
		super(nome, cpf, senha, tipoDeUsuario);
	}

	@Override
	public String toString() {
		return "Nome = " + getNome() 
				+ ", CPF = " + imprimeCPF(getCpf())
				+ ", Cargo = " + getTipoDeUsuario();
	}
}
