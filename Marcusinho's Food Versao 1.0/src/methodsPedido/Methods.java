package methodsPedido;

import universalMethods.UniversalMethods;
import entity.*;
import repository.CardapioDatabaseMethods;
import repository.ClienteDatabaseMethods;
import repository.PedidoDatabaseMethods;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Methods {
	private static Scanner scan = new Scanner(System.in);
	private static Cliente cliente = new Cliente();
	private static ArrayList<Integer> idPedido = new ArrayList<>();

	public static void findByName() {

		System.out.println("Digite o nome do cliente: ");
		String nome = scan.next();
		nome = nome.trim();
		UniversalMethods.limparConsole();
		findByName(nome);
	}

	public static void findByName(String nome) {
		try {
			ArrayList<Pedido> pedido = null;
			int indexPedido = 1;
			for (Pedido pedido2 : PedidoDatabaseMethods.separarPedidoNome(nome)) {
				int idCliente = PedidoDatabaseMethods.findByNamePedido(pedido2.getNome());
				pedido = CardapioDatabaseMethods.mostrarContaCliente(idCliente, pedido2.getIdPedido());
				pedidoByName(pedido, indexPedido);
				indexPedido++;
			}

			if (pedido == null) {
				UniversalMethods.sleepMessage("Informações Inválidas");
				return;
			}
		} catch (Exception e) {
			UniversalMethods.sleepMessage("Informações Inválidas");
			return;
		}
		
		UniversalMethods.aperteEnter();
	}
	
	public static void findByNameAndDate() {

		System.out.println("Digite o nome do cliente: ");
		String nome = scan.next();
		nome = nome.trim();
		System.out.println("Digite a data do pedido, desse modo: dia/mês/ano ");
		String date = methodsCliente.ModScanner.scanData();
		UniversalMethods.limparConsole();
		findByNameAndDate(nome, date);
	}
	
	public static void findByNameAndDate(String nome, String date) {
		try {
			ArrayList<Pedido> pedido = null;
			int indexPedido = 1;
			for (Pedido pedido2 : PedidoDatabaseMethods.separarPedidoNomeEData(nome, date)) {
				int idCliente = PedidoDatabaseMethods.findByNamePedido(pedido2.getNome());
				pedido = CardapioDatabaseMethods.mostrarContaCliente(idCliente, pedido2.getIdPedido());
				pedidoByName(pedido, indexPedido);
				indexPedido++;
			}

			if (pedido == null) {
				UniversalMethods.sleepMessage("Informações Inválidas");
				return;
			}
		} catch (Exception e) {
			UniversalMethods.sleepMessage("Informações Inválidas");
			return;
		}
		
		UniversalMethods.aperteEnter();
	}
	
	public static void pedir() {
		scan.useDelimiter("\r\n");
		int idade;
		print("Informe o ID do cliente: ");
		try {
			cliente.setIdCliente(UniversalMethods.scanId());
			idade = ClienteDatabaseMethods.getIdade(cliente.getIdCliente());
		} catch (IllegalArgumentException e) {
			UniversalMethods.sleepMessage("Este ID não existe");
			return;
		}

		while (true) {
			UniversalMethods.limparConsole();
			print("Bem-Vindo ao cardapio");
			print("1) Bebidas");
			print("2) Salgados");
			print("3) Quentinhas");
			print("4) Doces");
			print("5) Bedidas Alcoolicas");
			print("6) Ver Carrinho");
			print("7) Voltar");
			String escolha = scan.next();

			try {
				ArrayList<Produto> produto;
				switch (escolha) {

				case "1":
					UniversalMethods.limparConsole();
					produto = Cardapio.bebidas();
					methodsPedido.ModScanner.perguntarPedido(produto);
					methodsPedido.ModScanner.terminarPedido(1);
					break;

				case "2":
					UniversalMethods.limparConsole();
					produto = Cardapio.salgados();
					methodsPedido.ModScanner.perguntarPedido(produto);
					methodsPedido.ModScanner.terminarPedido(2);
					break;

				case "3":
					UniversalMethods.limparConsole();
					produto = Cardapio.quentinhas();
					methodsPedido.ModScanner.perguntarPedido(produto);
					methodsPedido.ModScanner.terminarPedido(3);
					break;

				case "4":
					UniversalMethods.limparConsole();
					produto = Cardapio.doces();
					methodsPedido.ModScanner.perguntarPedido(produto);
					methodsPedido.ModScanner.terminarPedido(4);
					break;

				case "5":
					UniversalMethods.limparConsole();
					if (idade < 18) {
						UniversalMethods.sleepMessage("Proibido a entrada de menores de 18 anos");
						break;
					}
					produto = Cardapio.bebidaAlcoolica();
					methodsPedido.ModScanner.perguntarPedido(produto);
					methodsPedido.ModScanner.terminarPedido(5);
					break;

				case "6":
					methodsPedido.ModScanner.carrinho(cliente.getIdCliente());
					break;

				case "7":
					methodsPedido.ModScanner.carrinhos.clear();
					return;

				default:
					UniversalMethods.limparConsole();
					UniversalMethods.sleepMessage("--- Escolha uma opção existente ---");
					break;
				}
			} catch (IllegalArgumentException e) {
			}
		}
	}

	public static void mostrarConta() {
		print("Digite o Id do Cliente que queira pegar a conta: ");
		try {
			cliente.setIdCliente(UniversalMethods.scanId());
			UniversalMethods.limparConsole();
			int indexPedido = 1;
			ArrayList<Pedido> pedido = null;
			for (int index : PedidoDatabaseMethods.separarPedido(cliente.getIdCliente())) {
				pedido = CardapioDatabaseMethods.mostrarContaCliente(cliente.getIdCliente(), index);
				pedido(pedido, indexPedido);
				indexPedido++;
			}

			if (pedido == null) {
				UniversalMethods.sleepMessage("Cliente sem Pedido");
				return;
			}

			UniversalMethods.aperteEnter();

		} catch (IllegalArgumentException e) {
			UniversalMethods.sleepMessage("Este ID não existe");
			return;
		} catch (Exception e) {
			UniversalMethods.sleepMessage("Cliente sem pedido!", 1500);
		}
	}

	public static void findByIdProduto() {
		print("Informe o ID do produto");
		Pedido pedido = new Pedido();
		try {
			pedido.setIdPedido(UniversalMethods.scanIdPedido());
			UniversalMethods.limparConsole();
			ArrayList<Pedido> pedidos = null;
			pedidos = CardapioDatabaseMethods.mostrarPedido(pedido.getIdPedido());
			pedido(pedidos);

		} catch (IllegalArgumentException e) {
			UniversalMethods.sleepMessage("Este ID não existe");
			return;
		} catch (Exception e) {
		}

		UniversalMethods.aperteEnter();
	}

	public static void updateStatus() {
		print("Informe o ID do cliente: ");
		try {
			cliente.setIdCliente(UniversalMethods.scanId());
			UniversalMethods.limparConsole();
			idPedido = PedidoDatabaseMethods.separarPedido(cliente.getIdCliente());
			if (idPedido.isEmpty()) {
				throw new Exception();
			}
			int i = 1;
			ArrayList<Pedido> pedido = null;
			for (int index : idPedido) {
				pedido = CardapioDatabaseMethods.mostrarContaCliente(cliente.getIdCliente(), index);
				pedido(pedido, i);
				i++;
			}
		} catch (IllegalArgumentException e) {
			UniversalMethods.sleepMessage("Este ID não existe");
			return;
		} catch (Exception e) {
			UniversalMethods.sleepMessage("Cliente sem pedido!", 1500);
			return;
		}

		print("Selecione o Pedido que deseja alterar o Status (Pelo o Número do Pedido): ");
		int opcaoPedido = UniversalMethods.scanInt();

		if (opcaoPedido > idPedido.size() || opcaoPedido == 0) {
			UniversalMethods.sleepMessage("Pedido Inválido", 1500);
			return;
		}

		System.out.println(idPedido.get(opcaoPedido - 1));

		while (true) {
			print("Informe o atual status do pedido: ");
			print("1 - Entregue");
			print("2 - Em Andamento");
			print("3 - Cancelado");
			print("4 - Enviando");
			String opcaoStatus = scan.next();

			switch (opcaoStatus) {
			case "1":
				CardapioDatabaseMethods.updateStatus(StatusPedido.ENTREGUE.getStatus(), idPedido.get(opcaoPedido - 1));
				return;

			case "2":
				CardapioDatabaseMethods.updateStatus(StatusPedido.EM_ANDAMENTO.getStatus(),
						idPedido.get(opcaoPedido - 1));
				return;

			case "3":
				CardapioDatabaseMethods.updateStatus(StatusPedido.CANCELADO.getStatus(), idPedido.get(opcaoPedido - 1));
				return;

			case "4":
				CardapioDatabaseMethods.updateStatus(StatusPedido.ENVIANDO.getStatus(), idPedido.get(opcaoPedido - 1));
				return;

			default:
				UniversalMethods.sleepMessage("--- Informe uma opção existente ---");

			}
			UniversalMethods.sleepMessage("Status atualizado com sucesso!");
		}
	}

	public static void deletePedido() {
		print("Informe o ID do cliente: ");
		try {
			cliente.setIdCliente(UniversalMethods.scanId());
			UniversalMethods.limparConsole();
			idPedido = PedidoDatabaseMethods.separarPedido(cliente.getIdCliente());
			if (idPedido.isEmpty()) {
				throw new Exception();
			}
			int i = 1;
			ArrayList<Pedido> pedido = null;
			for (int index : idPedido) {
				pedido = CardapioDatabaseMethods.mostrarContaCliente(cliente.getIdCliente(), index);
				pedido(pedido, i);
				i++;
			}
		} catch (IllegalArgumentException e) {
			UniversalMethods.sleepMessage("Este ID não existe");
			return;
		} catch (Exception e) {
			UniversalMethods.sleepMessage("Cliente sem pedido!", 1500);
			return;
		}

		print("Selecione o Pedido que deseja apagar (Pelo Número do Pedido): ");

		int opcaoPedido = UniversalMethods.scanInt();

		if (opcaoPedido > idPedido.size() || opcaoPedido == 0) {
			UniversalMethods.sleepMessage("Pedido Inválido");
			return;
		}

		print("Tem certeza que deseja excluir esse pedido;\r1-Sim \r2-Não");
		if (UniversalMethods.confirmacao()) {
			CardapioDatabaseMethods.delete(idPedido.get(opcaoPedido - 1));
			UniversalMethods.sleepMessage("Pedido deletado com sucesso!");
		}
	}

	private static void pedidoByName(ArrayList<Pedido> pedido, int index) throws Exception {

		System.out.print("Cliente: " + pedido.get(0).getNome());
		System.out.println("\tId do Cliente: " + pedido.get(0).getIdCliente());

		if (pedido.isEmpty()) {
			throw new Exception();
		}

		double valor = 0;
		System.out.println("Id do Pedido: " + pedido.get(0).getIdPedido());
		for (int i = 0; i < pedido.size(); i++) {
			System.out.print("\n" + pedido.get(i).getNomeProduto());
			System.out.printf(", \tValor: %.02f", pedido.get(i).getValor());
			System.out.print(", \tQuantidade: " + pedido.get(i).getQuantidade());
			valor += pedido.get(i).getValorTotal();
		}
		System.out.printf("\n\nValor Total: R$%.02f", valor);
		System.out.println("\nData: " + mostrarData(pedido.get(0).getData()));
		System.out.println("Status: " + pedido.get(0).getStatus());
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	}

	private static void pedido(ArrayList<Pedido> pedido, int index) throws Exception {

		if (pedido.isEmpty()) {
			throw new Exception();
		}

		if (index == 1) {
			System.out.print("Cliente: " + pedido.get(0).getNome());
			System.out.print("\tId do Cliente: " + pedido.get(0).getIdCliente() + "\n");
		}

		double valor = 0;
		System.out.println("\nPedido nº " + index + "...");
		System.out.println("Id do Pedido: " + pedido.get(0).getIdPedido());
		for (int i = 0; i < pedido.size(); i++) {
			System.out.print("\n" + pedido.get(i).getNomeProduto());
			System.out.printf(", \tValor: %.02f", pedido.get(i).getValor());
			System.out.print(", \tQuantidade: " + pedido.get(i).getQuantidade());
			valor += pedido.get(i).getValorTotal();
		}
		System.out.printf("\n\nValor Total: R$%.02f", valor);
		System.out.println("\nData: " + mostrarData(pedido.get(0).getData()));
		System.out.println("Status: " + pedido.get(0).getStatus());
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	}

	private static void pedido(ArrayList<Pedido> pedido) throws Exception {

		if (pedido.isEmpty()) {
			throw new Exception();
		}
		
		double valor = 0;
		System.out.println("Cliente: " + pedido.get(0).getNome());
		System.out.println("Id do Pedido: " + pedido.get(0).getIdPedido());
		for (int i = 0; i < pedido.size(); i++) {
			System.out.print("\n" + pedido.get(i).getNomeProduto());
			System.out.printf(", \tValor: %.02f", pedido.get(i).getValor());
			System.out.print(", \tQuantidade: " + pedido.get(i).getQuantidade());
			valor += pedido.get(i).getValorTotal();
		}
		System.out.printf("\n\nValor Total: R$%.02f", valor);
		System.out.println("\nData: " + mostrarData(pedido.get(0).getData()));
		System.out.println("Status: " + pedido.get(0).getStatus());
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	}

	public static String mostrarData(String stringDataSql) {
		SimpleDateFormat dataSql = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date data = null;
		try {
			data = dataSql.parse(stringDataSql);
		} catch (ParseException e) {
			e.printStackTrace();
		} // \n\t\t\t\t\t\t
		SimpleDateFormat formataData = new SimpleDateFormat("dd'/'MM'/'yyyy' -- Horário: 'HH:mm:ss");
		String dataFormatada = formataData.format(data);
		return dataFormatada;
	}

	public static void print(String message) {
		System.out.println(message);
	}

}