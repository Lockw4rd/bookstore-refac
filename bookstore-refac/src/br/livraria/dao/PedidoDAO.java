package br.livraria.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import br.livraria.factory.ConnectionFactory;
import br.livraria.model.Funcionario;
import br.livraria.model.Item;
import br.livraria.model.Pedido;
import br.livraria.model.Pessoa;

import br.livraria.util.DAO;

public class PedidoDAO {
	
	private PedidoDAO() {}
	
	public static void updatePreco(int idPedido) {
		
		Vector<Item> itens = ItemDAO.getItensByPedido(idPedido);
		
		double precoTotal = 0;
		for(int i = 0; i < itens.size(); i++) {
			precoTotal += itens.get(i).getPrecoTotal();
		}
		
		String sql = "UPDATE Pedido SET preco_total = ?"
				+ " WHERE id_pedido = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setDouble(1, precoTotal);
			
			pstm.setInt(2, idPedido);
			
			pstm.execute();
			
			System.out.println(idPedido + " Pedido atualizado com sucesso " + precoTotal);
			
		} catch (Exception e ) {
			
			e.printStackTrace();
			
		} finally {
			
			DAO.closeResources(conn, pstm, null);
			
		}
		
	}

	public static void open(Pedido pedido) {
		
		String sql = "INSERT INTO Pedido (id_funcionario, data_pedido, operacao) VALUES (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, pedido.getFuncionario().getIdFuncionario());
			pstm.setDate(2, new Date(pedido.getDataPedido().getTime()));
			pstm.setString(3, pedido.getOperacao());
			
			pstm.execute();
			
			System.out.println(pedido.getIdPedido() + " Pedido aberto com sucesso");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			DAO.closeResources(conn, pstm, null);
			
		}
		
	}
	
	public static Pedido getLastPedido() {
		
		String sql = "SELECT * FROM Pedido ORDER BY id_pedido DESC LIMIT 1";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		ResultSet rs = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			
			Pedido pedido = null;
			
			if(rs.next()) {
				
				Funcionario funcionario = FuncionarioDAO.getFuncionarioById(rs.getInt("id_funcionario"));
				java.util.Date dataPedido = rs.getDate("data_pedido");
				String operacao = rs.getString("operacao");
				
				pedido = new Pedido(funcionario, dataPedido, operacao);
				pedido.setIdPedido(rs.getInt("id_pedido"));
				
				System.out.println(pedido.getIdPedido() + " Ultimo pedido recuperado com sucesso");
				
			} else {
				System.out.println("Nao ha pedidos");
			}
			
			return pedido;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			DAO.closeResources(conn, pstm, rs);
			
		}
		
		return null;
	}
	
	public static Pedido getPedidoById(int idPedidoBuscado) {
		
		String sql = "SELECT * FROM Pedido WHERE id_pedido = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		ResultSet rs = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, idPedidoBuscado);
			
			rs = pstm.executeQuery();
			
			Pedido pedido = null;
			
			if(rs.next()) {
				
				Funcionario funcionario = FuncionarioDAO.getFuncionarioById(rs.getInt("id_funcionario")); 
				Pessoa cliente = PessoaDAO.getPessoaByCpf(rs.getString("cpf_cliente")); 
				java.util.Date dataPedido = rs.getDate("data_pedido"); 
				double totalPreco = rs.getDouble("preco_total"); 
				String formaPagamento = rs.getString("forma_pagamento"); 
				String operacao = rs.getString("operacao");
				
				pedido = new Pedido(funcionario, cliente, dataPedido, totalPreco, formaPagamento, operacao);
				pedido.setIdPedido(rs.getInt("id_pedido"));
				
				System.out.println(pedido.getIdPedido() + " Pedido recuperado com sucesso");
				
			} else {
				System.out.println("Nao foi encontrado o pedido com o id " + idPedidoBuscado);
			}
			
			return pedido;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			DAO.closeResources(conn, pstm, rs);
			
		}
		
		return null;
	}
	
	public static void update(Pedido pedido) {
		
		String sql = "UPDATE Pedido SET id_funcionario = ?, cpf_cliente = ?, data_pedido = ?, preco_total = ?, forma_pagamento = ?, operacao = ?"
				+ " WHERE id_pedido = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, pedido.getFuncionario().getIdFuncionario());
			pstm.setString(2, pedido.getCliente().getCpf());
			pstm.setDate(3, new Date(pedido.getDataPedido().getTime()));
			pstm.setDouble(4, pedido.getPrecoTotal());
			pstm.setString(5, pedido.getFormaPagamento());
			pstm.setString(6, pedido.getOperacao());
			
			pstm.setInt(7, pedido.getIdPedido());
			
			pstm.execute();
			
			System.out.println(pedido.getIdPedido() + " Pedido atualizado com sucesso");
			
		} catch (Exception e ) {
			
			e.printStackTrace();
			
		} finally {
			DAO.closeResources(conn, pstm, null);
		}
		
	}
	
	public static void close(Pedido pedido) {
		
		String sql = "UPDATE Pedido SET cpf_cliente = ?, preco_total = ?, forma_pagamento = ?"
				+ " WHERE id_pedido = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, pedido.getCliente().getCpf());
			pstm.setDouble(2, pedido.getPrecoTotal());
			pstm.setString(3, pedido.getFormaPagamento());
			
			pstm.setInt(4, pedido.getIdPedido());
			
			pstm.execute();
			
			System.out.println(pedido.getIdPedido() + " Pedido finalizado com sucesso");
			
			PedidoDAO.updatePreco(pedido.getIdPedido());
			
		} catch (Exception e ) {
			
			e.printStackTrace();
			
		} finally {
			
			DAO.closeResources(conn, pstm, null);
			
		}
		
	}
	
	public static Vector<Pedido> getPedidos() {
		
		String sql = "SELECT * FROM Pedido";
		
		Vector<Pedido> pedidos = new Vector<Pedido>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		ResultSet rs = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				Funcionario funcionario = FuncionarioDAO.getFuncionarioById(rs.getInt("id_funcionario"));
				Pessoa cliente = PessoaDAO.getPessoaByCpf(rs.getString("cpf_cliente"));
				java.util.Date dataPedido = rs.getDate("data_pedido");
				double precoTotal = rs.getDouble("preco_total");
				String formaPagamento = rs.getString("forma_pagamento");
				String operacao = rs.getString("operacao");
				
				Pedido pedido = new Pedido(funcionario, cliente, dataPedido, precoTotal, formaPagamento, operacao);
				pedido.setIdPedido(rs.getInt("id_pedido"));
				
				pedidos.add(pedido);
				
			}
			
			System.out.println("Todos os pedidos recuperados com sucesso");
			
			return pedidos;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			DAO.closeResources(conn, pstm, rs);
			
		}
		
		return null;
		
	}
	
	public static void deleteById(int id) {
		
		String sql = "DELETE FROM Pedido WHERE id_pedido = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
			
			System.out.println(id + " Pedido deletado com sucesso");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			DAO.closeResources(conn, pstm, null);
			
		}
		
	}

}