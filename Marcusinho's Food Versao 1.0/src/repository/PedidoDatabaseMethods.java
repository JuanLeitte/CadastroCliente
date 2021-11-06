package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import entity.Cliente;
import entity.Pedido;

public class PedidoDatabaseMethods {
	private static RepositoryConnection connection = new RepositoryConnection();

	public static ArrayList<Cliente> findByName(String nome) {
		ResultSet resultSet = null;
		ArrayList<Cliente> clientes = new ArrayList<>();

		String ConsultarQuery = "SELECT ID_CLIENTE, NOME, TIMESTAMPDIFF (YEAR, DATA_NASCIMENTO, CURDATE()) AS IDADE, TELEFONE FROM CLIENTE WHERE NOME LIKE '%' ? '%'";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(ConsultarQuery);
			statement.setString(1, nome);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Cliente cliente = new Cliente();

				cliente.setIdCliente(resultSet.getInt("Id_Cliente"));
				cliente.setNome(resultSet.getString("Nome"));
				cliente.setdataNascimento(resultSet.getString("Idade"));
				cliente.setTelefone(resultSet.getString("Telefone"));

				clientes.add(cliente);
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "FindByIdMethod - " + erro.getMessage());
		}
		return clientes;
	}
	
	public static int findByNamePedido(String nome) {
		ResultSet resultSet = null;
		int idCliente = 0;

		String ConsultarQuery = "SELECT DISTINCT ID_CLIENTE FROM CLIENTE WHERE NOME = ?";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(ConsultarQuery);
			statement.setString(1, nome);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				
				idCliente = resultSet.getInt("Id_Cliente");
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "FindByIdMethod - " + erro.getMessage());
		}
		return idCliente;
	}

	public static ArrayList<Integer> separarPedido(int idCliente) {
		ResultSet resultSet = null;
		ArrayList<Integer> pedidos = new ArrayList<>();

		String ConsultarQuery = "SELECT DISTINCT Id_Fk_Pedido AS Id_Pedido FROM pedido_produto "
				+ "INNER JOIN PEDIDO ON PEDIDO.Id_Pedido = Pedido_Produto.Id_Fk_Pedido "
				+ "WHERE Id_Fk_Cliente = ?";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(ConsultarQuery);
			statement.setInt(1, idCliente);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				pedidos.add(resultSet.getInt("Id_Pedido"));
				
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "SepararPedidoMethod - " + erro.getMessage());
		}
		return pedidos;
	}
	
	public static ArrayList<Pedido> separarPedidoNome(String nomeCliente) {
		ResultSet resultSet = null;
		ArrayList<Pedido> pedidos = new ArrayList<>();

		String ConsultarQuery = "SELECT DISTINCT Id_Fk_Pedido AS Id_Pedido, Cliente.nome FROM PEDIDO_PRODUTO "
				+ "INNER JOIN PEDIDO ON PEDIDO.Id_Pedido = Pedido_Produto.Id_Fk_Pedido "
				+ "INNER JOIN Cliente ON Cliente.Id_Cliente = Pedido.Id_Fk_Cliente "
				+ "WHERE Cliente.nome LIKE '%' ? '%' ORDER BY Pedido.Id_Pedido ASC";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(ConsultarQuery);
			statement.setString(1, nomeCliente);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Pedido pedido = new Pedido();

				pedido.setNome(resultSet.getString("Nome"));
				pedido.setIdPedido(resultSet.getInt("Id_Pedido"));
				
				pedidos.add(pedido);
				
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "SepararPedidoMethod - " + erro.getMessage());
		}
		return pedidos;
	}
	
	public static ArrayList<Pedido> separarPedidoNomeEData(String nomeCliente, String date) {
		ResultSet resultSet = null;
		ArrayList<Pedido> pedidos = new ArrayList<>();
		String ConsultarQuery = "SELECT DISTINCT Id_Fk_Pedido AS Id_Pedido, Cliente.nome FROM PEDIDO_PRODUTO "
				+ "INNER JOIN PEDIDO ON PEDIDO.Id_Pedido = Pedido_Produto.Id_Fk_Pedido "
				+ "INNER JOIN Cliente ON Cliente.Id_Cliente = Pedido.Id_Fk_Cliente "
				+ "WHERE Cliente.nome LIKE '%' ? '%' AND Pedido.Data_Hora BETWEEN STR_TO_DATE(? '00:00:00', '%d/%m/%Y %H:%i:%S') AND STR_TO_DATE(? '23:59:59', '%d/%m/%Y %H:%i:%S')ORDER BY Pedido.Id_Pedido ASC";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(ConsultarQuery);
			statement.setString(1, nomeCliente);
			statement.setString(2, date);
			statement.setString(3, date);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Pedido pedido = new Pedido();

				pedido.setNome(resultSet.getString("Nome"));
				pedido.setIdPedido(resultSet.getInt("Id_Pedido"));
				
				pedidos.add(pedido);
				
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "SepararPedidoMethod - " + erro.getMessage());
		}
		return pedidos;
	}
	
	public static ArrayList<Pedido> getIdPedido() {
		ResultSet resultSet = null;
		ArrayList<Pedido> getIdBancoDados = new ArrayList<>();

		String query = "SELECT ID_PEDIDO FROM PEDIDO";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(query);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Pedido pedido = new Pedido();

				pedido.setIdPedido((resultSet.getInt("Id_Pedido")));

				getIdBancoDados.add(pedido);
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "GetAllMethod" + erro.getMessage());
		}
		return getIdBancoDados;
	}
	
}
