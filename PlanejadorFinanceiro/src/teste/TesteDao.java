package teste;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.BancoDados;

public class TesteDao {

		public static void main(String[] args) {
			try {

				Connection conn = BancoDados.conectar();
				System.out.println("Conexão estabelecida.");
				
				PreparedStatement st = null;
				ResultSet rs = null;
				
				try {
					st = conn.prepareStatement("select * from rendimentos");
					rs = st.executeQuery();
					
					while(rs.next()) {
						System.out.println(rs.getString("categoria"));
					}
						
				}finally {
					BancoDados.finalizarStatement(st);
					BancoDados.finalizarResultSet(rs);
					BancoDados.desconectar();
				}
				
				
				
				BancoDados.desconectar();
				System.out.println("Conexão finalizada.");
			} catch (SQLException | IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

