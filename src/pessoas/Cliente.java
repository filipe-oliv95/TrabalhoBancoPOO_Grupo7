package pessoas;

import pessoas.enums.UsuariosEnum;

public class Cliente extends Usuario {

	public Cliente () {
		
	}

	public Cliente(UsuariosEnum tipoDeUsuario, String nome, String cpf, Integer senha) {
		super(tipoDeUsuario, nome, cpf, senha);
	}

	public static String imprimeCPF(String CPF) {
        return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
        CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }
	
	public int compareTo(Cliente titular) {
		if (this.getNome().compareTo(titular.getNome()) > 0) { // compara pelo nome
			return -1; 
		}
		if (this.getNome().compareTo(titular.getNome()) < 0) {
			return 1;
		} 
		return 0;
	}
	
	@Override
	public String toString() {
		return getNome() + " | CPF = " + imprimeCPF(getCpf());
	}
}
