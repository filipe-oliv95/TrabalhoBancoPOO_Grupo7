package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Escritor {
	
	public static void escritura(String path) throws IOException {
	
	Scanner sc = new Scanner(System.in);
	
	System.out.println("Digite o nome do arquivo: ");
	String nomeArquivo = sc.nextLine(); 
	
	try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
	
		while (true) {
			String linha = sc.nextLine();
			if (linha.equals("/fim"))
				break;
			bw.append(linha);
			bw.newLine();
		}
		
		System.out.println("Arquivo gravado com sucesso!");
		bw.close();
		sc.close();
	}
	catch (IOException e){ 
		System.out.println("Erro na escrita dos dados: " + e.getMessage());
	}			
}


}


