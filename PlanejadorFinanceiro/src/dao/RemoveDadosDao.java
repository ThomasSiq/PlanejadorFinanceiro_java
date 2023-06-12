package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RemoveDadosDao {
	public static void removeCategoria(String tabela, String categoria) {
		Statement st = null;
		try {
			try {
				Connection conn = BancoDados.conectar();
				System.out.println("Conexão estabelecida.");

				String statement = "delete from categoria" + tabela + " where categoria = \"" + categoria+"\"";
				System.out.println(statement);
				
				st = conn.createStatement();
				st.executeUpdate(statement);
				
			} finally {

				BancoDados.finalizarStatement(st);
				BancoDados.desconectar();
				System.out.println("Conexão finalizada??.");
			}
		} catch (SQLException | IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
