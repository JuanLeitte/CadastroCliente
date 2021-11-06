package methodsProduto;

import java.util.ArrayList;
import entity.Produto;
import repository.ProdutoDatabaseMethods;

public class Business {
	public static void create(Produto produto) {
		ProdutoDatabaseMethods.create(produto);
	}

	public static ArrayList<Produto> getAll() {
		return ProdutoDatabaseMethods.getAll();
	}

	public static void updateStatus(String string, int id) {
		ProdutoDatabaseMethods.atualizarDisponibilidade(string, id);
	}

	public static void update(Produto atualizado) {
		ProdutoDatabaseMethods.update(atualizado);
	}
	
	public static void delete(int cadastro) throws Exception{
		ProdutoDatabaseMethods.delete(cadastro);
	}
}