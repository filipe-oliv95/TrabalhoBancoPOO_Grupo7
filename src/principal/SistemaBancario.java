package principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import agencias.Agencia;
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

	public static Map<String, Agencia> mapaDeAgencias = new HashMap<>();
	public static Map<String, Gerente> mapaDeGerentes = new HashMap<>();
	public static Map<String, Diretor> mapaDeDiretores = new HashMap<>();
	public static Map<String, Funcionario> mapaDeFuncionarios = new HashMap<>();
	public static Map<String, Cliente> mapaDeClientes = new HashMap<>();
	public static Map<String, Conta> mapaDeContas = new HashMap<>();
	public static Map<String, Usuario> mapaDeUsuarios = new HashMap<>();
	public static Map<String, Presidente> mapaDePresidente = new HashMap<>();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Leitor.leitura(".\\database\\registrodedados.txt");


		System.out.println("Testando Mapa Funcionários =>  " + mapaDeClientes.get("56489723114"));
		System.out.println(mapaDeFuncionarios.get("80466528906"));

//		List<Conta> listaContas = new ArrayList<>(mapaDeContas.values());
//		for (Conta c : listaContas) {
//			System.out.println(c);
//		}

		Menu.menuInicial();
		
		
		sc.close();
	}
}