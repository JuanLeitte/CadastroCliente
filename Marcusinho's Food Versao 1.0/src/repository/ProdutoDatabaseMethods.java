package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import entity.Produto;

public class ProdutoDatabaseMethods {
	private static RepositoryConnection connection = new RepositoryConnection();

	public static void create(Produto produto) {
		try {
			String QueryCliente = "INSERT INTO PRODUTO(ID_PRODUTO, NOME, DESCRICAO, VALOR_PRODUTO, CLASSIFICACAO, DISPONIBILIDADE) VALUES (NULL, ?, ?, ?, ?, ?)";
			// CODIGO, QUANTIDADE

			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(QueryCliente);

			statement.setString(1, produto.getNome());
			statement.setString(2, produto.getDescricao());
//			statement.setInt(3, produto.getCodigo());
//			statement.setInt(4, produto.getQuantidade());
			statement.setDouble(3, produto.getPreco());
			statement.setString(4, produto.getClassificacao());
			statement.setString(5, produto.getDisponibilidade());

			statement.execute();
			statement.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "ProdutoCreateMethod" + erro.getMessage());
		}
	}

	public static ArrayList<Produto> getAll() {
		ResultSet resultSet = null;

		ArrayList<Produto> produtos = new ArrayList<>();

		String ConsultarQuery = "SELECT * FROM PRODUTO";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(ConsultarQuery);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Produto produto = new Produto();

				produto.setIdProduto((resultSet.getInt("Id_Produto")));
				produto.setNome((resultSet.getString("Nome")));
				produto.setDescricao((resultSet.getString("Descricao")));
//			produto.setQuantidade((resultSet.getInt("quantidade")));
//			produto.setCodigo((resultSet.getInt("codigo")));
				produto.setPreco((resultSet.getDouble("Valor_Produto")));
				produto.setClassificacao((resultSet.getString("Classificacao")));
				produto.setDisponibilidade((resultSet.getString("Disponibilidade")));

				produtos.add(produto);
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "ProdutoGetAllMethod" + erro.getMessage());
		}
		return produtos;
	}

	public static ArrayList<Produto> findById(int idProduto) {
		ResultSet resultSet = null;

		ArrayList<Produto> produtos = new ArrayList<>();

		String ConsultarQuery = "SELECT * FROM PRODUTO WHERE Id_Produto = ?";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(ConsultarQuery);
			statement.setInt(1, idProduto);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Produto produto = new Produto();

				produto.setIdProduto((resultSet.getInt("Id_Produto")));
				produto.setNome((resultSet.getString("Nome")));
				produto.setDescricao((resultSet.getString("Descricao")));
//			produto.setQuantidade((resultSet.getInt("quantidade")));
//			produto.setCodigo((resultSet.getInt("codigo")));
				produto.setPreco((resultSet.getDouble("Valor_Produto")));
				produto.setClassificacao((resultSet.getString("Classificacao")));
				produto.setDisponibilidade((resultSet.getString("Disponibilidade")));

				produtos.add(produto);
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "ProdutoGetAllMethod" + erro.getMessage());
		}
		return produtos;
	}

	public static void atualizarDisponibilidade(String disponilidade, int id) {
		try {
			Connection conn = connection.connect();

			String DeleteQueryProduto = "UPDATE PRODUTO SET DISPONIBILIDADE = ? WHERE ID_PRODUTO = ?";

			PreparedStatement preparedStatement = conn.prepareStatement(DeleteQueryProduto);
			preparedStatement.setString(1, disponilidade);
			preparedStatement.setInt(2, id);

			preparedStatement.execute();
			preparedStatement.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "DeleteMethodProduto - " + erro.getMessage());
		}
	}

	public static ArrayList<Produto> getIdProduto() {
		ResultSet resultSet = null;
		ArrayList<Produto> getIdBancoDados = new ArrayList<>();

		String query = "SELECT ID_PRODUTO FROM PRODUTO";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Produto produto = new Produto();

				produto.setIdProduto((resultSet.getInt("Id_Produto")));

				getIdBancoDados.add(produto);
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "TRATAMENTO ID" + erro.getMessage());
		}
		return getIdBancoDados;
	}

	public static void update(Produto produto) {
		try {

			String QueryUpdate = "UPDATE PRODUTO SET NOME = ?, DESCRICAO = ?, VALOR_PRODUTO = ?, CLASSIFICACAO = ?, DISPONIBILIDADE = ? WHERE Id_Produto = ?;";
			// QUANTIDADE = ?, CODIGO = ?

			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(QueryUpdate);
			statement.setString(1, produto.getNome());
			statement.setString(2, produto.getDescricao());
			statement.setDouble(3, produto.getPreco());
			statement.setString(4, produto.getClassificacao());
			statement.setString(5, produto.getDisponibilidade());
			statement.setDouble(6, produto.getIdProduto());
//			statement.setInt(4, produto.getQuantidade());
//			statement.setInt(5, produto.getCodigo());
			statement.execute();
			statement.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "UpdateMethodProduto" + erro.getMessage());
		}
	}

	public static void delete(int produto) throws Exception {

		String QueryUpdate = "DELETE FROM PRODUTO WHERE Id_Produto = ?";

		Connection conn = connection.connect();
		PreparedStatement statement = conn.prepareStatement(QueryUpdate);
		statement.setInt(1, produto);
		statement.execute();
		statement.close();
	}

	public static ArrayList<Produto> getId() {
		ResultSet resultSet = null;
		ArrayList<Produto> getIdBancoDados = new ArrayList<>();

		String query = "SELECT Id_Produto FROM PRODUTO";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Produto produto = new Produto();

				produto.setIdProduto((resultSet.getInt("Id_Produto")));

				getIdBancoDados.add(produto);
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "GetAllMethod" + erro.getMessage());
		}
		return getIdBancoDados;
	}

	public static ArrayList<Produto> findByName(String nome) {
		ResultSet resultSet = null;
		ArrayList<Produto> produtos = new ArrayList<>();

		String ConsultarQuery = "SELECT * FROM PRODUTO WHERE NOME LIKE '%' ? '%'";

		try {
			Connection conn = connection.connect();
			PreparedStatement statement = conn.prepareStatement(ConsultarQuery);
			statement.setString(1, nome);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Produto produto = new Produto();

				produto.setIdProduto((resultSet.getInt("Id_Produto")));
				produto.setNome((resultSet.getString("Nome")));
				produto.setDescricao((resultSet.getString("Descricao")));
				produto.setPreco((resultSet.getDouble("Valor_Produto")));
				produto.setClassificacao((resultSet.getString("Classificacao")));
				produto.setDisponibilidade((resultSet.getString("Disponibilidade")));

				produtos.add(produto);
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "FindByNameMethod - " + erro.getMessage());
		}
		return produtos;
	}
}