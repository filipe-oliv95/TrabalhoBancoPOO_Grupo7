package pessoas;

import java.util.HashMap;
import java.util.Map;

import agencias.Agencia;
import pessoas.enums.UsuariosEnum;

public class Gerente extends Funcionario {

    private Agencia agencia;
    
    public static Map<String, Gerente> mapaDeGerentes = new HashMap<>();
    
    public Gerente() {
        super();
    }
    
    public Gerente(Agencia agencia) {
        super();
        this.agencia = agencia;
    }
    
    public Gerente(UsuariosEnum tipoDeUsuario,String nome, String cpf, Integer senha, Agencia agencia) {
        super(tipoDeUsuario, nome, cpf, senha);
        this.agencia = agencia;
    }        

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }    
    

    @Override
    public String toString() {
        return "Nome = " + getNome() 
                + ", Cpf = " + imprimeCPF(getCpf())
                + ", Agencia = " + agencia
                + ", Cargo = " + getTipoDeUsuario();                
    }    
}