package main;
import java.util.Scanner;
import universalMethods.UniversalMethods;//UniversalMethods.cls();

public class MainProduto {
	private static Scanner scan = new Scanner(System.in);

	public static void mainProduto() {

		while (true) {
			UniversalMethods.limparConsole();
			print("\tSeja bem vindo ao cadastro de Produtos!\rPor favor escolha a opção de acordo com sua necessidade.");
			print("1 - Cadastrar um produto.");
			print("2 - Consultar os produtos.");
			print("3 - Consultar por nome.");
			print("4 - Atualizar cadastro de um produto.");
			print("5 - Atualizar disponibilidade do produto.");
			print("6 - Achar um produto por Id");
			print("7 - Deletar um produto.");
			print("8 - Voltar para a tela principal.");
			print("\nDigite Aqui: ");

			String opcao = scan.next();

			switch (opcao) {
			case "1":
				UniversalMethods.limparConsole();
				methodsProduto.Methods.create();
				break;
				
			case "2":
				UniversalMethods.limparConsole();
				methodsProduto.Methods.read();
				break;
				
			case "3":
				UniversalMethods.limparConsole();
				methodsProduto.Methods.findByName();
				break;
				
			case "4":
				UniversalMethods.limparConsole();
				methodsProduto.Methods.update();
				break;
				
			case "5":
				UniversalMethods.limparConsole();
				methodsProduto.Methods.updateStatusProduto();
				break;
				
			case "6":
				UniversalMethods.limparConsole();
				methodsProduto.Methods.findByIdProduto();
				break;
				
			case "7":
				UniversalMethods.limparConsole();
				methodsProduto.Methods.deleteProduto();
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
