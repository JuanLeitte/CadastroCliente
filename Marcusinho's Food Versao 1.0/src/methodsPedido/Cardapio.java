package methodsPedido;

import java.util.ArrayList;

import entity.Produto;
import repository.CardapioDatabaseMethods;

public class Cardapio {

	public static ArrayList<Produto> bebidas() {
		ArrayList<Produto> bebidas = CardapioDatabaseMethods.getBebidas();
		int I = 1;
		for (Produto bebida : bebidas) {
			System.out.print(I + ") - " + bebida.getNome());
			System.out.print("\t\tPreço: R$");
			System.out.printf("%.02f", bebida.getPreco());
			System.out.println();
			I++;
		}
		return bebidas;
	}

	public static ArrayList<Produto> salgados() {
		ArrayList<Produto> salgados = CardapioDatabaseMethods.getSalgados();
		int I = 1;
		for (Produto salgado : salgados) {
			System.out.print(I + ") - " + salgado.getNome());
			System.out.print("\t\tPreço: R$");
			System.out.printf("%.02f", salgado.getPreco());
			System.out.println();
			I++;
		}
		return salgados;
	}

	public static ArrayList<Produto> bebidaAlcoolica() {
		ArrayList<Produto> bebidasAlcoolicas = CardapioDatabaseMethods.getBebidasAlcoolicas();
		int I = 1;
		for (Produto bebidaAlcoolica : bebidasAlcoolicas) {
			System.out.print(I + ") - " + bebidaAlcoolica.getNome());
			System.out.print("\t\tPreço: R$");
			System.out.printf("%.02f", bebidaAlcoolica.getPreco());
			System.out.println();
			I++;
		}
		return bebidasAlcoolicas;
	}

	public static ArrayList<Produto> quentinhas() {
		ArrayList<Produto> quentinhas = CardapioDatabaseMethods.getQuentinhas();
		int I = 1;
		for (Produto quentinha : quentinhas) {
			System.out.print(I + ") - " + quentinha.getNome());
			System.out.print("\t\tPreço: R$");
			System.out.printf("%.02f", quentinha.getPreco());
			System.out.println();
			I++;
		}
		return quentinhas;
	}

	public static ArrayList<Produto> doces() {
		ArrayList<Produto> doces = CardapioDatabaseMethods.getDoces();
		int I = 1;
		for (Produto doce : doces) {
			System.out.print(I + ") - " + doce.getNome());
			System.out.print("\t\tPreço: R$");
			System.out.printf("%.02f", doce.getPreco());
			System.out.println();
			I++;
		}
		return doces;
	}
}