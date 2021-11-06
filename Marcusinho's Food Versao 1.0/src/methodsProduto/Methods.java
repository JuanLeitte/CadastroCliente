package methodsProduto;

import java.util.ArrayList;
import java.util.Scanner;
import entity.Produto;
import universalMethods.UniversalMethods;
import repository.ProdutoDatabaseMethods;

public class Methods {
	private static Scanner scan = new Scanner(System.in);
	private static Produto produto = new Produto();
	private static String linha = "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";

	public static void create() {
		try {
			encapsulamento();
			methodsProduto.Business.create(produto);
			UniversalMethods.sleepMessage("Cadastrado com Sucesso :D");
		} catch (Exception e) {
		}
	}

	public static void findByName() {
		scan.useDelimiter("\r\n");
		print("Digite o nome do produto: ");
		String nome = scan.next();
		UniversalMethods.limparConsole();
		findByName(nome);
	}

	public static void findByName(String nome) {
		ArrayList<Produto> produto = ProdutoDatabaseMethods.findByName(nome);

		for (int i = 0; i < produto.size(); i++) {
			System.out.println("IdProduto: " + produto.get(i).getIdProduto());
			System.out.println("Nome: " + produto.get(i).getNome());
			System.out.println("Descrição: " + produto.get(i).getDescricao());
			System.out.printf("Preço: %.02f", produto.get(i).getPreco());
			System.out.println("\nClassificação: " + produto.get(i).getClassificacao());
			System.out.println(linha);
		}

		if (produto.isEmpty()) {
			UniversalMethods.sleepMessage("Nome Inválido");
			return;
		}

		UniversalMethods.aperteEnter();
	}

	public static void read() {
		ArrayList<Produto> produto = methodsProduto.Business.getAll();

		if (produto.isEmpty()) {
			UniversalMethods.sleepMessage("Nenhum produto cadastrado");
			return;
		}

		print("=-=-=-=-=-=-PRODUTOS CADASTRADOS-=-=-=-=-=-=");
		for (int i = 0; i < produto.size(); i++) {
			print("ID: " + produto.get(i).getIdProduto());
			print("Nome: " + produto.get(i).getNome());
			print("Descrição: " + produto.get(i).getDescricao());
			System.out.printf("Preço: %.02f", produto.get(i).getPreco());
			print("\nClassificação: " + produto.get(i).getClassificacao());
			print("Disponibilidade: " + produto.get(i).getDisponibilidade());
			print(linha);
		}

		UniversalMethods.aperteEnter();
	}

	public static void update() {
		print("Informe o ID do produto: ");
		try {
			produto.setIdProduto(UniversalMethods.scanIdProduto());
			if (produto.getIdProduto() == 0) {
				UniversalMethods.sleepMessage("Este ID não existe");
				return;
			}
			UniversalMethods.limparConsole();
			encapsulamento();
			methodsProduto.Business.update(produto);
			UniversalMethods.sleepMessage("Produto atualizado com sucesso!");
		} catch (IllegalArgumentException e) {
		}
	}

	public static void deleteProduto() {
		int id = 0;
		print("Informe o Id do produto que deseja deletar: ");
		try {
			id = UniversalMethods.scanIdProduto();
		} catch (IllegalArgumentException e) {
			UniversalMethods.sleepMessage("Este ID não existe");
			return;
		}
		UniversalMethods.limparConsole();
		
		try {
			findById(id);
			System.out.println("Tem certeza que dejesa deletar esse produto? \n1 - Sim\n2 - Não");
			if (UniversalMethods.confirmacao()) {
				methodsProduto.Business.delete(id);
				UniversalMethods.sleepMessage("Produto deletado com seucesso!");
			}
		} catch (Exception e) {
			UniversalMethods.sleepMessage("Erro! Produto não pode ser excluido");
		}
	}

	public static void findByIdProduto() {
		try {
			System.out.println("Digite o id do produto: ");
			int id = UniversalMethods.scanIdProduto();
			UniversalMethods.limparConsole();
			findById(id);
			UniversalMethods.aperteEnter();
		} catch (IllegalArgumentException e) {
			UniversalMethods.sleepMessage("Este id não existe");
		}
	}

