package menus;

import java.util.Locale;
import java.util.Scanner;

import contas.Conta;

public class Menu {

    
    public static void imprimirMenuCliente(Conta conta) {
        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);
        
        System.out.println("Escolha entre as opções abaixo:");
        System.out.println("[1] Saldo");
        System.out.println("[2] Depósito");
        System.out.println("[3] Transferência para outra conta");
        System.out.println("[4] Extrato da conta");
        
        int opcao = sc.nextInt();
        //adicionar o retorno
        switch (opcao) {
        case 1: System.out.printf("Saldo: %.2f", conta.getSaldo());
            break;
        case 2: 
            System.out.println("Quanto você deseja depositar?");
            double valor = sc.nextDouble();
            conta.depositar(valor);
            System.out.printf("Novo saldo: %.2f", conta.getSaldo());
            break;
        case 3:
            System.out.println("Qual o valor da transferencia?");
            valor = sc.nextDouble();
            sc.nextLine();
            System.out.println("Qual o CPF do destinatário?");
            String cpf = sc.nextLine();
            
            Conta contaDestino = Conta.mapaDeContas.get(cpf);            
            System.out.println("\nQuem é o titular da conta (Objeto Cliente dentro de Conta) => " + contaDestino.getTitular().getNome());
            System.out.println(contaDestino + "\n");
            
            System.out.printf("Saldo destino antes de receber: %.2f%n", contaDestino.getSaldo());
            conta.transferir(contaDestino, valor);
            System.out.printf("Novo saldo: %.2f%n", contaDestino.getSaldo());
            break;
        default:
            break;
        }
    sc.close();
    }    
}


 
