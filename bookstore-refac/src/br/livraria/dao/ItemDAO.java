package br.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import br.livraria.factory.ConnectionFactory;
import br.livraria.model.Item;
import br.livraria.model.Livro;
import br.livraria.model.Pedido;

import br.livraria.util.DAO;

public class ItemDAO {
	
	private ItemDAO() {}

	public static void save(Item item) {
	
		String sql = "INSERT INTO Item (id_livro, id_pedido, qtd_vendida, preco_total) VALUES (?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {

			conn = ConnectionFactory.createConnectionToSQLite();

			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, item.getLivro().getIdLivro());
			pstm.setInt(2, item.getPedido().getIdPedido());
			pstm.setInt(3, item.getQtdVendida());
			pstm.setDouble(4, item.getPrecoTotal());
			pstm.execute();

			System.out.println(item.getIdItem() + " Item salvo com sucesso");
			
			PedidoDAO.updatePreco(item.getPedido().getIdPedido());
			
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			DAO.closeResources(conn, pstm, null);
		}

	}
	
	public static Item getItemByIdLivro(int idLivro, int idPedido) {
		
		String sql = "SELECT * FROM Item WHERE id_livro = ? AND id_pedido = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		ResultSet rs = null;

		try {

			conn = ConnectionFactory.createConnectionToSQLite();

			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, idLivro);
			pstm.setInt(2, idPedido);
			
			rs = pstm.executeQuery();

			if(rs.next()) {
				
				Livro livro = LivroDAO.getLivroById(rs.getInt("id_livro"));
				Pedido pedido = PedidoDAO.getPedidoById(rs.getInt("id_pedido"));
				int qtdVendida = rs.getInt("qtd_vendida");
				
				Item item = new Item(livro, pedido, qtdVendida);
				item.setIdItem(rs.getInt("id_item"));
				
				System.out.println(item.getIdItem() + " Item recuperado");
				
				return item;
				
			} else {
				System.out.println("Nao foi encontrado nenhum item com esse id");
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			DAO.closeResources(conn, pstm, rs);

		}

		return null;
	}

	public static Vector<Item> getItensByPedido(int idPedido) {

		String sql = "SELECT * FROM Item WHERE id_pedido = ?";

		Vector<Item> itens = new Vector<Item>();

		Connection conn = null;
		PreparedStatement pstm = null;

		ResultSet rs = null;

		try {

			conn = ConnectionFactory.createConnectionToSQLite();

			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, idPedido);
			
			rs = pstm.executeQuery();

			Pedido pedido = PedidoDAO.getPedidoById(idPedido);
			
			while(rs.next()) {
				
				Livro livro = LivroDAO.getLivroById(rs.getInt("id_livro"));
				int qtdVendida = rs.getInt("qtd_vendida");

				Item item = new Item(livro, pedido, qtdVendida);
				item.setIdItem(rs.getInt("id_item"));
				
				itens.add(item);

			}

			System.out.println("Todos os itens do pedido " + pedido.getIdPedido() + " recuperados com sucesso");

			return itens;

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			DAO.closeResources(conn, pstm, rs);

		}

		return null;

	}
	
	public static Item getItemById(int id) {
		
		String sql = "SELECT * FROM Item WHERE id_item = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		ResultSet rs = null;

		try {

			conn = ConnectionFactory.createConnectionToSQLite();

			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, id);
			
			rs = pstm.executeQuery();

			if(rs.next()) {
				
				Livro livro = LivroDAO.getLivroById(rs.getInt("id_livro"));
				Pedido pedido = PedidoDAO.getPedidoById(rs.getInt("id_pedido"));
				int qtdVendida = rs.getInt("qtd_vendida");
				
				Item item = new Item(livro, pedido, qtdVendida);
				item.setIdItem(rs.getInt("id_item"));
				
				System.out.println(item.getIdItem() + " Item recuperado");
				
				return item;
				
			} else {
				System.out.println("Nao foi encontrado nenhum item com esse id");
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			DAO.closeResources(conn, pstm, rs);

		}

		return null;
	}

	public static void update(Item item) {

		String sql = "UPDATE Item SET id_livro = ?, id_pedido = ?, qtd_vendida = ?, preco_total = ?"
				+ " WHERE id_item = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {

			conn = ConnectionFactory.createConnectionToSQLite();

			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, item.getLivro().getIdLivro());
			pstm.setInt(2, item.getPedido().getIdPedido());
			pstm.setInt(3, item.getQtdVendida());
			pstm.setDouble(4, item.getPrecoTotal());

			pstm.setInt(5, item.getIdItem());

			pstm.execute();

			System.out.println(item.getIdItem() + " Item atualizado com sucesso");
			
			PedidoDAO.updatePreco(item.getPedido().getIdPedido());
			
		} catch (Exception e ) {

			e.printStackTrace();

		} finally {

			DAO.closeResources(conn, pstm, null);

		}

	}

	public static void deleteByID(int id) {

		String sql = "DELETE FROM Item WHERE id_item = ?";
		
		Item item = ItemDAO.getItemById(id);
		
		Connection conn = null;

		PreparedStatement pstm = null;

		try {

			conn = ConnectionFactory.createConnectionToSQLite();

			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();

			System.out.println(id + " Item deletado com sucesso");
			
			PedidoDAO.updatePreco(item.getPedido().getIdPedido());

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			DAO.closeResources(conn, pstm, null);

		}

	}

}