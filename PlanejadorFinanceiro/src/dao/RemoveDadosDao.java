package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RemoveDadosDao {
	public static void removeCategoria(String tabela, String categoria, String user) throws SQLException {
		Statement st = null;
		try {
			try {
				Connection conn = BancoDados.conectar();

				String statement = "delete from categoria" + tabela + " where categoria = \"" + categoria+"\" and user = \""+user+"\"";
				System.out.println(statement);
				
				st = conn.createStatement();
				st.executeUpdate(statement);
				
			} finally {

				BancoDados.finalizarStatement(st);
				BancoDados.desconectar();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void removeTupla(String tabela, String codigo, String user) throws SQLException {
		Statement st = null;
		try {
			try {
				Connection conn = BancoDados.conectar();

				String statement = "delete from " + tabela + " where codigo = \"" + codigo+"\" and user = \""+user+"\"";
				
				st = conn.createStatement();
				st.executeUpdate(statement);
				
			} finally {

				BancoDados.finalizarStatement(st);
				BancoDados.desconectar();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
