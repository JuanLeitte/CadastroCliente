package methodsPedido;

import java.util.ArrayList;

import entity.*;
import repository.PedidoDatabaseMethods;

public class Business {

	// public static void create(Pedido cadastro) {
	// PedidoDatabaseMethods.create(cadastro);
	// }

	public static ArrayList<Cliente> findByName(String nome) {
		return PedidoDatabaseMethods.findByName(nome);
	}

}
