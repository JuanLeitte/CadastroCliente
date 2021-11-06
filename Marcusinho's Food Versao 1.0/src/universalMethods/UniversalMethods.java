package universalMethods;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import entity.Cliente;
import entity.Pedido;
import methodsCliente.SubMethods;
import methodsPedido.Business;
	
public class UniversalMethods {
	private static Scanner scan = new Scanner(System.in);
	
	public static int scanInt() {
		int Int;

		while (true) {
			try {
				Int = scan.nextInt();
				return Int;
			} catch (InputMismatchException e) {
				System.out.println("Digite Apenas Números.");
				scan.next();
			}
		}
	}
	
	public static boolean confirmacao() {
		while (true) {
			String escolha = scan.next();
			switch (escolha) {
			case "1)":
			case "1-":
			case "1":
				return true;
			case "2)":
			case "2-":
			case "2":
				return false;
			default:
				System.out.println("Escolha uma opção valida");
			}
		}
	}

	public static double scanDouble() {
		double Double;

		while (true) {
			try {
				Double = scan.nextDouble();
				return Double;
			} catch (InputMismatchException e) {
				System.out.println("Digite Apenas Números (Pode ser Decimal).");
				scan.next();
			}
		}
	}
	
	public static int scanId() throws IllegalArgumentException {
		
		int Int = 0;
		Int = UniversalMethods.scanInt();
		SubMethods.verificarIdCliente(Int);
		return Int;	
		}
	
public static int scanIdPedido() throws IllegalArgumentException {
		
		int Int = 0;
		Int = UniversalMethods.scanInt();
		SubMethods.verificarIdPedido(Int);
		return Int;	
		}
	
	public static int scanIdProduto() throws IllegalArgumentException {
		
		int Int = 0;
		Int = UniversalMethods.scanInt();
		SubMethods.verificarIdProduto(Int);
		return Int;	
		}
		
		public static void findByName(String nome) {
			ArrayList<Cliente> cliente = Business.findByName(nome);
			System.out.println();
			System.out.println("=-=-=-=-=-=-CLIENTE CADASTRADOS-=-=-=-=-=-=");
			for (int i = 0; i < cliente.size(); i++) {

				System.out.println("Id: " + cliente.get(i).getIdCliente());
				System.out.println("Nome: " + cliente.get(i).getNome());
				System.out.println("Idade: " + cliente.get(i).getdataNascimento());
				System.out.println("Telefone: " + SubMethods.mostrarTelefone(cliente.get(i).getTelefone()));
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
			}
			
			if (cliente.isEmpty()) {
				UniversalMethods.sleepMessage("Nome Inválido");
			}
			
			UniversalMethods.aperteEnter();
		}
	
	public static int validarOpcao(ArrayList<Pedido> pedido, int opcaoPedido) {
		
		for (int i = 0; i < pedido.size(); i++) {
			int idPedido = pedido.get(i).getIdPedido();
			if(opcaoPedido == idPedido) {
				return opcaoPedido;
			}
		}
		return 0;
	}
	
	public static void aperteEnter() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nAperte Enter Para Continuar...");
		scanner.nextLine();
	}
	
	public static void limparConsole(){
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}catch(Exception e){
		}
	}
	
	public static void exit(){
		try {
			new ProcessBuilder("cmd", "/c", "exit").inheritIO().start().waitFor();
		}catch(Exception e){
		}
	}
	
	public static void sleepMessage(String message) {
		try {
			System.out.println(message);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}
	
	public static void sleepMessage(String message, int tempo) {
		try {
			System.out.println(message);
			Thread.sleep(tempo);
		} catch (InterruptedException e) {
		}
	}
}
