package methodsCliente;

import entity.*;
import repository.ClienteDatabaseMethods;
import java.util.ArrayList;

public class Business {

	public static void create(Cliente cadastro) {
		ClienteDatabaseMethods.create(cadastro);
	}

	public static ArrayList<Cliente> getAll() {
		return ClienteDatabaseMethods.getAll();
	}

	public static void update(Cliente cliente) {
		ClienteDatabaseMethods.update(cliente);
	}

	public static void delete(int id) {
		ClienteDatabaseMethods.delete(id);
	}
}