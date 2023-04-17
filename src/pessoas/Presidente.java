package pessoas;

import java.util.HashMap;
import java.util.Map;

import pessoas.enums.UsuariosEnum;

public class Presidente extends Funcionario {

	public static Map<String, Presidente> mapaPresidente = new HashMap<>();
	
	public Presidente() {
		super();
	}

	public Presidente(String nome, String cpf, Integer senha, UsuariosEnum tipoDeUsuario) {
		super(nome, cpf, senha, tipoDeUsuario);
	}

	
	@Override
	public String toString() {
		return "Nome =" + getNome() 
					+ ", CPF = " + imprimeCPF(getCpf()) 
					+ ", Cargo = " + getTipoDeUsuario();
	}	
}
