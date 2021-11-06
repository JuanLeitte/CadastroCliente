package methodsProduto;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Produto;
import repository.ProdutoDatabaseMethods;
public class SubMethods {
	private static Scanner scan = new Scanner(System.in);
	
	public static void verificarID(int Int) throws IllegalArgumentException {
		ArrayList <Produto> produtos = ProdutoDatabaseMethods.getIdProduto();

		for (int i = 0; i < produtos.size(); i++) {
			int intProduto = produtos.get(i).getIdProduto();
			if (Int == intProduto) {
			return;
			}
		}
		throw new IllegalArgumentException();
	}

	public static String confirmacao() {
		while (true) {
			String escolha = scan.next();
			switch (escolha) {
			case "1)":
			case "1-":
			case "1":
				return "Disponivel";
			case "2)":
			case "2-":
			case "2":
				return "Indisponivel";
			default:
				System.out.println("Escolha uma opção valida");
			}
		}
	}
}
