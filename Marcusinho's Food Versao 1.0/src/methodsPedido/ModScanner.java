package methodsPedido;

import java.util.ArrayList;
import java.util.Scanner;
import entity.Carrinho;
import entity.Produto;
import repository.CardapioDatabaseMethods;
import universalMethods.UniversalMethods;

public class ModScanner {
	private static Scanner scan = new Scanner(System.in);
	private static ArrayList<String> pedidoArrayList = new ArrayList<>();
	static ArrayList<Carrinho> carrinhos = new ArrayList<>();

	public static void terminarPedido(int index) {

		int indexPedido = 0;
		ArrayList<Produto> arrayListCardapio = SubMethods.adicionarCardapio(index);
		String[] pedidoArray = pedidoArrayList.get(indexPedido).split(" ");
		indexPedido++;
		int pedidoNumero = 1;
		for (String pedido : pedidoArray) {
			pedido.trim();
			System.out.println("\nPedido nº " + pedidoNumero + " - "
					+ arrayListCardapio.get(Integer.parseInt(pedido) - 1).getNome() + "\nQuantidade: \n");
			int quantidade = UniversalMethods.scanInt();
			if (quantidade == 0) {
				UniversalMethods.sleepMessage("Pedido nº " + (pedidoNumero) + " - Cancelado :(", 1500);
				pedidoNumero++;
				continue;
			}
			Carrinho carrinho = new Carrinho();
			carrinho.setPedido(arrayListCardapio.get(Integer.parseInt(pedido) - 1).getIdProduto());
			carrinho.setNomeProduto(arrayListCardapio.get(Integer.parseInt(pedido) - 1).getNome());
			carrinho.setQuantidade(quantidade);
			carrinhos.add(carrinho);
			UniversalMethods.sleepMessage(
					arrayListCardapio.get(Integer.parseInt(pedido) - 1).getNome() + " --> Adicionado ao Carrinho", 1500);
			pedidoNumero++;
		}
		pedidoArrayList.clear();
	}

	public static void carrinho(int idCliente) {
		UniversalMethods.limparConsole();
		if (carrinhos.isEmpty()) {
			UniversalMethods.sleepMessage("Carrinho Vazio...");
			return;
		}
		scan.useDelimiter("\r\n");
		int i = 1;
		System.out.println("Produtos: ");
		for (Carrinho carrinho : carrinhos) {
			System.out.println(
					"\t" + i + ") " + carrinho.getNomeProduto() + ", Quantidade -- " + carrinho.getQuantidade());
			i++;
		}
		System.out.println("\n1) Finalizar Pedido");
		System.out.println("2) Apagar um Produto");
		System.out.println("3) Esvaziar o Carrinho");
		System.out.println("4) Voltar");

		String escolha = scan.next();

		switch (escolha) {
		case "1":
			System.out.println("Quem ira receber o pedido? ");
			String recebedor = scan.next();
			int idPedido = CardapioDatabaseMethods.pedirProduto(idCliente, recebedor);
			for (Carrinho carrinho : carrinhos) {
				CardapioDatabaseMethods.pedir(idPedido, carrinho.getPedido(), carrinho.getQuantidade());
			}
			UniversalMethods.sleepMessage("Pedido " + idPedido + " registrado com sucesso");
			carrinhos.clear();
			return;

		case "2":
			System.out.println("Escreva o número do produto que queira apagar: ");
			int escolhaExclusao = UniversalMethods.scanInt();
			UniversalMethods.sleepMessage(carrinhos.get(escolhaExclusao - 1).getNomeProduto() + " --> Foi excluido do carrinho");
			carrinhos.remove(escolhaExclusao - 1);
			break;

		case "3":
			System.out.println("Tem certeza que deseja esvaziar o carrinho? \n1 - Sim\n2 - Não");
			if (UniversalMethods.confirmacao()) {
				carrinhos.clear();
				UniversalMethods.sleepMessage("Carrinho esvaziado!");
			}
			break;

		case "4":
			return;

		default:
			UniversalMethods.sleepMessage("Digite uma opção válida");
			break;
		}
	}

	public static void perguntarPedido(ArrayList<Produto> produto) throws IllegalArgumentException {

		if (produto.isEmpty()) {
			UniversalMethods.sleepMessage("Setor vazio");
			throw new IllegalArgumentException();
		}
		int index = produto.size() + 1;
		System.out.println(index + ") - Voltar");
		scan.useDelimiter("\r\n");
		String pedidos = scan.next();
		if (pedidos.matches("\\s{0,}")) {
			return;
		}
		if (pedidos.matches("[0-9\\., ]*")) {
			pedidos = pedidos.replace(".", " ").replace(",", " ").replaceAll("\\s{2,}", " ").trim();
			String[] pedido = pedidos.split(" ");
			for (int i = 0; i < pedido.length; i++) {
				int validation = Integer.parseInt(pedido[i]);
				if (validation == index) {
					throw new IllegalArgumentException();
				}
				if (validation > produto.size()) {
					UniversalMethods.sleepMessage("Pedido Inválido, um ou mais digitos incorretos");
					throw new IllegalArgumentException();
				}
			}
			pedidoArrayList.add(pedidos);
		} else {
			UniversalMethods.sleepMessage("Digite Apenas numero");
			throw new IllegalArgumentException();
		}
	}

}