package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InsereDadosDao {
	public static void insertCategoria(String tabela, String categoria, String user) throws SQLException {
		Statement st = null;
		try {
			try {
				Connection conn = BancoDados.conectar();
				String statement = "insert into categoria" + tabela + " (categoria, user) values (\"" + categoria+ "\", \"" +user+"\")";
				
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
	
	public static void cadastrar(ArrayList<String> tupla, String user) throws SQLException {
		Statement st = null;
		try {
			try {
				Connection conn = BancoDados.conectar();

				String statement = "insert into " + tupla.get(0) + " (" + tupla.get(1) + ", user) values (" + tupla.get(2)
						+ ",\""+user+"\" )";
				
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
