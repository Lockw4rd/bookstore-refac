package br.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import br.livraria.factory.ConnectionFactory;
import br.livraria.model.Pessoa;

import br.livraria.util.DAO;

public class PessoaDAO {
	
	private PessoaDAO() {}
	
	public static void save(Pessoa pessoa) {
		
		String sql = "INSERT INTO Pessoa (cpf, nome, endereco, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, pessoa.getCpf());
			pstm.setString(2, pessoa.getNome());
			pstm.setString(3, pessoa.getEndereco());
			pstm.setString(4, pessoa.getCidade());
			pstm.setString(5, pessoa.getEstado());
			pstm.setString(6, pessoa.getTelefone());
			pstm.setString(7, pessoa.getEmail());
			
			pstm.execute();
			
			System.out.println(pessoa.getNome() + " Pessoa salva com sucesso");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			DAO.closeResources(conn, pstm, null);
		}
		
	}

	public static Pessoa getPessoaByCpf(String cpfBuscado) {
		
		String sql = "SELECT * FROM Pessoa WHERE cpf = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		ResultSet rs = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, cpfBuscado);
			
			rs = pstm.executeQuery();
			
			Pessoa pessoa = null;
			
			if(rs.next()) {
				
				String cpf = rs.getString("cpf");
				String nome = rs.getString("nome");
				String endereco = rs.getString("endereco");
				String cidade = rs.getString("cidade");
				String estado = rs.getString("estado");
				String telefone = rs.getString("telefone");
				String email = rs.getString("email");
				
				pessoa = new Pessoa(cpf, nome, endereco, cidade, estado, telefone, email);
				
				System.out.println(pessoa.getNome() + " Pessoa recuperada com sucesso");
				
			} else {
				System.out.println("Nenhuma pessoa encontrada com o CPF " + cpfBuscado);
			}
			
			return pessoa;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			DAO.closeResources(conn, pstm, rs);
			
		}
		
		return null;
		
	}
	
	public static Vector<Pessoa> getPessoas() {
		
		String sql = "SELECT * FROM Pessoa";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		ResultSet rs = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			
			Vector<Pessoa> pessoas = new Vector<Pessoa>();
			
			while(rs.next()) {
				
				String cpf = rs.getString("cpf");
				String nome = rs.getString("nome");
				String endereco = rs.getString("endereco");
				String cidade = rs.getString("cidade");
				String estado = rs.getString("estado");
				String telefone = rs.getString("telefone");
				String email = rs.getString("email");
				
				Pessoa pessoa = new Pessoa(cpf, nome, endereco, cidade, estado, telefone, email);
				
				pessoas.add(pessoa);
				
			} 
			
			System.out.println("Todas as pessoas recuperadas com sucesso");
			
			return pessoas;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			DAO.closeResources(conn, pstm, rs);
			
		}
		
		return null;
		
	}
	
	public static void update(Pessoa pessoa) {
		
		String sql = "UPDATE Pessoa SET nome = ?, endereco = ?, cidade = ?, estado = ?, telefone = ?, email = ?"
				+ " WHERE cpf = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, pessoa.getNome());
			pstm.setString(2, pessoa.getEndereco());
			pstm.setString(3, pessoa.getCidade());
			pstm.setString(4, pessoa.getEstado());
			pstm.setString(5, pessoa.getTelefone());
			pstm.setString(6, pessoa.getEmail());
			
			pstm.setString(7, pessoa.getCpf());
			
			pstm.execute();
			
			System.out.println(pessoa.getNome() + " Pessoa atualizada com sucesso");
			
		} catch (Exception e ) {
			
			e.printStackTrace();
			
		} finally {
			
			DAO.closeResources(conn, pstm, null);
			
		}
		
	}

	public static void deleteByCpf(String cpf) {
		
		String sql = "DELETE FROM Pessoa WHERE cpf = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, cpf);
			
			pstm.execute();
			
			System.out.println(cpf + " Pessoa deletada com sucesso");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			DAO.closeResources(conn, pstm, null);
			
		}
		
	}

}