	public static void findById(int idProduto) {
		ArrayList<Produto> produto = ProdutoDatabaseMethods.findById(idProduto);

		if (produto.isEmpty()) {
			UniversalMethods.sleepMessage("Nenhum produto cadastrado");
			return;
		}

		for (int i = 0; i < produto.size(); i++) {
			print("ID: " + produto.get(i).getIdProduto());
			print("Nome: " + produto.get(i).getNome());
			print("Descrição: " + produto.get(i).getDescricao());
			System.out.printf("Preço: %.02f", produto.get(i).getPreco());
			print("\nClassificação: " + produto.get(i).getClassificacao());
			print("Disponibilidade: " + produto.get(i).getDisponibilidade());
			print(linha);
		}

	}

	public static void updateStatusProduto() {
		int id = 0;
		print("Informe o ID do produto que deseja alterar: ");
		try {
			id = UniversalMethods.scanIdProduto();
		} catch (IllegalArgumentException e) {
			UniversalMethods.sleepMessage("Este ID não existe");
			return;
		}
		print("1) Disponivel");
		print("2) Indisponivel");
		methodsProduto.Business.updateStatus(methodsProduto.SubMethods.confirmacao(), id);
		UniversalMethods.sleepMessage("Produto alterado com seucesso!");
	}

	/*
	 * db4free.net marcuzinhofood bXZLo6@mB3dRqQ$L
	 */

	private static void encapsulamento() throws IllegalArgumentException {
		scan.useDelimiter("\r\n");
		print("Digite o nome do produto: ");
		produto.setNome(scan.next());
		if (produto.getNome().equals(methodsCliente.ModScanner.volta)) {
			throw new IllegalArgumentException();
		}

		print("Digite o descrição do produto: ");
		produto.setDescricao(scan.next());
		if (produto.getDescricao().equals(methodsCliente.ModScanner.volta)) {
			throw new IllegalArgumentException();
		}

//		print("Digite o Codigo do produto; ");
//		produto.setCodigo(UniversalMethods.scanInt());
//
//		print("Digite a quantidade de produtos em estoque: ");
//		produto.setQuantidade(UniversalMethods.scanInt());

		// Por enquanto a gente nao esta usando estoque e nem o codigo do produto, pois
		// o id do produto substitui o codigo

		print("Digite o preço do produto: ");
		produto.setPreco(UniversalMethods.scanDouble());
		if (produto.getPreco() == 0) {
			throw new IllegalArgumentException();
		}

		classficacaoProduto();
		disponibilidadeProduto();

		// print ("Digite a classificação deste produto: ");
		// print ("1- Bebida \r 2- Salgado \r 3- Bebidas Alcoolicas")

	}

	private static void classficacaoProduto() {
		while (true) {
			print("Digite a classificação do produto: ");
			print("1) Bebida");
			print("2) Salgado");
			print("3) Bebida Alcoolica");
			print("4) Quentinha");
			print("5) Lanche");
			print("6) Doce");

			String classificacao = scan.next();

			switch (classificacao) {
			case "0":
				throw new IllegalArgumentException();

			case "1":
				produto.setClassificacao("Bebida");
				return;

			case "2":
				produto.setClassificacao("Salgado");
				return;

			case "3":
				produto.setClassificacao("Bebida Alcoolica");
				return;

			case "4":
				produto.setClassificacao("Quentinha");
				return;

			case "5":
				produto.setClassificacao("Lanche");
				return;

			case "6":
				produto.setClassificacao("Doce");
				return;

			default:
				System.out.println("Opção Inválida");
				break;
			}
		}
	}

	private static void disponibilidadeProduto() {
		while (true) {
			print("Digite a classificação do produto: ");
			print("1) Disponivel");
			print("2) Indisponivel");

			String classificacao = scan.next();

			switch (classificacao) {
			case "0":
				throw new IllegalArgumentException();

			case "1":
				produto.setDisponibilidade("Disponivel");
				return;

			case "2":
				produto.setDisponibilidade("Indisponivel");
				return;

			default:
				System.out.println("Opção Inválida");
				break;
			}
		}
	}

	private static void print(String message) {
		System.out.println(message);
	}
}