package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InsertTable {
	public static void cadastrar(ArrayList<String> tupla) throws SQLException {
		Statement st = null;
		try {
			try {
				Connection conn = BancoDados.conectar();
				System.out.println("Conexão estabelecida.");

				String statement = "insert into " + tupla.get(0) + " (" + tupla.get(1) + ") values (" + tupla.get(2)
						+ ")";
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