package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import entity.Cliente;
import entity.Endereco;

public class ClienteDatabaseMethods {
	private static RepositoryConnection connection = new RepositoryConnection();

	public static void create(Cliente cliente) {
		try {
			String QueryCliente = "INSERT INTO ENDERECO (ID_ENDERECO, CEP, BAIRRO, LOGRADOURO, COMPLEMENTO, NUMERO) VALUES (NULL, ?,?,?,?,?); INSERT INTO CLIENTE(ID_CLIENTE, NOME, CPF, DATA_NASCIMENTO, TELEFONE, Id_Fk_Endereco) VALUES (NULL, ?, ?, STR_TO_DATE(?, '%d/%m/%Y'), ?, @@identity)";
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(QueryCliente);

			statement.setString(1, cliente.getEndereco().getCep());
			statement.setString(2, cliente.getEndereco().getBairro());
			statement.setString(3, cliente.getEndereco().getLogradouro());
			statement.setString(4, cliente.getEndereco().getComplemento());
			statement.setString(5, cliente.getEndereco().getNumero());
			statement.setString(6, cliente.getNome());
			statement.setString(7, cliente.getCpf());
			statement.setString(8, cliente.getdataNascimento());
			statement.setString(9, cliente.getTelefone());

			statement.execute();
			statement.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "CreateMethod" + erro.getMessage());
		}
	}

	public static void update(Cliente cliente) {
		try {

			String QueryUpdate = "UPDATE CLIENTE SET NOME = ?, CPF = ?, DATA_NASCIMENTO = STR_TO_DATE(?, '%d/%m/%Y'), TELEFONE = ? WHERE ID_CLIENTE = ?; UPDATE ENDERECO SET CEP = ?, BAIRRO = ?, LOGRADOURO = ?, COMPLEMENTO = ?, NUMERO = ? WHERE ID_ENDERECO = ?";

			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(QueryUpdate);
			statement.setString(1, cliente.getNome());
			statement.setString(2, cliente.getCpf());
			statement.setString(3, cliente.getdataNascimento());
			statement.setString(4, cliente.getTelefone());
			statement.setInt(5, cliente.getIdCliente());
			statement.setString(6, cliente.getEndereco().getCep());
			statement.setString(7, cliente.getEndereco().getBairro());
			statement.setString(8, cliente.getEndereco().getLogradouro());
			statement.setString(9, cliente.getEndereco().getComplemento());
			statement.setString(10, cliente.getEndereco().getNumero());
			statement.setInt(11, cliente.getIdCliente());

			statement.execute();
			statement.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "UpdateMethod" + erro.getMessage());
		}
	}

	public static ArrayList<Cliente> getAll() {
		ResultSet resultSet = null;

		ArrayList<Cliente> clientes = new ArrayList<>();

		String ConsultarQuery = "SELECT Id_Cliente, NOME, CPF, TIMESTAMPDIFF (YEAR, DATA_NASCIMENTO, CURDATE()) AS IDADE, TELEFONE, CEP, BAIRRO, LOGRADOURO, COMPLEMENTO, NUMERO FROM CLIENTE INNER JOIN ENDERECO ON Id_Fk_Endereco = Id_Endereco";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(ConsultarQuery);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Cliente cliente = new Cliente();
				Endereco endereco = new Endereco();

				cliente.setIdCliente((resultSet.getInt("id_cliente")));
				cliente.setNome((resultSet.getString("nome")));
				cliente.setCpf((resultSet.getString("cpf")));
				cliente.setdataNascimento((resultSet.getString("idade")));
				cliente.setTelefone((resultSet.getString("telefone")));
				endereco.setCep((resultSet.getString("cep")));
				endereco.setBairro((resultSet.getString("bairro")));
				endereco.setLogradouro((resultSet.getString("logradouro")));
				endereco.setComplemento((resultSet.getString("complemento")));
				endereco.setNumero((resultSet.getString("numero")));
				cliente.setEndereco(endereco);

				clientes.add(cliente);
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "GetAllMethod" + erro.getMessage());
		}
		return clientes;
	}

	public static Cliente getById(int idCliente) {
		ResultSet resultSet = null;

		Cliente cliente = new Cliente();
		Endereco endereco = new Endereco();

		String ConsultarQuery = "SELECT Id_Cliente, NOME, CPF, TIMESTAMPDIFF (YEAR, DATA_NASCIMENTO, CURDATE()) AS IDADE, TELEFONE, CEP, BAIRRO, LOGRADOURO, COMPLEMENTO, NUMERO FROM CLIENTE INNER JOIN ENDERECO ON Id_Fk_Endereco = Id_Endereco WHERE Id_Endereco = ?";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(ConsultarQuery);
			statement.setInt(1, idCliente);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				cliente.setIdCliente((resultSet.getInt("id_cliente")));
				cliente.setNome((resultSet.getString("nome")));
				cliente.setCpf((resultSet.getString("cpf")));
				cliente.setdataNascimento((resultSet.getString("idade")));
				cliente.setTelefone((resultSet.getString("telefone")));
				endereco.setCep((resultSet.getString("cep")));
				endereco.setBairro((resultSet.getString("bairro")));
				endereco.setLogradouro((resultSet.getString("logradouro")));
				endereco.setComplemento((resultSet.getString("complemento")));
				endereco.setNumero((resultSet.getString("numero")));
				cliente.setEndereco(endereco);

			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "GetAllMethod" + erro.getMessage());
		}
		return cliente;
	}

	public static ArrayList<Cliente> getNascimento() {
		ResultSet resultSet = null;

		ArrayList<Cliente> getNascimentoBancoDados = new ArrayList<>();

		String query = "SELECT TIMESTAMPDIFF (YEAR, DATA_NASCIMENTO, CURDATE()) AS Idade FROM CLIENTE";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(query);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Cliente cliente = new Cliente();

				cliente.setdataNascimento((resultSet.getString("Idade")));

				getNascimentoBancoDados.add(cliente);
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "GetAllMethod" + erro.getMessage());
		}
		return getNascimentoBancoDados;
	}

	public static ArrayList<Cliente> getCPF() {
		ResultSet resultSet = null;
		ArrayList<Cliente> getCpfBancoDados = new ArrayList<>();

		String query = "SELECT CPF FROM CLIENTE";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(query);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Cliente Cliente = new Cliente();

				Cliente.setCpf((resultSet.getString("cpf")));

				getCpfBancoDados.add(Cliente);
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "GetAllMethod" + erro.getMessage());
		}
		return getCpfBancoDados;
	}

	public static ArrayList<Cliente> getTelefone() {
		ResultSet resultSet = null;
		ArrayList<Cliente> getTelefoneBancoDados = new ArrayList<>();

		String query = "SELECT TELEFONE FROM CLIENTE";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(query);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Cliente Cliente = new Cliente();

				Cliente.setTelefone((resultSet.getString("telefone")));

				getTelefoneBancoDados.add(Cliente);
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "GetAllMethod" + erro.getMessage());
		}
		return getTelefoneBancoDados;
	}

	public static ArrayList<Integer> GetIdPedido(int idCliente) {
		ResultSet resultSet = null;
		ArrayList<Integer> getIdPedido = new ArrayList<>();

		String query = "SELECT ID_PEDIDO FROM PEDIDO WHERE ID_FK_CLIENTE = ?";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, idCliente);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				
				int idPedido = resultSet.getInt("id_pedido");
				getIdPedido.add(idPedido);
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "GetAllMethodAUX" + erro.getMessage());
		}
		return getIdPedido;
	}

	public static void delete(int idCliente) {
		try {
			ArrayList<Integer> idPedido = GetIdPedido(idCliente);
			Connection conn = connection.connect();

			String DeleteQueryCliente = "Delete from pedido_produto where Id_Fk_Pedido = ?";

			PreparedStatement preparedStatement = conn.prepareStatement(DeleteQueryCliente);
			for(Integer Int : idPedido) {
				preparedStatement.setInt(1, Int);
				preparedStatement.execute();
			}
			
			DeleteQueryCliente = "Delete from pedido where id_fk_cliente = ?; Delete from cliente where Id_Cliente = ?; Delete from endereco where Id_Endereco = ?";
			preparedStatement = conn.prepareStatement(DeleteQueryCliente);
			preparedStatement.setInt(1, idCliente);
			preparedStatement.setInt(2, idCliente);
			preparedStatement.setInt(3, idCliente);
			preparedStatement.execute();
			preparedStatement.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "DeleteMethod - " + erro.getMessage());
		}
	}

	public static ArrayList<Cliente> getId() {
		ResultSet resultSet = null;
		ArrayList<Cliente> getIdBancoDados = new ArrayList<>();

		String query = "SELECT ID_CLIENTE FROM CLIENTE";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(query);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Cliente Cliente = new Cliente();

				Cliente.setIdCliente((resultSet.getInt("id_cliente")));

				getIdBancoDados.add(Cliente);
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "GetAllMethod" + erro.getMessage());
		}
		return getIdBancoDados;
	}

	public static int getIdade(int id) {
		ResultSet resultSet = null;
		String ConsultarQuery = "SELECT TIMESTAMPDIFF (YEAR, DATA_NASCIMENTO, CURDATE()) AS IDADE FROM CLIENTE WHERE Id_Cliente = ?";
		int idade = 0;
		
		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(ConsultarQuery);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				idade = (resultSet.getInt("Idade"));

			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "FindByIdMethod - " + erro.getMessage());
		}
		return idade;
	}
}