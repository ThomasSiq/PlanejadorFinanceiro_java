package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertDadosDao {
	public static void insertCategoria(String tabela, String categoria) {
		Statement st = null;
		try {
			try {
				Connection conn = BancoDados.conectar();
				String statement = "insert into categoria" + tabela + " (categoria) values (\"" + categoria+ "\")";
				
				st = conn.createStatement();
				st.executeUpdate(statement);
				
			} finally {

				BancoDados.finalizarStatement(st);
				BancoDados.desconectar();
			}
		} catch (SQLException | IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
