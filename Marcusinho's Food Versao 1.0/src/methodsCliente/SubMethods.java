package methodsCliente;

import java.util.ArrayList;
import entity.Cliente;
import entity.Pedido;
import entity.Produto;
import repository.ClienteDatabaseMethods;
import repository.PedidoDatabaseMethods;
import repository.ProdutoDatabaseMethods;

public class SubMethods {
	
	public static StringBuilder mostrarCPF(String cpf) {
		StringBuilder sb = new StringBuilder();
		sb.append(cpf);
		sb.insert(3, ".").insert(7, ".").insert(11, "-");
		return sb;
	}

	public static StringBuilder mostrarTelefone(String telefone) {
		StringBuilder sb = new StringBuilder();
		sb.append(telefone);
		sb.insert(0, "(").insert(3, ") ").insert(10, "-");
		return sb;
	}

	public static void verificarIdCliente(int Int) throws IllegalArgumentException {
		ArrayList<Cliente> cliente = ClienteDatabaseMethods.getId();

		for (int i = 0; i < cliente.size(); i++) {
			int intCliente = cliente.get(i).getIdCliente();
			if (Int == intCliente) {
				return;
			}
		}
		throw new IllegalArgumentException();
	}
	
	public static void verificarIdPedido(int Int) throws IllegalArgumentException {
		ArrayList<Pedido> pedido = PedidoDatabaseMethods.getIdPedido();

		for (int i = 0; i < pedido.size(); i++) {
			int intPedido = pedido.get(i).getIdPedido();
			if (Int == intPedido) {
				return;
			}
		}
		throw new IllegalArgumentException();
	}
	
	public static void verificarIdProduto(int Int) throws IllegalArgumentException {
		ArrayList<Produto> produto = ProdutoDatabaseMethods.getId();

		for (int i = 0; i < produto.size(); i++) {
			int intProduto = produto.get(i).getIdProduto();
			if (Int == intProduto) {
				return;
			}
		}
		throw new IllegalArgumentException();
	}
	
}