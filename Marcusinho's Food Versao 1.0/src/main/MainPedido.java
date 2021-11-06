package main;

import java.util.Scanner;
import universalMethods.UniversalMethods;

public class MainPedido {

	private static Scanner scan = new Scanner(System.in);

	public static void mainPedido() {
		while (true) {
			UniversalMethods.limparConsole();
			print("\tSeja bem vindo ao cadastro de Pedidos!\rPor favor escolha a opção de acordo com sua necessidade.");
			print("1 - Pedir.");
			print("2 - Mostrar o valor total do pedido.");
			print("3 - Atualizar o Status.");
			print("4 - Apagar um pedido.");
			print("5 - Consultar pelo nome.");
			print("6 - Consultar pelo nome e data.");
			print("7 - Consultar pelo id do pedido.");
			print("8 - Voltar para a tela principal.");

			String opcao = scan.next();

			switch (opcao) {
			case "1":
				UniversalMethods.limparConsole();
				methodsPedido.Methods.pedir();
				break;
				
			case "2":
				UniversalMethods.limparConsole();
				methodsPedido.Methods.mostrarConta();
				break;
				
			case "3":
				UniversalMethods.limparConsole();
				methodsPedido.Methods.updateStatus();
				break;
				
			case "4":
				UniversalMethods.limparConsole();
				methodsPedido.Methods.deletePedido();
				break;
				
			case "5":
				UniversalMethods.limparConsole();
				methodsPedido.Methods.findByName();
				break;
				
			case "6":
				UniversalMethods.limparConsole();
				methodsPedido.Methods.findByNameAndDate();
				break;
				
			case "7":
				UniversalMethods.limparConsole();
				methodsPedido.Methods.findByIdProduto();
				break;
				
			case "8":
				return;
				
			default:
				UniversalMethods.limparConsole();
				UniversalMethods.sleepMessage("\n\t\t---Digite uma opção correta!---\n");
				break;
			}
		}

	}

	private static void print(String message) {
		System.out.println(message);
	}
}
