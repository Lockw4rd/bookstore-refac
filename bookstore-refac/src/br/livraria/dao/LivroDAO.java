package br.livraria.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import br.livraria.factory.ConnectionFactory;
import br.livraria.model.Editora;
import br.livraria.model.Livro;

import br.livraria.util.DAO;

public class LivroDAO {
	
	private LivroDAO() {}

	public static void save(Livro livro) {
		
		String sql = "INSERT INTO Livro (titulo, genero, data_publicada, autor, qtd_estoque, preco_unit, id_editora) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, livro.getTitulo());
			pstm.setString(2, livro.getGenero());
			pstm.setDate(3, new Date(livro.getDataPublicada().getTime()));
			pstm.setString(4, livro.getAutor());
			pstm.setInt(5, livro.getQtdEstoque());
			pstm.setDouble(6, livro.getPrecoUnit());
			pstm.setInt(7, livro.getEditora().getIdEditora());
			
			pstm.execute();
			
			System.out.println(livro.getTitulo() + " Livro salvo com sucesso");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			DAO.closeResources(conn, pstm, null);
		}
		
	}
	
	public static Vector<Livro> getLivros() {
		
		String sql = "SELECT * FROM Livro";
		
		Vector<Livro> livros = new Vector<Livro>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		ResultSet rs = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				String titulo = rs.getString("titulo");
				String genero = rs.getString("genero");
				java.util.Date dataPublicada = rs.getDate("data_publicada");
				String autor = rs.getString("autor");
				int qtdEstoque = rs.getInt("qtd_estoque");
				double precoUnit = rs.getDouble("preco_unit");
				Editora editora = EditoraDAO.getEditoraById(rs.getInt("id_editora"));
				
				Livro livro = new Livro(titulo, genero, dataPublicada, autor, qtdEstoque, precoUnit, editora);
				livro.setIdLivro(rs.getInt("id_livro"));
				
				livros.add(livro);
				
			}
			
			System.out.println("Todos os livros recuperados com sucesso");
			
			return livros;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			DAO.closeResources(conn, pstm, rs);
			
		}
		
		return null;
		
	}
	
	public static Livro getLivroById(int idLivro) {
		
		String sql = "SELECT * FROM Livro WHERE id_livro = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		ResultSet rs = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, idLivro);
			
			rs = pstm.executeQuery();
			
			Livro livro = null;
			
			if(rs.next()) {
				
				String titulo = rs.getString("titulo");
				String genero = rs.getString("genero");
				java.util.Date dataPublicada = rs.getDate("data_publicada");
				String autor = rs.getString("autor");
				int qtdEstoque = rs.getInt("qtd_estoque");
				double precoUnit = rs.getDouble("preco_unit");
				Editora editora = EditoraDAO.getEditoraById(rs.getInt("id_editora"));
				
				livro = new Livro(titulo, genero, dataPublicada, autor, qtdEstoque, precoUnit, editora);
				livro.setIdLivro(rs.getInt("id_livro"));
				
				System.out.println(livro.getTitulo() + " Livro recuperado com sucesso");
				
			} else {
				System.out.println("Nao foi encontrado um livro com o id " + idLivro);
			}
			
			return livro;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			DAO.closeResources(conn, pstm, rs);
			
		}
		
		return null;
		
	}
	
	public static void update(Livro livro) {
		
		String sql = "UPDATE Livro SET titulo = ?, genero = ?, data_publicada = ?, autor = ?, qtd_estoque = ?, preco_unit = ?, id_editora = ?"
				+ " WHERE id_livro = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, livro.getTitulo());
			pstm.setString(2, livro.getGenero());
			pstm.setDate(3, new Date(livro.getDataPublicada().getTime()));
			pstm.setString(4, livro.getAutor());
			pstm.setInt(5, livro.getQtdEstoque());
			pstm.setDouble(6, livro.getPrecoUnit());
			pstm.setInt(7, livro.getEditora().getIdEditora());
			
			pstm.setInt(8, livro.getIdLivro());
			
			pstm.execute();
			
			System.out.println(livro.getTitulo() + " Livro atualizado com sucesso");
			
		} catch (Exception e ) {
			
			e.printStackTrace();
			
		} finally {
			
			DAO.closeResources(conn, pstm, null);
			
		}
		
	}
	
	public static void deleteByID(int id) {
		
		String sql = "DELETE FROM Livro WHERE id_livro = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
			
			System.out.println(id + " Livro deletado com sucesso");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			DAO.closeResources(conn, pstm, null);
			
		}
		
	}

}
