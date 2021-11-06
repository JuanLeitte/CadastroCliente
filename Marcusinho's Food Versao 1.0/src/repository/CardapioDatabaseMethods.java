package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;
import entity.*;

public class CardapioDatabaseMethods {

	private static RepositoryConnection connection = new RepositoryConnection();

	public static ArrayList<Produto> getBebidas() {
		ResultSet resultSet = null;

		ArrayList<Produto> bebidas = new ArrayList<>();

		String ConsultarQuery = "SELECT * FROM PRODUTO WHERE CLASSIFICACAO = 'Bebida' AND DISPONIBILIDADE = 'Disponivel'";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(ConsultarQuery);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Produto produto = new Produto();

				produto.setIdProduto((resultSet.getInt("Id_Produto")));
				produto.setNome((resultSet.getString("Nome")));
				produto.setDescricao((resultSet.getString("Descricao")));
				produto.setPreco((resultSet.getDouble("Valor_Produto")));
//				produto.setQuantidade((resultSet.getInt("quantidade")));
//				produto.setCodigo((resultSet.getInt("codigo")));
				produto.setClassificacao((resultSet.getString("Classificacao")));

				bebidas.add(produto);
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "GetAllMethodCardapioBebida - " + erro.getMessage());
		}
		return bebidas;
	}

	public static ArrayList<Produto> getBebidasAlcoolicas() {
		ResultSet resultSet = null;

		ArrayList<Produto> bebidasAlcoolicas = new ArrayList<>();

		String ConsultarQuery = "SELECT * FROM PRODUTO WHERE CLASSIFICACAO = 'Bebida Alcoolica' AND DISPONIBILIDADE = 'Disponivel'";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(ConsultarQuery);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Produto produto = new Produto();

				produto.setIdProduto((resultSet.getInt("Id_Produto")));
				produto.setNome((resultSet.getString("Nome")));
				produto.setDescricao((resultSet.getString("Descricao")));
				produto.setPreco((resultSet.getDouble("Valor_Produto")));
//				produto.setQuantidade((resultSet.getInt("quantidade")));
//				produto.setCodigo((resultSet.getInt("codigo")));
				produto.setClassificacao((resultSet.getString("Classificacao")));

				bebidasAlcoolicas.add(produto);
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "GetAllMethodCardapioBebidaAlcoolica - " + erro.getMessage());
		}
		return bebidasAlcoolicas;
	}

	public static ArrayList<Produto> getSalgados() {
		ResultSet resultSet = null;

		ArrayList<Produto> salgados = new ArrayList<>();

		String ConsultarQuery = "SELECT * FROM PRODUTO WHERE CLASSIFICACAO = 'Salgado' AND DISPONIBILIDADE = 'Disponivel'";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(ConsultarQuery);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Produto produto = new Produto();

				produto.setIdProduto((resultSet.getInt("Id_Produto")));
				produto.setNome((resultSet.getString("Nome")));
				produto.setDescricao((resultSet.getString("Descricao")));
				produto.setPreco((resultSet.getDouble("Valor_Produto")));
//				produto.setQuantidade((resultSet.getInt("quantidade")));
//				produto.setCodigo((resultSet.getInt("codigo")));
				produto.setClassificacao((resultSet.getString("Classificacao")));

				salgados.add(produto);
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "GetAllMethodCardapioSalgado - " + erro.getMessage());
		}
		return salgados;
	}

	public static int pedirProduto(int idCliente, String recebedor) {
		ResultSet resultSet = null;
		String ConsultarQuery = "INSERT INTO PEDIDO (Id_Pedido, Recebedor, Data_Hora, Id_Fk_Cliente, Status_Pedido) VALUES (NULL, ?, ?, ?, ?)";
		SimpleDateFormat dataFormatada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar data = Calendar.getInstance();
		int idPedido = 0;
		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(ConsultarQuery);
			statement.setString(1, recebedor);
			statement.setString(2, dataFormatada.format(data.getTime()));
			statement.setInt(3, idCliente);
			statement.setString(4, "Em Andamento");
			statement.execute();
			ConsultarQuery = "SELECT LAST_INSERT_ID() AS Id_Pedido";
			statement = conn.prepareStatement(ConsultarQuery);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {

				idPedido = resultSet.getInt("Id_Pedido");
			}

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "GetAllMethodCriarPedido - " + erro.getMessage());
		}
		return idPedido;
	}

	public static void pedir(int idPedido, int idProduto, int quantidade) {
		String ConsultarQuery = "INSERT INTO PEDIDO_PRODUTO (Id_Pedido_Produto, Id_Fk_Produto, Id_Fk_Pedido, Quantidade_Produto) VALUES (NULL, ?, ?, ?)";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(ConsultarQuery);

			statement.setInt(1, idProduto);
			statement.setInt(2, idPedido);
			statement.setInt(3, quantidade);

			statement.execute();
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "GetAllMethodCriarPedido - " + erro.getMessage());
		}
	}

	public static ArrayList<Pedido> mostrarContaCliente(int idCliente, int idPedido) {
		ResultSet resultSet = null;
		ArrayList<Pedido> pedidos = new ArrayList<>();

		String ConsultarQuery = "SELECT c.Id_Cliente, c.Nome, p.Id_Pedido, p.Recebedor, po.Nome AS Nome_Produto, po.Valor_Produto, pp.Quantidade_Produto AS Quantidade, po.Valor_Produto*pp.Quantidade_Produto AS Valor_Total, p.Data_Hora, p.Status_Pedido FROM PEDIDO p "
				+ "INNER JOIN CLIENTE c ON p.Id_Fk_Cliente = c.Id_Cliente "
				+ "INNER JOIN PEDIDO_PRODUTO pp ON p.Id_Pedido = pp.Id_Fk_Pedido "
				+ "INNER JOIN PRODUTO po ON po.Id_Produto = pp.Id_Fk_Produto "
				+ "WHERE c.Id_Cliente = ? AND p.Id_Pedido = ? ORDER BY p.Id_Pedido ASC";
		
		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(ConsultarQuery);
			statement.setInt(1, idCliente);
			statement.setInt(2, idPedido);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Pedido pedido = new Pedido();
				
				pedido.setIdCliente(resultSet.getInt("id_Cliente"));
				pedido.setIdPedido(resultSet.getInt("id_Pedido"));
				pedido.setNome(resultSet.getString("Nome"));
				pedido.setNomeProduto(resultSet.getString("Nome_Produto"));
				pedido.setRecebedor(resultSet.getString("Recebedor"));
				pedido.setValor(resultSet.getDouble("Valor_Produto"));
				pedido.setValorTotal(resultSet.getDouble("Valor_Total"));
				pedido.setQuantidade(resultSet.getInt("Quantidade"));
				pedido.setData(resultSet.getString("Data_Hora"));
				pedido.setStatus(resultSet.getString("Status_Pedido"));

				pedidos.add(pedido);
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "GetAllMethodCardapioSalgado - " + erro.getMessage());
		}
		return pedidos;
	}
	
	public static ArrayList<Pedido> mostrarPedido(int idPedido) {
		ResultSet resultSet = null;
		ArrayList<Pedido> pedidos = new ArrayList<>();

		String ConsultarQuery = "SELECT c.Id_Cliente, c.Nome, p.Id_Pedido, p.Recebedor, po.Nome AS Nome_Produto, po.Valor_Produto, pp.Quantidade_Produto AS Quantidade, po.Valor_Produto*pp.Quantidade_Produto AS Valor_Total, p.Data_Hora, p.Status_Pedido FROM PEDIDO p "
				+ "INNER JOIN CLIENTE c ON p.Id_Fk_Cliente = c.Id_Cliente "
				+ "INNER JOIN PEDIDO_PRODUTO pp ON p.Id_Pedido = pp.Id_Fk_Pedido "
				+ "INNER JOIN PRODUTO po ON po.Id_Produto = pp.Id_Fk_Produto "
				+ "WHERE p.Id_Pedido = ? ORDER BY p.Id_Pedido ASC";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(ConsultarQuery);
			statement.setInt(1, idPedido);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Pedido pedido = new Pedido();
				
				pedido.setIdCliente(resultSet.getInt("id_Cliente"));
				pedido.setIdPedido(resultSet.getInt("id_Pedido"));
				pedido.setNome(resultSet.getString("Nome"));
				pedido.setNomeProduto(resultSet.getString("Nome_Produto"));
				pedido.setRecebedor(resultSet.getString("Recebedor"));
				pedido.setValor(resultSet.getDouble("Valor_Produto"));
				pedido.setValorTotal(resultSet.getDouble("Valor_Total"));
				pedido.setQuantidade(resultSet.getInt("Quantidade"));
				pedido.setData(resultSet.getString("Data_Hora"));
				pedido.setStatus(resultSet.getString("Status_Pedido"));

				pedidos.add(pedido);
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "GetAllMethodCardapioSalgado - " + erro.getMessage());
		}
		return pedidos;
	}
	
	public static ArrayList<Produto> getDoces() {
		ResultSet resultSet = null;
		ArrayList<Produto> doces = new ArrayList<>();

		String ConsultarQuery = "SELECT * FROM PRODUTO WHERE CLASSIFICACAO = 'Doce' AND DISPONIBILIDADE = 'Disponivel'";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(ConsultarQuery);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Produto produto = new Produto();

				produto.setIdProduto((resultSet.getInt("Id_Produto")));
				produto.setNome((resultSet.getString("Nome")));
				produto.setDescricao((resultSet.getString("Descricao")));
				produto.setPreco((resultSet.getDouble("Valor_Produto")));
//				produto.setQuantidade((resultSet.getInt("quantidade")));
//				produto.setCodigo((resultSet.getInt("codigo")));
				produto.setClassificacao((resultSet.getString("Classificacao")));

				doces.add(produto);
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "GetAllMethodCardapioDoces - " + erro.getMessage());
		}
		return doces;
	}

	public static ArrayList<Produto> getQuentinhas() {
		ResultSet resultSet = null;
		ArrayList<Produto> quentinhas = new ArrayList<>();

		String ConsultarQuery = "SELECT * FROM PRODUTO WHERE CLASSIFICACAO = 'Quentinha' AND DISPONIBILIDADE = 'Disponivel'";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(ConsultarQuery);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Produto produto = new Produto();

				produto.setIdProduto((resultSet.getInt("Id_Produto")));
				produto.setNome((resultSet.getString("Nome")));
				produto.setDescricao((resultSet.getString("Descricao")));
				produto.setPreco((resultSet.getDouble("Valor_Produto")));
//				produto.setQuantidade((resultSet.getInt("quantidade")));
//				produto.setCodigo((resultSet.getInt("codigo")));
				produto.setClassificacao((resultSet.getString("Classificacao")));

				quentinhas.add(produto);
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "GetAllMethodCardapioQuentinhas - " + erro.getMessage());
		}
		return quentinhas;
	}

	public static void updateStatus(String status, int idPedido) {
		try {

			String QueryUpdate = "UPDATE PEDIDO SET STATUS_PEDIDO = ? WHERE Id_Pedido = ?";

			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(QueryUpdate);
			statement.setString(1, status);
			statement.setInt(2, idPedido);

			statement.execute();
			statement.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "UpdateMethod" + erro.getMessage());
		}
	}

	public static void delete(int idPedido) {
		try {
			Connection conn = connection.connect();

			String DeleteQueryCliente = "DELETE FROM PEDIDO_PRODUTO WHERE Id_Fk_Pedido = ?; DELETE FROM PEDIDO WHERE ID_PEDIDO = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(DeleteQueryCliente);
			preparedStatement.setInt(1, idPedido);
			preparedStatement.setInt(2, idPedido);
			preparedStatement.execute();
			preparedStatement.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "DeleteMethod - " + erro.getMessage());
		}
	}

}