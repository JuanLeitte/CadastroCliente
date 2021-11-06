package main;

import java.util.Scanner;

import universalMethods.UniversalMethods;

public class MainCliente {
	private static Scanner scan = new Scanner(System.in);

	public static void mainCliente() {
		while (true) {
			UniversalMethods.limparConsole();
			print("\tSeja bem vindo ao cadastro de clientes!\rPor favor escolha a opção de acordo com sua necessidade.");
			print("1 - Cadastrar um cliente.");
			print("2 - Consultar os clientes.");
			print("3 - Consultar por id.");
			print("4 - Consultar por nome.");
			print("5 - Deletar cadastro de cliente.");
			print("6 - Atualizar cadastro de cliente.");
			print("7 - Voltar para a tela principal.");

			print("\nDigite aqui: ");
			String option = scan.next();

			switch (option) {
			case "1":
				UniversalMethods.limparConsole();
				methodsCliente.Methods.create();
				break;
				
			case "2":
				UniversalMethods.limparConsole();
				methodsCliente.Methods.read();
				break;
				
			case "3":
				UniversalMethods.limparConsole();
				methodsCliente.Methods.findById();
				break;
				
			case "4":
				UniversalMethods.limparConsole();
				methodsCliente.Methods.findByName();
				break;
				
			case "5":
				UniversalMethods.limparConsole();
				methodsCliente.Methods.delete();
				break;
				
			case "6":
				UniversalMethods.limparConsole();
				methodsCliente.Methods.update();
				break;
				
			case "7":
				return;
				
			default:
				UniversalMethods.limparConsole();
				UniversalMethods.sleepMessage("\n\t\t---Digite uma opÃ§Ã£o correta!---\n");
				break;
			}
		}
	}

	private static void print(String message) {
		System.out.println(message);
	}
}
