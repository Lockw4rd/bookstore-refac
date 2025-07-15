package br.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import br.livraria.factory.ConnectionFactory;
import br.livraria.model.Editora;

import br.livraria.util.DAO;

public class EditoraDAO {
	
	private EditoraDAO() {}
	
	public static void save(Editora editora) {
		
		String sql = "INSERT INTO Editora (nome, cnpj, endereco) VALUES (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, editora.getNome());
			pstm.setString(2, editora.getCnpj());
			pstm.setString(3, editora.getEndereco());
			
			pstm.execute();
			
			System.out.println(editora.getNome() + " Editora salva com sucesso");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
	
			DAO.closeResources(conn, pstm, null);
		}
		
	}
	
	public static Vector<Editora> getEditoras() {
		
		String sql = "SELECT * FROM Editora";
		
		Vector<Editora> editoras = new Vector<Editora>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		ResultSet rs = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				String cnpj = rs.getString("cnpj");
				String nome = rs.getString("nome");
				String endereco = rs.getString("endereco");
				
				Editora editora = new Editora(nome, cnpj, endereco);
				editora.setIdEditora(rs.getInt("id_editora"));
				
				editoras.add(editora);
				
			}
			
			System.out.println("Todas editoras recuperadas com sucesso");
			
			return editoras;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			DAO.closeResources(conn, pstm, rs);
			
		}
		
		return null;
		
	}
	
	public static Editora getEditoraById(int idEditora) {
		
		String sql = "SELECT * FROM Editora WHERE id_editora = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		ResultSet rs = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, idEditora);
			
			rs = pstm.executeQuery();
			
			Editora editora = null;
			
			if(rs.next()) {
				
				String cnpj = rs.getString("cnpj");
				String nome = rs.getString("nome");
				String endereco = rs.getString("endereco");
				
				editora = new Editora(nome, cnpj, endereco);
				editora.setIdEditora(rs.getInt("id_editora"));
				
				System.out.println(editora.getNome() + " Editora recuperada com sucesso");
				
			} else {
				System.out.println("Nenhuma editora encontrada com o id " + idEditora);
			}
			
			return editora;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			DAO.closeResources(conn, pstm, rs);
			
		}
		
		return null;
		
	}
	
	public static void update(Editora editora) {
		
		String sql = "UPDATE Editora SET cnpj = ?, nome = ?, endereco = ?"
				+ " WHERE id_editora = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, editora.getCnpj());
			pstm.setString(2, editora.getNome());
			pstm.setString(3, editora.getEndereco());
			
			pstm.setInt(4, editora.getIdEditora());
			
			pstm.execute();
			
			System.out.println(editora.getNome() + " Editora atualizada com sucesso");
			
		} catch (Exception e ) {
			
			e.printStackTrace();
			
		} finally {
			DAO.closeResources(conn, pstm, null);
			
		}
		
	}
	
	public static void deleteByID(int id) {
		
		String sql = "DELETE FROM Editora WHERE id_editora = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
			
			System.out.println(id + " Editora deletada com sucesso");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			DAO.closeResources(conn, pstm, null);
			
		}
		
	}

	public static boolean existeEditoraComCnpj(String cnpj) {
		Vector<Editora> editoras = EditoraDAO.getEditoras();
		for (Editora editora : editoras) {
			if (editora.getCnpj().equals(cnpj)) {
				return true;
			}
		}
		return false;
	}
	

}
