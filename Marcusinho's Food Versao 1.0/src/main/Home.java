package main;


import java.util.Scanner;
import universalMethods.UniversalMethods;

public class Home {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		
		while (true) {
			UniversalMethods.limparConsole();
			print("\t\tTela Principal");
			print("--- Escolha qual op��o deseja utilizar! ---");
			print("\r1 - �rea do Cliente!");
			print("\r2 - �rea do Produto!");
			print("\r3 - �rea do Pedido!");
			print("\r4 - Sair da Aplica��o!");

			print("\nDigite Aqui: \r");
			String escolhaMethod = scanner.next();

			switch (escolhaMethod) {
			case "1":
				main.MainCliente.mainCliente();
				break;
				
			case "2":
				main.MainProduto.mainProduto();
				break;
				
			case "3":
				main.MainPedido.mainPedido();
				break;
				
			case "4":
				UniversalMethods.exit();
				System.exit(0);
				break;
				
			default:
				UniversalMethods.sleepMessage("Informe uma op��o existente");
				break;
			}
		}
	}

	private static void print(String message) {
		System.out.println(message);

	}
}
