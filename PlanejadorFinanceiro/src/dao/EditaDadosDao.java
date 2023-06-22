package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EditaDadosDao {
	public static void editarCategoria(String tabela, String categoria, String novo, String user) throws SQLException {
		Statement st = null;
		try {
			try {
				Connection conn = BancoDados.conectar();
				String statement = "update categoria" + tabela + " set categoria = \"" +novo+ "\" where categoria = \""+categoria+"\" and user = \""+user+"\" ";
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
	
	public static void editarTupla(String tabela, String codigo, String categoria, String ano, String despesa, String mensal, String ocasional, String user) throws SQLException {
		Statement st = null;
		try {
			try {
				Connection conn = BancoDados.conectar();
				String statement = "update " + tabela + " set categoria = \"" +categoria+ "\", ano = \""+ano+"\" , nome = \""+despesa+"\", mensal = \""+mensal+"\", ocasional = \""+ocasional+"\"    where codigo = "+codigo+" and user = \""+user+"\"";
				st = conn.createStatement();
				st.executeUpdate(statement);
				
			} finally {

				BancoDados.finalizarStatement(st);
				BancoDados.desconectar();
			}
		} catch ( IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
