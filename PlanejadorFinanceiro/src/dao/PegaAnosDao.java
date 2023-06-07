package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PegaAnosDao {
	public static ArrayList<Integer> getTable(String tabela) {

		ArrayList<Integer> anos = new ArrayList();
		try {

			Connection conn = BancoDados.conectar();
			System.out.println("Conexão estabelecida.");

			PreparedStatement st = null;
			ResultSet rs = null;

			try {
				st = conn.prepareStatement("select distinct ano from " + tabela);
				rs = st.executeQuery();

				while (rs.next()) {

					anos.add(rs.getInt("ano"));
				}

			} finally {

				BancoDados.finalizarStatement(st);
				BancoDados.finalizarResultSet(rs);
				BancoDados.desconectar();
			}

		} catch (SQLException | IOException e) {
			System.out.println(">>>" + e.getMessage());
		}
		return anos;
	}
}
