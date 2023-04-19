package pessoas;

import java.util.HashMap;
import java.util.Map;

import pessoas.enums.UsuariosEnum;

public abstract class Usuario {

	private String nome;
	private String cpf;
	private Integer senha;
	private UsuariosEnum tipoDeUsuario;
	
	public static Map<String, Usuario> mapaDeUsuarios = new HashMap<>();
	
	public Usuario() {
		super();
	}

	public Usuario(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public Usuario(UsuariosEnum tipoDeUsuario, String nome, String cpf, Integer senha) {
		this.tipoDeUsuario = tipoDeUsuario;
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}	

	public Integer getSenha() {
		return senha;
	}

	public void setSenha(Integer senha) {
		this.senha = senha;
	}

	public UsuariosEnum getTipoDeUsuario() {
		return tipoDeUsuario;
	}

	public void setTipoDeUsuario(UsuariosEnum tipoDeUsuario) {
		this.tipoDeUsuario = tipoDeUsuario;
	}	
	
	@Override
	public String toString() {
		return "Nome=" + nome + ", cpf=" + cpf 
					+ ", tipoDeUsuario=" + tipoDeUsuario;
	}	
}
