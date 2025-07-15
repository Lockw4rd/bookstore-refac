package br.livraria.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	private static final String DATABASE_URL = "jdbc:sqlite:banco.db";
	
	private ConnectionFactory() {}
	
	/*
	 * Conexão com o SQLite
	 */
	public static Connection createConnectionToSQLite() throws Exception {
		
		Class.forName("org.sqlite.JDBC");
		
		Connection connection = DriverManager.getConnection(DATABASE_URL);
		
		return connection;
	}
	
}
