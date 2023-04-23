package principal;

import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

import contas.Conta;
import io.Leitor;
import menus.Menu;
import pessoas.Cliente;
import pessoas.Diretor;
import pessoas.Funcionario;
import pessoas.Gerente;

public class SistemaBancario {

    public static Map<String, Gerente> mapaDeGerentes = new HashMap<>();
    public static Map<String, Diretor> mapaDeDiretores = new HashMap<>();
    public static Map<String, Funcionario> mapaDeFuncionarios = new HashMap<>();
    public static Map<String, Cliente> mapaDeClientes = new HashMap<>();
    public static Map<String, Conta> mapaDeContas = new HashMap<>();
//    public static Map<String, Usuario> mapaDeUsuarios = new HashMap<>();
//    public static Map<String, Presidente> mapaDePresidente = new HashMap<>();

    public static void main(String[] args) throws InputMismatchException, NullPointerException, IOException {

    	// POPULANDO OS DADOS DO SISTEMA
        Leitor.leitura(".\\database\\registrodedados.txt");
        
       	Menu.menuInicial();

    }
}