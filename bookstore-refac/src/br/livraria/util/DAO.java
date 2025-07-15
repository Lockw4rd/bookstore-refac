package br.livraria.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO {
    private DAO() {}    
    
    // Método para fechar recursos de forma segura
	public static void closeResources(Connection conn, PreparedStatement pstm, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }

            if (pstm != null) {
                pstm.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            // Tratar ou lançar uma exceção personalizada
            e.printStackTrace();
        }
    }
}
