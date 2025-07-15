package br.livraria.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

import br.livraria.dao.EditoraDAO;
import br.livraria.dao.FuncionarioDAO;
import br.livraria.dao.LivroDAO;
import br.livraria.dao.PessoaDAO;
import br.livraria.model.Editora;
import br.livraria.model.Funcionario;
import br.livraria.model.Livro;
import br.livraria.model.Pessoa;

public class TableFactory {
	
	private TableFactory() {}

	public static void createTables() {
		executeSqlScript(new File("resources\\livraria.sql"));
	}
	
	public static void createAdministrador() {
		
		Funcionario funcionario = new Funcionario("00000000000", "ADM", "", "", "", "", "", "123", new Date(), true);
		
		FuncionarioDAO.save(funcionario);
	}
	
	public static void createEditora() {
		
		Editora editora;
		
		editora = new Editora("Empresa do Jeu", "00001111222233", "Bonito");
		EditoraDAO.save(editora);
		
		editora = new Editora("Empresa do JL", "11112222333344", "Marte");
		EditoraDAO.save(editora);
		
		editora = new Editora("Empresa do Baiano", "22223333444455", "Bahia");
		EditoraDAO.save(editora);
		
		editora = new Editora("Empresa do Matheus", "33334444555566", "Floripa");
		EditoraDAO.save(editora);
		
		editora = new Editora("Empresa da Gabi", "44445555666677", "Londres");
		EditoraDAO.save(editora);
		
		editora = new Editora("Empresa do Halls", "55556666777788", "Acre");
		EditoraDAO.save(editora);
		
	}
	
	public static void createLivro() {
		
		Livro livro;
		
		livro = new Livro("Aprendendo JAVA", "Programacao", new Date(), "Jeu", 100, 50, EditoraDAO.getEditoraById(1));
		LivroDAO.save(livro);
		
		livro = new Livro("Como reconhecer um chato", "Licao de Vida", new Date(), "JL", 100, 50, EditoraDAO.getEditoraById(2));
		LivroDAO.save(livro);
		
		livro = new Livro("Como dormir 50h por dia", "Ensinamento", new Date(), "Baiano", 100, 50, EditoraDAO.getEditoraById(3));
		LivroDAO.save(livro);
		
		livro = new Livro("Do 0 ao 1bi - Entenda como eu conquister", "Motivacional", new Date(), "Matheus", 100, 50, EditoraDAO.getEditoraById(4));
		LivroDAO.save(livro);
		
		livro = new Livro("A vida de uma milionaria", "Entreterimento", new Date(), "Gabizola", 100, 50, EditoraDAO.getEditoraById(5));
		LivroDAO.save(livro);
		
		livro = new Livro("Sobrevivendo a Selva", "Sobrevivencia", new Date(), "Halls", 100, 50, EditoraDAO.getEditoraById(6));
		LivroDAO.save(livro);
	}
	
	
	private static void executeSqlScript(File inputFile) {

	    // Delimitador - usado para finalizar operacao
	    String delimiter = ";";
	    
	    Connection conn = null;
	    try {
	    	
	    	conn = ConnectionFactory.createConnectionToSQLite();
	    	
	    } catch (Exception e) {
	    	
	    	e.printStackTrace();
	    	
	    }

	    Scanner scanner = null;
	    try {
	    	
	        scanner = new Scanner(inputFile).useDelimiter(delimiter);
	        
	    } catch (FileNotFoundException e) {
	    	
	        e.printStackTrace();
	        
	    }

	    Statement currentStatement = null;
	    while(scanner.hasNext()) {

	        // Get statement 
	        String rawStatement = scanner.next() + delimiter;
	        try {
	            // Execute statement
	            currentStatement = conn.createStatement();
	            currentStatement.execute(rawStatement);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            // Release resources
	            if (currentStatement != null) {
	                try {
	                    currentStatement.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	            currentStatement = null;
	        }
	    }
	    
	    scanner.close();
	    
	}

	public static void createFuncionario() {
		
		Funcionario funcionario;
		
		funcionario = new Funcionario("00011122233", "Jeu Chaves", "", "Bonito", "MS", "", "", "123456", new Date(), false);
		FuncionarioDAO.save(funcionario);
		
		funcionario = new Funcionario("11122233344", "Matheuzao", "", "Floripa", "SC", "", "", "123456", new Date(), false);
		FuncionarioDAO.save(funcionario);
		
		funcionario = new Funcionario("22233344455", "Baiano", "", "Bahia", "BH", "", "", "123456", new Date(), false);
		FuncionarioDAO.save(funcionario);
		
		funcionario = new Funcionario("33344455566", "Gabizola", "", "Londres", "US", "", "", "123456", new Date(), false);
		FuncionarioDAO.save(funcionario);
		
		funcionario = new Funcionario("44455566677", "JL", "", "Campo Grande", "MS", "", "", "123456", new Date(), false);
		FuncionarioDAO.save(funcionario);
	}

	public static void createCliente() {
		
		Pessoa cliente;
		
		cliente = new Pessoa("55566677788", "Professora Querida", "", "", "", "", "");
		PessoaDAO.save(cliente);
		
		cliente = new Pessoa("66677788899", "Aluno Amigo", "", "", "", "", "");
		PessoaDAO.save(cliente);
		
		cliente = new Pessoa("77788899900", "Cliente Padrao", "", "", "", "", "");
		PessoaDAO.save(cliente);
		
	}
	
}