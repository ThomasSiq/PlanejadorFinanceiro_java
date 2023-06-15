package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EditarDadosDao {
	public static void editarCategoria(String tabela, String categoria, String novo) {
		Statement st = null;
		try {
			try {
				Connection conn = BancoDados.conectar();
				String statement = "update categoria" + tabela + " set categoria = \"" +novo+ "\" where categoria = \""+categoria+"\"";
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
	
	public static void editarTupla(String tabela, String codigo, String categoria, String ano, String despesa, String mensal, String ocasional) {
		Statement st = null;
		try {
			try {
				Connection conn = BancoDados.conectar();
				String statement = "update " + tabela + " set categoria = \"" +categoria+ "\", ano = \""+ano+"\" , despesa = \""+despesa+"\", mensal = \""+mensal+"\", ocasional = \""+ocasional+"\"    where codigo = "+codigo;
				System.out.println(statement);
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