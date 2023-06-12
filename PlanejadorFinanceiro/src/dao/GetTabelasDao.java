package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetTabelasDao {

	public static ArrayList<ArrayList<Object>> getTable(String tabela) {

		ArrayList<ArrayList<Object>> lista = new ArrayList();
		try {

			Connection conn = BancoDados.conectar();
			System.out.println("Conexão estabelecida.");

			PreparedStatement st = null;
			ResultSet rs = null;

			try {
				st = conn.prepareStatement("select * from " + tabela);
				rs = st.executeQuery();

				if (tabela == "rendimentos") {
					while (rs.next()) {
						ArrayList<Object> tupla = new ArrayList();
						tupla.add(rs.getString("categoria"));
						tupla.add(rs.getString("rendimento"));
						tupla.add(rs.getDouble("mensal"));
						tupla.add(rs.getDouble("ocasional"));
						tupla.add(rs.getDouble("total"));

						lista.add(tupla);
					}
				}
				else if(tabela == "despesas") {
					while (rs.next()) {
						ArrayList<Object> tupla = new ArrayList();
						tupla.add(rs.getInt("codigo"));
						tupla.add(rs.getInt("ano"));
						tupla.add(rs.getInt("mes"));
						tupla.add(rs.getInt("duracao"));
						tupla.add(rs.getString("categoria"));
						tupla.add(rs.getString("despesa"));
						tupla.add(rs.getDouble("mensal"));
						tupla.add(rs.getDouble("ocasional"));
						tupla.add(rs.getDouble("total"));

						lista.add(tupla);
					}
				}

			} finally {

				BancoDados.finalizarStatement(st);
				BancoDados.finalizarResultSet(rs);
				BancoDados.desconectar();
			}

		} catch (SQLException | IOException e) {
			System.out.println(">>>"+e.getMessage());
		}
		return lista;
	}
	
	public static ArrayList<Integer> getTableYears(String tabela) {

		ArrayList<Integer> anos = new ArrayList();
		try {

			Connection conn = BancoDados.conectar();
			System.out.println("Conexão estabelecida.");

			PreparedStatement st = null;
			ResultSet rs = null;

			try {
				st = conn.prepareStatement("select distinct ano from " + tabela+" order by ano DESC");
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