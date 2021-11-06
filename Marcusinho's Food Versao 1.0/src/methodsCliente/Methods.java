package methodsCliente;

import entity.*;
import repository.ClienteDatabaseMethods;
import universalMethods.UniversalMethods;
import java.util.ArrayList;
import java.util.Scanner;

public class Methods {
	private static Scanner scan = new Scanner(System.in);
	private static Cliente cliente = new Cliente();
	private static Endereco endereco = new Endereco();
	private static ModScanner scanner = new ModScanner();
	private static String linha = "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";

	public static void create() {
		try {
			scan.useDelimiter("\r\n");
			encapsulamento();
			Business.create(cliente);
			UniversalMethods.sleepMessage("Cliente cadastrado com Sucesso :D");
		} catch (Exception e) {
		}
	}

	public static void read() {
		ArrayList<Cliente> clientes = Business.getAll();

		if (clientes.isEmpty()) {
			System.out.println("Nenhum cliente cadastrado");
			return;
		}

		print("=-=-=-=-=-=-CLIENTES CADASTRADOS-=-=-=-=-=-=");

		for (int i = 0; i < clientes.size(); i++) {
			print("Id: " + clientes.get(i).getIdCliente());
			print("Nome: " + clientes.get(i).getNome());
			print("CPF: " + SubMethods.mostrarCPF(clientes.get(i).getCpf()));
			print("Idade: " + clientes.get(i).getdataNascimento());
			print("Telefone: " + SubMethods.mostrarTelefone(clientes.get(i).getTelefone()));
			print("CEP: " + clientes.get(i).getEndereco().getCep());
			print("Bairro: " + clientes.get(i).getEndereco().getBairro());
			print("Logradouro: " + clientes.get(i).getEndereco().getLogradouro());
			print("Complemento: " + clientes.get(i).getEndereco().getComplemento());
			print("Numero: " + clientes.get(i).getEndereco().getNumero());
			print(linha);
		}

		UniversalMethods.aperteEnter();
	}

	public static void findById() {
		int id = 0;
		print("Informe o ID do cliente: ");
		UniversalMethods.limparConsole();
		try {
			id = UniversalMethods.scanId();
		} catch (IllegalArgumentException e) {
			UniversalMethods.sleepMessage("Este ID não existe");
			return;
		}

		Cliente clientes = ClienteDatabaseMethods.getById(id);

		print("=-=-=-=-=-=-CLIENTE CADASTRADO-=-=-=-=-=-=");

		print("Id: " + clientes.getIdCliente());
		print("Nome: " + clientes.getNome());
		print("CPF: " + SubMethods.mostrarCPF(clientes.getCpf()));
		print("Idade: " + clientes.getdataNascimento());
		print("Telefone: " + SubMethods.mostrarTelefone(clientes.getTelefone()));
		print("CEP: " + clientes.getEndereco().getCep());
		print("Bairro: " + clientes.getEndereco().getBairro());
		print("Logradouro: " + clientes.getEndereco().getLogradouro());
		print("Complemento: " + clientes.getEndereco().getComplemento());
		print("Numero: " + clientes.getEndereco().getNumero());
		print(linha);

		UniversalMethods.aperteEnter();
	}

	public static void findByName() {
		print("Digite o nome do cliente: ");
		String nome = scan.next();
		UniversalMethods.limparConsole();
		UniversalMethods.findByName(nome);
	}

	public static void delete() {
		int id = 0;
		print("Informe o ID do cliente que deseja deletar: ");
		try {
			id = UniversalMethods.scanId();
		} catch (IllegalArgumentException e) {
			UniversalMethods.sleepMessage("Este ID não existe");
			return;
		}

		print("Tem certeza que deseja excluir esse cliente\n1-Sim \n2-Não");
		if (UniversalMethods.confirmacao()) {
			Business.delete(id);
			UniversalMethods.sleepMessage("Cliente deletado com sucesso!");
		}
	}

	public static void update() {
		print("Informe o ID do cliente: ");
		try {
			cliente.setIdCliente(UniversalMethods.scanId());
			try {
				UniversalMethods.limparConsole();
				encapsulamento();
			} catch (IllegalArgumentException e) {
				return;
			}
			Business.update(cliente);
			UniversalMethods.sleepMessage("Cliente atualizado com sucesso!");
		} catch (IllegalArgumentException e) {
			UniversalMethods.sleepMessage("Este ID não existe");
		}
	}

//////////////////////////Encapsulamento	

	private static void encapsulamento() throws IllegalArgumentException {

		print("Digite o nome do cliente, desse modo: Augusto Reis");
		cliente.setNome(scanner.scanNome());

		print("Digite o cpf do cliente: ");
		cliente.setCpf(scanner.scanCpf());

		print("Digite a data de nascimento desse modo: dia/mês/ano ");
		cliente.setdataNascimento(ModScanner.scanData());

		print("Digite o número de telefone com DDD: ");
		cliente.setTelefone(scanner.scanTelefone());

////////////////////////////////////////////////////////////////////////////Endereço

		print("Digite o Cep atual: ");
		endereco.setCep(scanner.scanCep());

		print("Digite o bairro atual: ");
		endereco.setBairro(scanner.scanBairro());

		print("Digite o logradouro atual, deste modo: Rua Monji; Avenida Pinheiro; ");
		endereco.setLogradouro(scanner.scanLogradouro());

		print("Digite o complemento, Por exemplo: Acima do Marcusinho's food;");
		endereco.setComplemento(scan.next());

		print("Digite o número de sua casa atual: ");
		endereco.setNumero(scanner.scanNumero());

		cliente.setEndereco(endereco);
	}

	private static void print(String message) {
		System.out.println(message);
	}
}
