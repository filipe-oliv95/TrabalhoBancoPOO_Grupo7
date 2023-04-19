package pessoas;

import java.util.HashMap;
import java.util.Map;

import pessoas.enums.UsuariosEnum;

public abstract class Funcionario extends Usuario {

	public static Map<String, Funcionario> mapaDeFuncionarios = new HashMap<>(); 
	
	public Funcionario() {
		
	}		
		
	public Funcionario(UsuariosEnum tipoDeUsuario, String nome, String cpf, Integer senha) {
		super(tipoDeUsuario, nome, cpf, senha);
	}
	
	public static String imprimeCPF(String CPF) {
        return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
        CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }

	@Override
	public String toString() {
		return getNome() + ", cpf = " + imprimeCPF(getCpf())
				+ ", Cargo = " + getTipoDeUsuario();
	}
}
