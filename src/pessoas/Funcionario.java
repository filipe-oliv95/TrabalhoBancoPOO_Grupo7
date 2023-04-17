package pessoas;

import java.util.HashMap;
import java.util.Map;

import pessoas.enums.UsuariosEnum;

public abstract class Funcionario extends Usuario {

	public static Map<String, Funcionario> mapaDeFuncionarios = new HashMap<>(); 
	
	public Funcionario() {
		
	}		
		
	public Funcionario(String nome, String cpf, Integer senha, UsuariosEnum tipoDeUsuario) {
		super(nome, cpf, senha, tipoDeUsuario);
	}
	
	public static String imprimeCPF(String CPF) {
        return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
        CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }

	@Override
	public String toString() {
		return "[nome = " + getNome() 
				+ ", cpf = " + imprimeCPF(getCpf())
				+ ", Cargo = " + getTipoDeUsuario();
	}
}
