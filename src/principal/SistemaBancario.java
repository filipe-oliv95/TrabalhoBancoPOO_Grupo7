package principal;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import contas.Conta;
import io.Leitor;
import menus.Menu;
import pessoas.Cliente;
import pessoas.Diretor;
import pessoas.Funcionario;
import pessoas.Gerente;
import pessoas.Presidente;
import pessoas.Usuario;

public class SistemaBancario {

    public static Map<String, Gerente> mapaDeGerentes = new HashMap<>();
    public static Map<String, Diretor> mapaDeDiretores = new HashMap<>();
    public static Map<String, Funcionario> mapaDeFuncionarios = new HashMap<>();
    public static Map<String, Cliente> mapaDeClientes = new HashMap<>();
    public static Map<String, Conta> mapaDeContas = new HashMap<>();
    public static Map<String, Usuario> mapaDeUsuarios = new HashMap<>();
    public static Map<String, Presidente> mapaDePresidente = new HashMap<>();

    public static void main(String[] args) {

    	// POPULANDO OS DADOS DO SISTEMA
        Leitor.leitura(".\\database\\registrodedados.txt");
        
        
        try {
        	Menu.menuInicial();
        }
        catch(IOException e){
			System.out.println(" Ërro: " + e.getMessage());
		}  
    }
}