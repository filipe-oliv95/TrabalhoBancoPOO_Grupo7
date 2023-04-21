package principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import relatorios.Relatorio;

public class SistemaBancario {

    //public static Map<String, Agencia> mapaDeAgencias = new HashMap<>();
    public static Map<String, Gerente> mapaDeGerentes = new HashMap<>();
    public static Map<String, Diretor> mapaDeDiretores = new HashMap<>();
    public static Map<String, Funcionario> mapaDeFuncionarios = new HashMap<>();
    public static Map<String, Cliente> mapaDeClientes = new HashMap<>();
    public static Map<String, Conta> mapaDeContas = new HashMap<>();
    public static Map<String, Usuario> mapaDeUsuarios = new HashMap<>();
    public static Map<String, Presidente> mapaDePresidente = new HashMap<>();

    public static void main(String[] args) throws IOException {

        Leitor.leitura(".\\database\\registrodedados.txt");
        
        try { // TRATAMENTO EXCEÇÃO IO
        	Menu.menuInicial();
        }
        catch(IOException e){
			System.out.println(e.getMessage());
		}
		finally{
			Menu.menuInicial();
		}        
    }
}