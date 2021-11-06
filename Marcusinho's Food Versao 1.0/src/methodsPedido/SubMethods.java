package methodsPedido;

import java.util.ArrayList;
import entity.Produto;
import repository.CardapioDatabaseMethods;

public class SubMethods {

	public static ArrayList<Produto> adicionarCardapio(int index) {

		ArrayList<Produto> arrayListCardapio = new ArrayList<>();
		switch (index) {
		case 1:
			arrayListCardapio = CardapioDatabaseMethods.getBebidas();
			break;
		case 2:
			arrayListCardapio = CardapioDatabaseMethods.getSalgados();
			break;
		case 3:
			arrayListCardapio = CardapioDatabaseMethods.getQuentinhas();
			break;
		case 4:
			arrayListCardapio = CardapioDatabaseMethods.getDoces();
			break;
		case 5:
			arrayListCardapio = CardapioDatabaseMethods.getBebidasAlcoolicas();
			break;
		}
		return arrayListCardapio;
	}
}