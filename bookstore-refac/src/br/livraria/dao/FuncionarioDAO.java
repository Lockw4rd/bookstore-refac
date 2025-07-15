package br.livraria.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import br.livraria.factory.ConnectionFactory;
import br.livraria.model.Funcionario;
import br.livraria.model.Pessoa;

import br.livraria.util.DAO;

public class FuncionarioDAO {
	
	private FuncionarioDAO() {}
	
	public static void save(Funcionario funcionario) {
		
		PessoaDAO.save(funcionario);
		
		String sql = "INSERT INTO Funcionario (senha, data_contrato, administrador, cpf) VALUES (?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToSQLite();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, funcionario.getSenha());
			pstm.setDate(2, new Date(funcionario.getDataContrato().getTime()));
			pstm.setBoolean(3, funcionario.isAdministrador());
			pstm.setString(4, funcionario.getCpf());
			
			pstm.execute();
			
			System.out.println(funcionario.getNome() + " Funcionario salvo com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			DAO.closeResources(conn, pstm, null);
		}
		
	}

	public static Vector<Funcionario> getFuncionarios() {
		String sql = "SELECT * FROM Funcionario";
		
		Vector<Funcionario> funcionarios = new Vector<Funcionario>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		ResultSet rs = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				String senha = rs.getString("senha");
				java.util.Date dataContrato = rs.getDate("data_contrato");
				boolean administrador = rs.getBoolean("administrador");
				String cpf = rs.getString("cpf");
				
				Pessoa pessoa = PessoaDAO.getPessoaByCpf(cpf);
				Funcionario funcionario = new Funcionario(pessoa, senha, dataContrato, administrador);
				funcionario.setIdFuncionario(rs.getInt("id_funcionario"));
				
				funcionarios.add(funcionario);
				
			}
			
			System.out.println("Todos os funcionarios recuperados com sucesso");
			
			return funcionarios;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			DAO.closeResources(conn, pstm, rs);
		}
		
		return null;
		
	}
	
	public static Funcionario getFuncionarioById(int idFuncionario) {
		
		String sql = "SELECT * FROM Funcionario WHERE id_funcionario = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		ResultSet rs = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, idFuncionario);
			
			rs = pstm.executeQuery();
			
			Funcionario funcionario = null;
			
			if(rs.next()) {
				
				String senha = rs.getString("senha");
				java.util.Date dataContrato = rs.getDate("data_contrato");
				boolean administrador = rs.getBoolean("administrador");
				String cpf = rs.getString("cpf");
				
				Pessoa pessoa = PessoaDAO.getPessoaByCpf(cpf);
				funcionario = new Funcionario(pessoa, senha, dataContrato, administrador);
				funcionario.setIdFuncionario(rs.getInt("id_funcionario"));
				
				System.out.println(funcionario.getNome() + " Funcionario recuperado com sucesso");
				
			} else {
				System.out.println("Nenhum funcionario encontrado com o id " + idFuncionario);
			}
			
			return funcionario;
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			DAO.closeResources(conn, pstm, rs);
		}
		
		return null;
		
	}
	
	public static void update(Funcionario funcionario) {
		
		PessoaDAO.update(funcionario);
		
		String sql = "UPDATE Funcionario SET senha = ?, data_contrato = ?, administrador = ?"
				+ " WHERE id_funcionario = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, funcionario.getSenha());
			pstm.setDate(2, new Date(funcionario.getDataContrato().getTime()));
			pstm.setBoolean(3, funcionario.isAdministrador());
			
			pstm.setInt(4, funcionario.getIdFuncionario());
			
			pstm.execute();
			
			System.out.println(funcionario.getNome() + " Funcionario atualizado com sucesso");
			
		} catch (Exception e ) {
			
			e.printStackTrace();
			
		} finally {
			DAO.closeResources(conn, pstm, null);	
		}
	}

	public static void deleteByID(int id) {
		
		String sql = "DELETE FROM Funcionario WHERE id_funcionario = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToSQLite();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
			
			System.out.println(id + " Funcionario deletado com sucesso");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			DAO.closeResources(conn, pstm, null);
		}
	}




}